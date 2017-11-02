package com.hainet.authorization.server.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import java.util.ArrayList;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                    // Foo Client
                    .withClient("foo-client-id")
                    .secret("535ee78a-cc58-49aa-82f6-d976a79456ce")
                    .scopes(new ArrayList<String>() {{
                        add("resource.read");
                        add("resource.write");
                    }}.toArray(new String[0]))
                    .autoApprove(new ArrayList<String>() {{
                        add("resource.read");
                        add("resource.write");
                    }}.toArray(new String[0]))
                    .authorizedGrantTypes("authorization_code")
                    .and()
                    // Bar Client
                    .withClient("bar-client-id")
                    .secret("3aefd491-61ae-4423-8e7b-bfd6fd4b15d4")
                    .scopes(new ArrayList<String>() {{
                        add("resource.read");
                        add("resource.write");
                    }}.toArray(new String[0]))
                    .autoApprove(new ArrayList<String>() {{
                        add("resource.read");
                        add("resource.write");
                    }}.toArray(new String[0]))
                    .authorizedGrantTypes("authorization_code");
    }
}
