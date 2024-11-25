package com.abdul.admin.domain.user.usecase;

import com.abdul.admin.domain.user.model.UserInfo;
import com.abdul.admin.domain.user.model.UserRegistrationRequestInfo;
import com.abdul.admin.domain.user.port.in.RegisterUserUseCase;
import com.abdul.admin.domain.user.port.out.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepository userRepository;

    @Override
    public UserInfo execute(UserRegistrationRequestInfo userRegistrationRequestInfo) {
        return userRepository.save(userRegistrationRequestInfo);
    }
}