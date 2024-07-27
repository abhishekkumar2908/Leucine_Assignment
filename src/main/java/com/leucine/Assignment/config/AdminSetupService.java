package com.leucine.Assignment.config;


import com.leucine.Assignment.enums.UserRole;
import com.leucine.Assignment.dao.User;
import com.leucine.Assignment.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AdminSetupService {

    @Autowired
    private AdminRepository adminRepository;



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    @Transactional
    public void createAdmin() {

        Boolean isAdminExists = adminRepository.existsByUser_Username("admin");
        if (!isAdminExists) {

            User user = User.builder()
                    .username("admin")

                    .password("admin1234")
                    .role(UserRole.ADMIN)
                    .name("Admin User")
                    .phone("123-456-7890")
                    .build();

            user = userRepository.save(user);

        }
        else {
            System.out.println("Admin already exists ");

        }
    }
}
