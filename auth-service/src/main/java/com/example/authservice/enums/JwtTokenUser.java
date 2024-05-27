package com.example.authservice.enums;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenUser {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUser.class);
    Long id;
    String username;
    String tokenId;
    String jwtToken;

    public Map<String, Object> backMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.id);
        map.put("username", this.username);
        map.put("tokenId", this.tokenId);
        map.put("jwtToken", this.jwtToken);
        return map;
    }
}
