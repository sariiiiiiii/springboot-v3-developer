package me.sunggeun.springbootdeveloper.exercises.controller;

import lombok.RequiredArgsConstructor;
import me.sunggeun.springbootdeveloper.exercises.entity.Member;
import me.sunggeun.springbootdeveloper.exercises.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/test")
    public List<Member> getAllMembers() {
        return testService.getAllMembers();
    }

}
