package com.seemantshekhar.tblog.services;

import com.seemantshekhar.tblog.models.User;
import com.seemantshekhar.tblog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    /**
     * @param s username
     * @return UserDetails if exists
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = repository.findByUsername(s);
        System.out.println(s + " " + user);

        if (user == null) {
            throw new UsernameNotFoundException("Bad Credentials"); // if no user with such details found
        }

        List<GrantedAuthority> authorities = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()); // list of authorities

        return new org.springframework.security.core.userdetails
                .User(user.getUsername(), user.getPassword(), authorities);

    }
}
