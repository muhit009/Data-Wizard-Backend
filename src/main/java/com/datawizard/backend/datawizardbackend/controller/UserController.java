package com.datawizard.backend.datawizardbackend.controller;

import com.datawizard.backend.datawizardbackend.domain.exception.APIBadRequestException;
import com.datawizard.backend.datawizardbackend.domain.exception.APIInternalServerErrorException;
import com.datawizard.backend.datawizardbackend.domain.request.UserRequest;
import com.datawizard.backend.datawizardbackend.domain.response.UserResponse;
import com.datawizard.backend.datawizardbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController(value = "users")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{userID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getUser(@PathVariable("userID") int userID) throws APIInternalServerErrorException, APIBadRequestException {
        return userService.getUser(userID);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PutMapping(value = "/{userID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse updateUser(@PathVariable("userID") int userID, @RequestBody UserRequest userRequest) {
        if(userRequest != null){
            userRequest.setUserID(userID);
        }
        return userService.updateUser(userRequest);
    }

    @DeleteMapping(value = "/{userID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse deleteUser(@PathVariable("userID") int userID) {
        return userService.deleteUser(userID);
    }
}
