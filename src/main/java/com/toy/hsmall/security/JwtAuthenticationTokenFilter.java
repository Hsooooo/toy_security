package com.toy.hsmall.security;

import com.toy.hsmall.userinfo.model.Email;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static io.micrometer.common.util.StringUtils.isNotEmpty;
import static java.util.Objects.nonNull;

@Slf4j
public class JwtAuthenticationTokenFilter extends GenericFilterBean {
    private static final Pattern BEARER = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);
    private final String headerKey;
    private final Jwt jwt;

    public JwtAuthenticationTokenFilter(String headerKey, Jwt jwt) {
        this.headerKey = headerKey;
        this.jwt = jwt;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            String authorizationToken = obtainAuthorizationToken(req);
            if (authorizationToken != null) {
                try {
                    Jwt.Claims claims = verify(authorizationToken);
                    log.debug("Jwt parse result : {}", claims);

                    if (canRefresh(claims, 6000 * 10)) {
                        String refreshedToken = jwt.refreshToken(authorizationToken);
                        res.setHeader(headerKey, refreshedToken);
                    }

                    Long userKey = claims.userKey;
                    String name = claims.name;
                    String email = claims.email;

                    List<GrantedAuthority> authorities = obtainAuthorities(claims);

                    if (nonNull(userKey) && isNotEmpty(name) && nonNull(email) && authorities.size() > 0) {
                        JwtAuthenticationToken authenticationToken =
                                new JwtAuthenticationToken(new JwtAuthentication(userKey, name, email), null, authorities);
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                } catch (Exception e) {
                    log.warn("Jwt processing failed : {}", e.getLocalizedMessage());
                }
            } else {
                log.debug("SecurityContextHolder not populated with security token, as it already contained: '{}'",
                        SecurityContextHolder.getContext().getAuthentication());
            }

            chain.doFilter(req, res);
        }
    }

    public String obtainAuthorizationToken(HttpServletRequest request) {
        String token = request.getHeader(headerKey);
        if (token != null) {
            if (log.isDebugEnabled())
                log.debug("Jwt authorization api detected : {}", token);
            try {
                token = URLDecoder.decode(token, "UTF-8");
                String[] parts = token.split(" ");
                if (parts.length == 2) {
                    String schema = parts[0];
                    String credentials = parts[1];
                    return BEARER.matcher(schema).matches() ? credentials : null;
                }
            } catch (UnsupportedEncodingException e) {
                log.error(e.getLocalizedMessage(), e);
            }
        }
        return null;
    }

    private boolean canRefresh(Jwt.Claims claims, long refreshRangeMillis) {
        long exp = claims.exp();
        if (exp > 0) {
            long remain = exp - System.currentTimeMillis();
            return remain < refreshRangeMillis;
        }
        return false;
    }

    private List<GrantedAuthority> obtainAuthorities(Jwt.Claims claims) {
        String[] roles = claims.roles;
        return roles == null || roles.length == 0 ?
                Collections.emptyList() :
                Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    private Jwt.Claims verify(String token) {
        return jwt.verify(token);
    }
}
