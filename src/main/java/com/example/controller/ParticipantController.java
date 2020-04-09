package com.example.controller;

import com.example.dto.apiKey.ApiKeyDto;
import com.example.dto.task.MainUserTaskDto;
import com.example.dto.user.MainTaskUserDto;
import com.example.dto.user.MainUserDto;
import com.example.dto.usertask.UserTaskDto;
import com.example.error.ApiError;
import com.example.service.TaskService;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/Unity/participants")
@RequiredArgsConstructor
@Api(tags = "Participant")
public class ParticipantController {

    private final UserService userService;

    private final TaskService taskService;

    @PostMapping("/{participantId}/tasks/{taskId}")
    @ApiOperation(value = "Take part in task")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all users", response = UserTaskDto.class),
            @ApiResponse(code = 400, message = "Bad request", response = ApiError.class),
            @ApiResponse(code = 404, message = "Non-existing user id or task id", response = ApiError.class)
    })
    public MainTaskUserDto takePartInTask(@PathVariable int participantId, @PathVariable int taskId, @Valid @RequestBody UserTaskDto userTaskDto) {
        return userService.takePartInTask(participantId, taskId, userTaskDto);
    }

    @PatchMapping("/approve/{participantId}/tasks/{taskId}")
    @ApiOperation(value = "Approve participant")
    public MainUserTaskDto approveParticipant(@PathVariable int participantId, @PathVariable int taskId, @RequestParam boolean approved, @RequestBody ApiKeyDto apiKeyDto) {
        return userService.approveUserForTask(participantId, taskId, approved, apiKeyDto);
    }

    @GetMapping("/{userId}/tasks")
    @ApiOperation(value = "View a list of all tasks by user id")
    @ApiResponse(code = 200, message = "List of all tasks", response = MainTaskUserDto.class)
    public List<MainTaskUserDto> getAllTasksByUserId(@PathVariable("userId") int id) {
        return userService.getAllTasksByUserId(id);
    }

    @GetMapping("/{taskId}/users")
    @ApiOperation(value = "View a list of all participants of the task by id")
    @ApiResponse(code = 200, message = "List of all users", response = MainUserDto.class)
    public List<MainUserTaskDto> getAllUsersByTaskId(@PathVariable("taskId") int id) {
        return taskService.getAllUsersByTaskId(id);
    }

}