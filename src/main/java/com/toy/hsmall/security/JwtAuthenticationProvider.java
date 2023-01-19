package com.toy.hsmall.security;

import com.toy.hsmall.common.enums.Role;
import com.toy.hsmall.common.security.AuthenticationRequest;
import com.toy.hsmall.userinfo.model.Email;
import com.toy.hsmall.userinfo.model.UserInfo;
import com.toy.hsmall.userinfo.service.UserService;
import com.toy.hsmall.userinfo.vo.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final Jwt jwt;
    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;

        return processUserAuthentication(jwtAuthenticationToken.authenticationRequest());
    }

    private Authentication processUserAuthentication(AuthenticationRequest request) throws Exception {
        try {
            UserInfo userInfo = userService.login(new Login.Request(request.getPrincipal(), request.getCredentials()));
            JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(
                    new JwtAuthentication(userInfo.getUserNo(), userInfo.getUserName(), userInfo.getEmail()),
                    null,
                    createAuthorityList(Role.USER.toString())
            );
            String apiToken = userInfo.newApiToken(jwt, new String[]{Role.USER.toString()});
            authenticationToken.setDetails(new AuthenticationResult(apiToken, userInfo));
            return authenticationToken;
        } catch (NotFoundE)
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
