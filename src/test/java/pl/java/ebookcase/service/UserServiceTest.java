//package pl.java.ebookcase.service;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.mockito.internal.verification.VerificationModeFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import pl.java.ebookcase.model.User;
//import pl.java.ebookcase.repository.UserRepository;
//
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//public class UserServiceTest {
//
//    @Configuration
//    static class UserServiceTestContextConfiguration {
//        @Bean
//        public UserService userService() {
//            return new UserService(userRepository(), bCryptPasswordEncoder());
//        }
//        @Bean
//        public UserRepository userRepository() {
//            return Mockito.mock(UserRepository.class);
//        }
//        @Bean
//        public BCryptPasswordEncoder bCryptPasswordEncoder() {
//            return Mockito.mock(BCryptPasswordEncoder.class);
//        }
//    }
//
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Before
//    public void setup() {
//        User user = new User( "log", "name", "surname", "passw", "passw", "mail");
//        user.setId(1L);
//        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
//        Mockito.when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("encoded");
//        Mockito.when(userRepository.save(user)).thenReturn(user);
//        Mockito.when(userRepository.findByLogin(user.getLogin())).thenReturn(user);
//    }
//
//    @Test()
//    public void getUserByIdTest() {
//        assertEquals(Long.valueOf(1L), userService.getUserById(1L).getId());
//        assertEquals("log", userService.getUserById(1L).getLogin());
//        assertEquals("name", userService.getUserById(1L).getName());
//        assertEquals("surname", userService.getUserById(1L).getSurname());
//        assertEquals("passw", userService.getUserById(1L).getPassword());
//        assertEquals("passw", userService.getUserById(1L).getConfirmPassword());
//        assertEquals("mail", userService.getUserById(1L).getEmail());
//        Mockito.verify(userRepository, VerificationModeFactory.times(7)).findById(Mockito.any());
//    }
//
//    @Test()
//    public void saveTest() {
//        User user = userService.getUserById(1L);
//        Mockito.verify(userRepository, VerificationModeFactory.times(1)).findById(Mockito.any());
//        User userSave = userService.save(user);
//        Mockito.verify(userRepository, VerificationModeFactory.times(1)).save(Mockito.any());
//        assertEquals(user.getId(),userSave.getId());
//        assertEquals(user.getLogin(),userSave.getLogin());
//        assertEquals(user.getName(),userSave.getName());
//        assertEquals(user.getSurname(),userSave.getSurname());
//        assertEquals(user.getPassword(),userSave.getPassword());
//        assertEquals(user.getConfirmPassword(),userSave.getConfirmPassword());
//        assertEquals(user.getEmail(),userSave.getEmail());
//        assertNotNull(userSave.getPasswordEncrypted());
//    }
//
//    @Test()
//    public void getUserByLoginTest() {
//        assertEquals(Long.valueOf(1L), userService.getUserByLogin("log").getId());
//        assertEquals("log", userService.getUserByLogin("log").getLogin());
//        assertEquals("name", userService.getUserByLogin("log").getName());
//        assertEquals("surname", userService.getUserByLogin("log").getSurname());
//        assertEquals("passw", userService.getUserByLogin("log").getPassword());
//        assertEquals("passw", userService.getUserByLogin("log").getConfirmPassword());
//        assertEquals("mail", userService.getUserByLogin("log").getEmail());
//        Mockito.verify(userRepository, VerificationModeFactory.times(7)).findByLogin(Mockito.any());
//    }
//
//    @After
//    public void verify() {
//        Mockito.reset(userRepository);
//    }
//}