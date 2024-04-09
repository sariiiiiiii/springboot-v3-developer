package me.sunggeun.springbootdeveloper.config.jwt;

import io.jsonwebtoken.Jwts;
import me.sunggeun.springbootdeveloper.domain.User;
import me.sunggeun.springbootdeveloper.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProperties jwtProperties;

    @Test
    @DisplayName("generationToken(): 유저 정보와 만료 기간을 전달해 토큰을 만들 수 있다.")
    void generateToken() {
        // given
        User testUser = userRepository.save(
                User.builder()
                        .email("test@naver.com")
                        .password("test")
                        .build()
        );

        // when
        String token = tokenProvider.generateToken(testUser, Duration.ofDays(14));

        // then
        Long userId = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);

        Assertions.assertThat(userId).isEqualTo(testUser.getId());
    }

}