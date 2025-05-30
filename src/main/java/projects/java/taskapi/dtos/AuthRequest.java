package projects.java.taskapi.dtos;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
