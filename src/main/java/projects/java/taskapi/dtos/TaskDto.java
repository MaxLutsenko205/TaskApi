package projects.java.taskapi.dtos;

import lombok.Data;
import projects.java.taskapi.models.Status;

@Data
public class TaskDto {

    private String title;
    private String description;
    private Status status;

}
