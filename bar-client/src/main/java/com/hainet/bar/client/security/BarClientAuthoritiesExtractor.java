package com.hainet.bar.client.security;

import com.hainet.bar.client.domain.dao.GrantDao;
import com.hainet.bar.client.domain.entity.Grant;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BarClientAuthoritiesExtractor implements AuthoritiesExtractor {

    private final GrantDao dao;

    @Override
    public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(
                dao.findByUsername(map.get("username").toString())
                        .stream()
                        .map(Grant::getName)
                        .collect(Collectors.toList())
                        .toString()
                        .replaceAll("\\[", "")
                        .replaceAll("]", ""));
    }

}
