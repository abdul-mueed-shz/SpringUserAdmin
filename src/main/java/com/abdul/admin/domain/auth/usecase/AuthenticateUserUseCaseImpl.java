package com.abdul.admin.domain.auth.usecase;

import com.abdul.admin.domain.auth.model.AuthenticationInfo;
import com.abdul.admin.domain.auth.model.AuthenticationRequestInfo;
import com.abdul.admin.domain.auth.port.in.AuthenticateUserUseCase;
import com.abdul.admin.domain.auth.port.in.GenerateJwtTokenUseCase;
import com.abdul.admin.domain.user.model.UserInfo;
import com.abdul.admin.domain.user.port.in.GetUserDetailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateUserUseCaseImpl implements AuthenticateUserUseCase {

    private final AuthenticationManager authenticationManager;
    private final GenerateJwtTokenUseCase generateJwtTokenUseCase;
    private final GetUserDetailUseCase getUserDetailUseCase;

    public AuthenticationInfo authenticate(AuthenticationRequestInfo authenticationRequestInfo) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(authenticationRequestInfo.getUsername(),
                        authenticationRequestInfo.getPassword());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserInfo userInfo = getUserDetailUseCase.get(authenticationRequestInfo.getUsername());
        return generateAuthenticationResponse(userInfo);
    }

    private AuthenticationInfo generateAuthenticationResponse(UserInfo userInfo) {
        String accessToken = generateJwtTokenUseCase.generateAccessToken(userInfo);
        String refreshToken = generateJwtTokenUseCase.generateRefreshToken(userInfo);
        return AuthenticationInfo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}