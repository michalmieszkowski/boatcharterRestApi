package com.boatcharter.security;

import com.boatcharter.users.Users;
import com.boatcharter.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserPrincipalDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Users user =  this.usersRepository.findByLogin(login);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        System.out.println(user);
        return userPrincipal;
    }
}
