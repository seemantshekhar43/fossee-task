package com.seemantshekhar.tblog.repositories;

import com.seemantshekhar.tblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

//User Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Method to find user by Username
    User findByUsername(String username);
}
