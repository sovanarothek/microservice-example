package com.example.common.base.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenUser {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUser.class);
    Long id;
    String username;
}
