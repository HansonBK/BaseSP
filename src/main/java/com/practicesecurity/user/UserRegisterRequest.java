package com.practicesecurity.user;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class UserRegisterRequest{


    @Setter
    @Getter
    @NotBlank(message = "Username is required")
    private String username;
    @Getter
    @NotBlank(message = "password is required")
    private String password;


    public UserRegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserRegisterRequest() {}
}
