package com.datawizard.backend.datawizardbackend.service;

import com.datawizard.backend.datawizardbackend.domain.exception.APIBadRequestException;
import com.datawizard.backend.datawizardbackend.domain.exception.APIInternalServerErrorException;
import com.datawizard.backend.datawizardbackend.domain.request.UserRequest;
import com.datawizard.backend.datawizardbackend.domain.response.UserResponse;

public interface UserService {
    UserResponse getUser(int userID) throws APIInternalServerErrorException, APIBadRequestException;
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(UserRequest request);
    UserResponse deleteUser(int userID);
}
