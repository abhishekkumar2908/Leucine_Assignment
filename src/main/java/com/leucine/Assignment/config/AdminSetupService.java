package com.leucine.Assignment.config;


import com.leucine.Assignment.UserRole;
import com.leucine.Assignment.dao.Admin;
import com.leucine.Assignment.dao.User;
import com.leucine.Assignment.dao.Role;
import com.leucine.Assignment.repository.AdminRepository;
import com.leucine.Assignment.repository.RoleRepository;
import com.leucine.Assignment.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
//        Admin userAdmin = adminRepository.findByUser_Username("admin");
        if (!isAdminExists) {

            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin1234"));
            user.setName("Admin User");
            user.setEmail("admin@example.com");
            user.setPhone("123-456-7890");
            user.setRole(UserRole.ADMINISTRATOR);
            user = userRepository.save(user);



            Admin admin = new Admin();
            admin.setUser(user);
            admin.setPhoto("https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50");
            admin.setDepartmentId(1234L);
            adminRepository.save(admin);
        }
        else {
            System.out.println("Admin already exists ");

        }
    }
}
