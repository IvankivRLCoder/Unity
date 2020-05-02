package com.example.service.impl;

import com.example.dao.TaskDao;
import com.example.dao.UserDao;
import com.example.dto.apiKey.ApiKeyDto;
import com.example.dto.pagination.PaginationDto;
import com.example.dto.task.MainTaskDto;
import com.example.dto.task.MainUserTaskDto;
import com.example.dto.task.TaskDto;
import com.example.dto.user.GetUserDto;
import com.example.error.BadCredentialsException;
import com.example.error.EntityNotFountException;
import com.example.error.UserIsNotCreatorException;
import com.example.filter.TaskFilter;
import com.example.model.*;
import com.example.service.TaskService;
import com.example.service.UserService;
import com.example.utils.PaginationUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
@SuppressWarnings("Duplicates")
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;

    private final UserDao userDao;

    private final UserService userService;

    private final ModelMapper modelMapper;

    @Override
    public MainTaskDto createTask(TaskDto taskDto, int userId) {
        int apiKeyId = userService.getByApiKey(taskDto.getApiKey());
        if (userId != apiKeyId) {
            throw new BadCredentialsException("Your apiKey is not tied to this id");
        }
        Task task = modelMapper.map(taskDto, Task.class);
        task.setCreator(getByUserId(userId));
        task.setCreationDate(LocalDate.now());
        task.setStatus(Status.PENDING);
        calculateTaskPriority(task);
        return modelMapper.map(taskDao.save(task), MainTaskDto.class);
    }

    @Override
    public MainTaskDto getTaskById(int id) {
        Task task = getSingleTask(id);
        calculateTaskPriority(task);
        taskDao.update(task);
        return modelMapper.map(task, MainTaskDto.class);
    }

    @Override
    public PaginationDto<MainTaskDto> getAllTasks(Integer offset, Integer limit, String criteria, String priority,
                                                  String category, String order) {
        List<Task> tasksFromDB = taskDao.getAll();
        tasksFromDB.forEach(task -> {
            task.setApprovedParticipants(
                    (int) task.getUserTasks()
                            .stream()
                            .filter(UserTask::isApproved)
                            .count()
            );
            calculateTaskPriority(task);
            if (task.getApprovedParticipants() == task.getPossibleNumberOfParticipants()) {
                task.setStatus(Status.ACTIVE);
            }
            taskDao.update(task);
        });

        List<MainTaskDto> mappedTasks = tasksFromDB
                .stream()
                .map(task -> modelMapper.map(task, MainTaskDto.class))
                .collect(Collectors.toList());

        List<MainTaskDto> criteriaSorted = TaskFilter.filterByCriteria(mappedTasks, criteria, order);
        List<MainTaskDto> categorySorted = TaskFilter.filterByCategory(criteriaSorted, category, order);
        List<MainTaskDto> prioritySorted = TaskFilter.filterByPriority(categorySorted, priority, order);
        List<MainTaskDto> mainTaskDtos = TaskFilter.initialFilter(prioritySorted, order);

        if (limit != null && offset != null) {
            return PaginationUtils.paginate(mainTaskDtos, offset, limit);
        }
        return PaginationDto.<MainTaskDto>builder()
                .entities(mainTaskDtos)
                .quantity(0)
                .entitiesLeft(0)
                .build();

    }

    @Override
    public void deleteTask(int id, ApiKeyDto apiKeyDto) {
        userService.getByApiKey(apiKeyDto.getApiKey());
        taskDao.delete(getSingleTask(id));
    }

    @Override
    public MainTaskDto updateTask(TaskDto taskDto, int id) {
        userService.getByApiKey(taskDto.getApiKey());
        Task task = getSingleTask(id);
        calculateTaskPriority(task);
        int userId = userService.getByApiKey(taskDto.getApiKey());

        if (task.getCreator().getId() != userId) {
            throw new UserIsNotCreatorException("User is not creator of task: " + task.getId());
        }
        Task newTask = modelMapper.map(taskDto, Task.class);

        task.setCategory(newTask.getCategory());
        task.setCreationDate(newTask.getCreationDate());
        task.setDescription(newTask.getDescription());
        task.setPriority(newTask.getPriority());
        task.setPossibleNumberOfParticipants(newTask.getPossibleNumberOfParticipants());
        task.setStatus(newTask.getStatus());
        task.setTitle(newTask.getTitle());

        return modelMapper.map(taskDao.update(task), MainTaskDto.class);
    }

    @Override
    public List<MainUserTaskDto> getAllUsersByTaskId(int id) {
        return taskDao.getById(id).getUserTasks()
                .stream()
                .map(userTask -> modelMapper.map(userTask, MainUserTaskDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PaginationDto<GetUserDto> getAllApprovedUsers(Integer offset, Integer limit, int taskId) {
        Task task = getSingleTask(taskId);
        calculateTaskPriority(task);
        taskDao.update(task);
        List<GetUserDto> approvedUsers = task.getUserTasks()
                .stream()
                .filter(UserTask::isApproved)
                .map(userTask -> modelMapper.map(userTask.getUser(), GetUserDto.class))
                .collect(Collectors.toList());

        if (limit != null && offset != null) {
            return PaginationUtils.paginate(approvedUsers, offset, limit);
        }
        return PaginationDto.<GetUserDto>builder()
                .entities(approvedUsers)
                .quantity(0)
                .entitiesLeft(0)
                .build();

    }

    private Task getSingleTask(int id) {
        Task task = taskDao.getById(id);
        if (task == null) {
            throw new EntityNotFountException("Task is not found with id = " + id);
        }
        long approvedParticipants = task.getUserTasks()
                .stream()
                .filter(UserTask::isApproved)
                .count();
        task.setApprovedParticipants((int) approvedParticipants);
        if (approvedParticipants == task.getPossibleNumberOfParticipants()) {
            task.setStatus(Status.ACTIVE);
        }
        return task;
    }

    private User getByUserId(int id) {
        User user = userDao.getById(id);
        if (user == null) {
            throw new EntityNotFountException("User is not found with id = " + id);
        }
        return user;
    }

    private void calculateTaskPriority(Task task) {
        LocalDate creationDate = task.getCreationDate();
        LocalDate endDate = task.getEndDate();

        int possibleNumberOfParticipants = task.getPossibleNumberOfParticipants();
        int approvedParticipants = task.getApprovedParticipants();

        String status = task.getStatus().getTaskStatus();
        if (status.equalsIgnoreCase("active") || status.equalsIgnoreCase("done")) {
            task.setPriority(Priority.NONE);
            return;
        }

        double participantsDiff = possibleNumberOfParticipants - approvedParticipants;
        long datesDiff = DAYS.between(creationDate, endDate);

        double coef = datesDiff / participantsDiff;
        if (coef < 1) {
            task.setPriority(Priority.CRITICAL);
        } else if (coef >= 1 && coef <= 1.5) {
            task.setPriority(Priority.HIGH);
        } else if (coef > 1.5 && coef <= 2.5) {
            task.setPriority(Priority.MEDIUM);
        } else task.setPriority(Priority.LOW);

    }

}
