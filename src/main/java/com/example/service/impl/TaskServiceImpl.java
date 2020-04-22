package com.example.service.impl;

import com.example.dao.TaskDao;
import com.example.dao.UserDao;
import com.example.dto.apiKey.ApiKeyDto;
import com.example.dto.pagination.PaginationDto;
import com.example.dto.task.MainTaskDto;
import com.example.dto.task.MainUserTaskDto;
import com.example.dto.task.TaskDto;
import com.example.error.EntityNotFountException;
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

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;

    private final UserDao userDao;

    private final UserService userService;

    private final ModelMapper modelMapper;

    @Override
    public MainTaskDto createTask(TaskDto taskDto, int userId) {
        userId = userService.getByApiKey(taskDto.getApiKey());
        Task task = modelMapper.map(taskDto, Task.class);
        task.setCreator(getByUserId(userId));
        task.setActive(false);
        task.setCreationDate(LocalDate.now());
        return modelMapper.map(taskDao.save(task), MainTaskDto.class);
    }

    @Override
    public MainTaskDto getTaskById(int id) {
        return modelMapper.map(getByTaskId(id), MainTaskDto.class);
    }

    @Override
    public PaginationDto<MainTaskDto> getAllTasks(Integer pageNumber, String priority,
                                                  String category, String order) {
        if (pageNumber == null) {
            pageNumber = 1;
        }
        List<Task> tasksFromDB = taskDao.getAll();
        tasksFromDB.forEach(task -> {
            task.setApprovedParticipants(
                    (int) task.getUserTasks()
                            .stream()
                            .filter(UserTask::isApproved)
                            .count()
            );
            if (task.getApprovedParticipants() == task.getPossibleNumberOfParticipants()) {
                task.setStatus(Status.ACTIVE);
                task.setActive(true);
            }
        });

        List<MainTaskDto> mappedTasks = tasksFromDB
                .stream()
                .map(task -> modelMapper.map(task, MainTaskDto.class))
                .collect(Collectors.toList());
        List<MainTaskDto> categorySorted = TaskFilter.filterByCategory(mappedTasks, category, order);
        List<MainTaskDto> prioritySorted = TaskFilter.filterByPriority(categorySorted, priority, order);

        return PaginationUtils.paginate(TaskFilter.initialFilter(prioritySorted, order), pageNumber);
    }

    @Override
    public void deleteTask(int id, ApiKeyDto apiKeyDto) {
        userService.getByApiKey(apiKeyDto.getApiKey());
        taskDao.delete(getByTaskId(id));
    }

    @Override
    public MainTaskDto updateTask(TaskDto taskDto, int id) {
        userService.getByApiKey(taskDto.getApiKey());
        Task task = getByTaskId(id);
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
        return taskDao.getById(id).getUserTasks().stream()
                .map(userTask -> modelMapper.map(userTask, MainUserTaskDto.class))
                .collect(Collectors.toList());
    }

    private Task getByTaskId(int id) {
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

    private Priority calculateTaskPriority(Task task) {
        LocalDate taskCreationDate = task.getCreationDate();
        int possibleParticipants = task.getPossibleNumberOfParticipants();
        String taskStatus = task.getStatus().getTaskStatus();
        boolean active = task.isActive();
        int approvedParticipants = task.getApprovedParticipants();

        if (taskStatus.equalsIgnoreCase("active")
                || taskStatus.equalsIgnoreCase("done")
                || active) {
            return Priority.NONE;
        }

        return null;
    }

}
