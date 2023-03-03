package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepository2;

    public User createUser(String username, String password)
    {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName("test");
        user.setLastName("test");
        //blogList of user will be null initially

        userRepository2.save(user);
        return user;
    }

    public void deleteUser(int userId)
    {
        userRepository2.deleteById(userId);
    }

    public User updateUser(Integer id, String password)
    {
        User user = userRepository2.findById(id).get();
        user.setPassword(password);
        userRepository2.save(user);

        return user;
    }
}
