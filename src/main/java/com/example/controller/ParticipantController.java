package com.example.controller;

import com.example.dto.usertask.UserTaskDto;
import com.example.error.ApiError;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/Unity/participants")
@RequiredArgsConstructor
@Api(tags = "Participant")
public class ParticipantController {

    private final UserService userService;

    @PostMapping("/{participantId}/tasks/{taskId}")
    @ApiOperation(value = "Take part in task")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all users", response = UserTaskDto.class),
            @ApiResponse(code = 400, message = "Bad request", response = ApiError.class),
            @ApiResponse(code = 404, message = "Non-existing user id or task id", response = ApiError.class)
    })
    public UserTaskDto takePartInTask(@PathVariable int participantId, @PathVariable int taskId, @Valid @RequestBody UserTaskDto userTaskDto) {
        return userService.takePartInTask(participantId, taskId, userTaskDto);
    }

    @PatchMapping("/approve/{participantId}/tasks/{taskId}")
    @ApiOperation(value = "Approve participant")
    public UserTaskDto approveParticipant(@PathVariable int participantId, @PathVariable int taskId, @RequestParam boolean approved) {
        return userService.approveUserForTask(participantId, taskId, approved);
    }
}