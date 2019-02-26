package com.vehbiakdogan.todo;

import com.vehbiakdogan.todo.model.User;
import com.vehbiakdogan.todo.repository.UserRepository;
import com.vehbiakdogan.todo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceImplIntegrationTest {

    @TestConfiguration
    static  public class UserServiceImplTestContextConfiguration {
       @Bean
        public UserServiceImpl userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;


    @Before
    public void setUp() {
        User  user = new User();
        user.setUsername("vehbiakdogan");
        user.setPassword("12345");
        Mockito.when(userRepository.usernamePasswordQuery(user.getUsername(),user.getPassword()))
                .thenReturn(user);
    }

    @Test
    public void userLoginShouldBeFound() {
        String username = "alex";
        String password = "12345";
        User found = userService.getUser(username,password);

        assertThat(found.getUsername())
                .isEqualTo(username);
    }

}
