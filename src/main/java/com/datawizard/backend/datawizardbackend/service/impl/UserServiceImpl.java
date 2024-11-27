package com.datawizard.backend.datawizardbackend.service.impl;

import com.datawizard.backend.datawizardbackend.dao.UserDao;
import com.datawizard.backend.datawizardbackend.domain.exception.APIBadRequestException;
import com.datawizard.backend.datawizardbackend.domain.exception.APIInternalServerErrorException;
import com.datawizard.backend.datawizardbackend.domain.middle.UserMiddle;
import com.datawizard.backend.datawizardbackend.domain.request.UserRequest;
import com.datawizard.backend.datawizardbackend.domain.response.UserResponse;
import com.datawizard.backend.datawizardbackend.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserResponse getUser(int userID) throws APIInternalServerErrorException, APIBadRequestException {
        validateUserID(userID);
        UserMiddle middle = userDao.getUser(userID);
        if(middle == null){
            throw new APIBadRequestException("No user found for userID: " + userID);
        }
        return new UserResponse(middle);
    }

    @Override
    public UserResponse createUser(UserRequest request) {
        UserMiddle middle = preprocessAndValidate(request);
        middle = userDao.createUser(middle);
        return new UserResponse(middle);
    }

    @Override
    public UserResponse updateUser(UserRequest request) {
        UserMiddle middle = preprocessAndValidate(request);
        middle = userDao.updateUser(middle);
        return new UserResponse(middle);
    }

    @Override
    public UserResponse deleteUser(int userID) {
        validateUserID(userID);
        UserMiddle middle = userDao.disableUser(userID);
        return new UserResponse(middle);
    }

    private UserMiddle preprocessAndValidate(UserRequest request){
        //TODO validation
        return new UserMiddle(request);
    }

    private void validateUserID(int userID){
        if(userID <= 0){
            throw new APIBadRequestException("User ID must be a positive integer. Provided userID: " + userID);
        }
    }
}
