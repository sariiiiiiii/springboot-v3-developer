package me.sunggeun.springbootdeveloper.dto;

public record AddUserRequest(
        String email,
        String password
) {
}
