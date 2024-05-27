package com.example.authservice.service;

import com.example.authservice.entity.UserEntity;
import com.example.authservice.repository.UserRepo;
import com.example.authservice.request.LoginRequest;
import com.example.authservice.request.RegisterRequest;
import com.example.authservice.response.UserTokenResponse;
import com.example.common.base.exception.InvalidCredentialsException;
import com.example.common.base.exception.UserAlreadyExistsException;
import com.example.common.base.exception.UserNotFoundException;
import com.example.common.base.util.ApiResult;
import com.example.common.base.util.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Resource
    private UserRepo userRepo;


    public ApiResult login(LoginRequest request) throws Exception {
        try {
            Optional<UserEntity> user = userRepo.findByUsername(request.getUsername());

            if (user.isEmpty()) {
                throw new UserNotFoundException("Username not found: " + request.getUsername());
            }

            if (!isPasswordValid(request.getPassword(), user.get().getPassword())) {
                throw new InvalidCredentialsException("Invalid password");
            }

            String jwtToken = JwtUtil.getInstance().generateToken(user.get().getUsername(), user.get().getId());

            Date lastLoginTime = new Date();
            user.get().setLastLogin(lastLoginTime);
            userRepo.save(user.get());

            UserTokenResponse userTokenResponse = UserTokenResponse.builder()
                    .id(user.get().getId())
                    .username(user.get().getUsername())
                    .jwtToken(jwtToken)
                    .jwtTokenId(UUID.randomUUID().toString())
                    .lastLoginTime(lastLoginTime)
                    .build();

            return ApiResult.success(userTokenResponse);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ApiResult register(RegisterRequest request) {
        Optional<UserEntity> user = userRepo.findByUsername(request.getUsername());
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("Username already exist: " + request.getUsername());
        }

        UserEntity userEntity = UserEntity.builder()
                .username(request.getUsername())
                .password(hashPassword(request.getPassword()))
                .createdAt(new Date())
                .build();
        userRepo.save(userEntity);

        return ApiResult.success("Register success");
    }

    public String hashPassword(String plainTextPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(plainTextPassword);
    }

    public boolean isPasswordValid(String plainTextPassword, String hashedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(plainTextPassword, hashedPassword);
    }
}
