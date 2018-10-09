package com.szcti.lcloud.recommbuy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(locations = {"classpath:/bootstrap.yml"})
@ConfigurationProperties(prefix = "jwt")
public class JwtYmlConfig {
    private String expires_in;
    private String secret;
    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
