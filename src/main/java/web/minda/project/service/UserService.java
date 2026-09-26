package web.minda.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import web.minda.project.dto.UserCreatedEvent;
import web.minda.project.entity.LoginMaster;
import web.minda.project.repositories.LoginMasterRepository;

@Service
public class UserService {

    @Autowired
    private LoginMasterRepository loginMasterRepository;
    
    @Autowired
    private UserEventProducer userEventProducer;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    

    public LoginMaster createUser(LoginMaster user) {

        if (loginMasterRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User already exists");
        }
        
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        LoginMaster savedUser = loginMasterRepository.save(user);
        
        // After successful DB insertion
        UserCreatedEvent event = new UserCreatedEvent(
                savedUser.getLoginId(),
                savedUser.getFirstName(),
                savedUser.getContact(),
                savedUser.getEmail()
        );

        userEventProducer.publishUserCreated(event);


        return savedUser;
    }
}