package projects.java.taskapi.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import projects.java.taskapi.dtos.TaskDto;
import projects.java.taskapi.models.Task;
import projects.java.taskapi.models.User;
import projects.java.taskapi.repositories.TaskRepository;
import projects.java.taskapi.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Task addTask(TaskDto taskDto, UserDetails userDetails) {

        Task task = Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .status(taskDto.getStatus())
                .user(getUserByEmail(userDetails.getUsername()))
                .build();

        return taskRepository.save(task);
    }

    public List<Task> getUserTasks(UserDetails userDetails) {
        User user = getUserByEmail(userDetails.getUsername());
        return taskRepository.findByUser(user);
    }

    public Task getUserTaskById(UserDetails userDetails, Long taskId) {
        return taskRepository.findByUserAndId(getUserByEmail(userDetails.getUsername()), taskId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Задача с id: %d у пользователя с email: %s не найдена", taskId, userDetails.getUsername())));
    }


    protected User getUserByEmail(String email){
         return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Пользователь с email: %s не найден", email)));
    }


    public Task updateUserTaskById(UserDetails userDetails, Long taskId, TaskDto taskDto) {

        Task task = getUserTaskById(userDetails, taskId);
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());

        return taskRepository.save(task);
    }

    public void deleteUserTaskById(UserDetails userDetails, Long taskId) {
        Task task = getUserTaskById(userDetails, taskId);
        taskRepository.delete(task);
    }
}
