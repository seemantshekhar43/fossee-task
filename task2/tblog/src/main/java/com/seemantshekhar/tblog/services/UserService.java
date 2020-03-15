package com.seemantshekhar.tblog.services;

import com.seemantshekhar.tblog.models.User;
import com.seemantshekhar.tblog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//Service class for User
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Method to save user in Database
     *
     * @param user user object
     * @return success if successfull and doExist if user with that username already exist
     */
    public String save(User user) {
        User doExist = userRepository.findByUsername(user.getUsername());

        if (doExist != null) {
            return "exists";
        } else {
            userRepository.save(user);
            return "success";
        }

    }

    //Method to get current logged in user
    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUser = auth.getName();
        return userRepository.findByUsername(loggedUser);
    }

    //Metod to get user by id
    public User get(long id) {
        return userRepository.findById(id).get();
    }

    //Method to delete user by id
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
