package pl.edu.wat.backend.dto;

import lombok.Data;
import pl.edu.wat.domain.entity.User;

@Data
public class UserDto {

    private String username;

    private String email;

    public UserDto(User user) {
        setUsername(user.getName());
        setEmail(user.getEmail());
    }
}