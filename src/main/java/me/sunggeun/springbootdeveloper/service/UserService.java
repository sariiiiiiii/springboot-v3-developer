package me.sunggeun.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.sunggeun.springbootdeveloper.domain.User;
import me.sunggeun.springbootdeveloper.dto.AddUserRequest;
import me.sunggeun.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest request) {
        return userRepository.save(
                User.builder()
                        .email(request.email())
                        .password(bCryptPasswordEncoder.encode(request.password()))
                        .build()
        ).getId();
    }

}
