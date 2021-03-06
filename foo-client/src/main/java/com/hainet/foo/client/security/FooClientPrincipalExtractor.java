package com.hainet.foo.client.security;

import com.hainet.foo.client.domain.dao.FooUserDao;
import com.hainet.foo.client.domain.entity.FooUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FooClientPrincipalExtractor implements PrincipalExtractor {

    @Value("${authorization-server.uri}")
    private String authorizationServerUri;

    private final FooUserDao dao;

    @Override
    public Object extractPrincipal(final Map<String, Object> map) {
        final FooUser fooUser = dao.findByUsername((String) map.get("username"));
        if (fooUser == null) {
            throw new UsernameNotFoundException("Invalid username!");
        }

        if (map.get("logged_in_at") != null) {
            fooUser.setLoggedInAt(LocalDateTime.parse((String) map.get("logged_in_at")));
        }
        if (map.get("failed_to_log_in_at") != null) {
            fooUser.setFailedToLogInAt(LocalDateTime.parse((String) map.get("failed_to_log_in_at")));
        }

        return fooUser;
    }
}
