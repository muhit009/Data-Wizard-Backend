package com.datawizard.backend.datawizardbackend.service;

import com.datawizard.backend.datawizardbackend.domain.middle.UserMiddle;
import com.datawizard.backend.datawizardbackend.domain.request.UserRequest;
import com.datawizard.backend.datawizardbackend.domain.response.UserResponse;
import com.datawizard.backend.datawizardbackend.service.impl.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
    }

    @Override
    public UserResponse getUser(int userID) {
        //TODO DAO call
        UserMiddle middle = new UserMiddle();
        return new UserResponse(middle);
    }

    @Override
    public UserResponse createUser(UserRequest request) {
        UserMiddle middle = preprocessAndValidate(request);
        //TODO DAO call
        return new UserResponse(middle);
    }

    @Override
    public UserResponse updateUser(UserRequest request) {
        UserMiddle middle = preprocessAndValidate(request);
        //TODO DAO call
        return new UserResponse(middle);
    }

    @Override
    public UserResponse deleteUser(int userID) {
        //TODO DAO call
        UserMiddle middle = new UserMiddle();
        return new UserResponse(middle);
    }

    private UserMiddle preprocessAndValidate(UserRequest request){
        //TODO validation
        return new UserMiddle(request);
    }
}
