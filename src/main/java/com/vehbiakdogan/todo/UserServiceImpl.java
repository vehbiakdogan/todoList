package com.vehbiakdogan.todo;

import com.vehbiakdogan.todo.model.User;
import com.vehbiakdogan.todo.repository.UserRepository;
import com.vehbiakdogan.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void saveUser(User u) {

        userRepository.save(u);
    }

    @Override
    public User getUser(Long id) {
        Optional<User> optionalUser;
        try {
            optionalUser = userRepository.findById(id);
        } catch (Exception e) {
            optionalUser = null;
        }

        if (!optionalUser.isPresent()) {
            return null;
        } else {
            return optionalUser.get();
        }

    }

    // username password by get user details
    @Override
    public User getUser(String username, String password) {
        User optionalUser;
        try {
            optionalUser = userRepository.usernamePasswordQuery(username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            optionalUser = null;
        }

        if (optionalUser == null) {
            return null;
        } else {
            return optionalUser;
        }

    }

    // username control
    @Override
    public boolean usernameControl(String username) {

        System.out.println("user sayısı: "+userRepository.usernameControl(username));
        return userRepository.usernameControl(username) > 0;

    }


}
