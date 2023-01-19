package com.toy.hsmall.common.configure;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt.token")
@Getter
@Setter
@ToString
public class JwtTokenConfigure {
    private String header;
    private String issuer;
    private String clientSecret;
    private int expirySeconds;
}
