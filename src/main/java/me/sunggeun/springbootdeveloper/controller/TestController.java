package me.sunggeun.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.sunggeun.springbootdeveloper.entity.Member;
import me.sunggeun.springbootdeveloper.service.TestService;
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
