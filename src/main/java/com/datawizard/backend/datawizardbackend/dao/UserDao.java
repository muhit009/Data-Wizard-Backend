package com.datawizard.backend.datawizardbackend.dao;

import com.datawizard.backend.datawizardbackend.domain.exception.APIInternalServerErrorException;
import com.datawizard.backend.datawizardbackend.domain.middle.UserMiddle;

public interface UserDao {
    UserMiddle getUser(int userID) throws APIInternalServerErrorException;
    UserMiddle createUser(UserMiddle middle);
    UserMiddle updateUser(UserMiddle middle);
    UserMiddle disableUser(int userID);
}
