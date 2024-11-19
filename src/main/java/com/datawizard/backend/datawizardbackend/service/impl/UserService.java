package com.datawizard.backend.datawizardbackend.service.impl;

import com.datawizard.backend.datawizardbackend.domain.request.UserRequest;
import com.datawizard.backend.datawizardbackend.domain.response.UserResponse;

public interface UserService {
    UserResponse getUser(int userID);
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(UserRequest request);
    UserResponse deleteUser(int userID);
}
