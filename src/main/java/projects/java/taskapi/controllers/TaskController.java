package projects.java.taskapi.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import projects.java.taskapi.dtos.TaskDto;
import projects.java.taskapi.models.Task;
import projects.java.taskapi.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("api/task")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto, @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.addTask(taskDto, userDetails));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllUserTasks(@AuthenticationPrincipal UserDetails userDetails){
        List<Task> tasks = taskService.getUserTasks(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping("/{task_id}")
    public ResponseEntity<Task> getUserTask(@PathVariable("task_id") Long taskId, @AuthenticationPrincipal UserDetails userDetails){
        Task task = taskService.getUserTaskById(userDetails, taskId);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @PutMapping("/{task_id}")
    public ResponseEntity<Task> updateUserTask(@PathVariable("task_id") Long taskId,
                                               @RequestBody TaskDto taskDto,
                                               @AuthenticationPrincipal UserDetails userDetails){
        Task task = taskService.updateUserTaskById(userDetails, taskId, taskDto);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @DeleteMapping("/{task_id}")
    public ResponseEntity<Void> deleteUserTask(@PathVariable("task_id") Long taskId, @AuthenticationPrincipal UserDetails userDetails){
        taskService.deleteUserTaskById(userDetails, taskId);
        return ResponseEntity.noContent().build();
    }
}
