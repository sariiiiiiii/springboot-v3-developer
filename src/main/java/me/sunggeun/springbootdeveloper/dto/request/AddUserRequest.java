package me.sunggeun.springbootdeveloper.dto.request;

public record AddUserRequest(
        String email,
        String password
) {
}
