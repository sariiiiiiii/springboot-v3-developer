package me.sunggeun.springbootdeveloper.config.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public record JwtProperties(
        String issuer,
        String secretKey
) {
}
