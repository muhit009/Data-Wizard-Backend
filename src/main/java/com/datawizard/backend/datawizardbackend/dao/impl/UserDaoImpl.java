package com.datawizard.backend.datawizardbackend.dao.impl;

import com.datawizard.backend.datawizardbackend.dao.UserDao;
import com.datawizard.backend.datawizardbackend.domain.exception.APIBadRequestException;
import com.datawizard.backend.datawizardbackend.domain.exception.APIInternalServerErrorException;
import com.datawizard.backend.datawizardbackend.domain.middle.UserMiddle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    Log logger = LogFactory.getLog(UserDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    private static final String GET_USER_BY_USER_ID = """
            SELECT user_id, email, username, first_name, last_name, date_of_birth,
            phone_number, created_at, updated_at, suspended_at, profile_picture,
            status, address, role
            FROM users
            where user_id = ?""";

    private static final String CREATE_USER = """
            INSERT INTO users (email, password_hash, username, first_name, last_name, date_of_birth,
            phone_number, profile_picture, address, role)
            VALUES (?,?,?,?,?,?,?,?,?,?)""";

    private static final String UPDATE_USER = """
            UPDATE users SET email = ?, username = ?, first_name = ?, last_name = ?, date_of_birth = ?,
            phone_number = ?, profile_picture = ?, address = ?, role = ?, updated_at = NOW()
            WHERE user_id = ?""";

    private static final String DISABLE_USER = """
            UPDATE users SET status = 'suspended', suspended_at = NOW(), updated_at = NOW() WHERE user_id = ?""";

    @SuppressWarnings("Convert2Lambda")
    private static final RowMapper<UserMiddle> userRowMapper = new RowMapper<>() {
        @Override
        public UserMiddle mapRow(@SuppressWarnings("NullableProblems") ResultSet rs, int rowNum) throws SQLException {
            return new UserMiddle(rs);
        }
    };

    public UserDaoImpl(@Autowired DataSource dataWizardsDS){
        this.jdbcTemplate = new JdbcTemplate(dataWizardsDS);
    }

    @Override
    public UserMiddle getUser(int userID) throws APIInternalServerErrorException {
        try{
            return jdbcTemplate.queryForObject(GET_USER_BY_USER_ID, userRowMapper, userID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            throw new APIInternalServerErrorException("Failed to get user from DB.", e);
        }
    }

    @Override
    public UserMiddle createUser(UserMiddle middle) {
        if(middle == null){
            throw new APIBadRequestException("Provided user record is null.");
        }
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, middle.getEmail());
                ps.setString(2, middle.getPasswordHash());
                ps.setString(3, middle.getUsername());
                ps.setString(4, middle.getFirstName());
                ps.setString(5, middle.getLastName());
                if(middle.getDateOfBirth() != null) {
                    ps.setDate(6, Date.valueOf(middle.getDateOfBirth()));
                }else{
                    ps.setDate(6, null);
                }
                ps.setString(7, middle.getPhoneNumber());
                ps.setString(8, middle.getProfilePicture());
                ps.setString(9, middle.getAddress());
                ps.setString(10, middle.getRole().toString());
                return ps;
            }, keyHolder);

            if(keyHolder.getKey() == null){
                logger.error("Failed to insert user record. No key generated.");
                throw new APIInternalServerErrorException("Failed to insert user record.");
            }
            return getUser(keyHolder.getKey().intValue());
        } catch (DataAccessException e) {
            logger.error("Failed to insert user record in DB.", e);
            throw new APIInternalServerErrorException("Failed to insert user record in DB.", e);
        }
    }

    @Override
    public UserMiddle updateUser(UserMiddle middle) {
        if(middle == null){
            throw new APIBadRequestException("Provided user record is null.");
        }
        if(middle.getUserID() == 0){
            throw new APIBadRequestException("Provided user record has a zero user ID which is not allowed.");
        }
        try{
            List<Object> args = new ArrayList<>();
            args.add(middle.getEmail());
            args.add(middle.getUsername());
            args.add(middle.getFirstName());
            args.add(middle.getLastName());
            if(middle.getDateOfBirth() != null) {
                args.add(Date.valueOf(middle.getDateOfBirth()));
            }else{
                args.add(null);
            }
            args.add(middle.getPhoneNumber());
            args.add(middle.getProfilePicture());
            args.add(middle.getAddress());
            if(middle.getRole() != null) {
                args.add(middle.getRole().toString());
            }else{
                args.add(null);
            }
            args.add(middle.getUserID());

            int numRowsUpdated = jdbcTemplate.update(UPDATE_USER,args.toArray());
            if(numRowsUpdated == 0){
                logger.error("No user record found to update for user ID: " + middle.getUserID());
                throw new APIInternalServerErrorException("No user record found to update for user ID: " + middle.getUserID());
            }
            return getUser(middle.getUserID());
        } catch (DataAccessException e) {
            logger.error("Failed to update user record in DB.", e);
            throw new APIInternalServerErrorException("Failed to update user record in DB.", e);
        }
    }

    @Override
    public UserMiddle disableUser(int userID) {
        int numRowsUpdated;
        try{
            numRowsUpdated = jdbcTemplate.update(DISABLE_USER, userID);
        } catch (DataAccessException e) {
            throw new APIInternalServerErrorException("Failed to disable user in DB.", e);
        }
        if(numRowsUpdated == 0){
            throw new APIInternalServerErrorException("No user record found to disable for user ID: " + userID);
        }
        return getUser(userID);
    }
}
