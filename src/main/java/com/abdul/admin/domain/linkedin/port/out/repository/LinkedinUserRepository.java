package com.abdul.admin.domain.linkedin.port.out.repository;

import com.abdul.admin.domain.linkedin.model.LinkedinUserInfo;

public interface LinkedinUserRepository {

    LinkedinUserInfo findByState(String state);
}