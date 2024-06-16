package com.VasilijeISA.ISA2023.service;

import com.VasilijeISA.ISA2023.dto.UpdateUserDto;
import com.VasilijeISA.ISA2023.dto.UserRequestDto;
import com.VasilijeISA.ISA2023.exeption.UserAlreadyExistsException;
import com.VasilijeISA.ISA2023.model.Role;
import com.VasilijeISA.ISA2023.model.User;
import com.VasilijeISA.ISA2023.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;
    @Autowired
    private EmailService emailService;

    public User findByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User activateAccount(String token) {
        Optional<User> optionalUser = userRepository.findByActivationToken(token);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmailConfirmed(true);

            user.setActivationToken("");
            userRepository.save(user);
            return user;
        }
        return null;
    }
    public User sendRegistrationEmail(UserRequestDto userRequest){
        String activationToken = UUID.randomUUID().toString();
        String activationUrl = "http://localhost:8080/activate-account?token=" + activationToken;

        try {
            String subject = "Aktivacija naloga";
            String htmlBody = "<html><body>"
                    + "<h2>Aktivacija naloga</h2>"
                    + "<p>Kliknite na link ispod da biste aktivirali svoj nalog:</p>"
                    + "<p><a href=\"" + activationUrl + "\">Aktiviraj nalog</a></p>"
                    + "</body></html>";

            emailService.sendHtmlEmail(userRequest.getEmail(), subject, htmlBody);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send activation email", e);
        }
        return save (userRequest, activationToken);
    }
    public User save(UserRequestDto userRequest, String
                     activationToken) {
        User existingUser = userRepository.findByEmail(userRequest.getEmail());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("User with email " + userRequest.getEmail() + " already exists");
        }

        User u = new User();
        u.setEmail(userRequest.getEmail());
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setName(userRequest.getName());
        u.setSurname(userRequest.getSurname());
        u.setEnabled(true);
        List<Role> roles = roleService.findByName("ROLE_USER");
        u.setRoles(roles);
        u.setTown(userRequest.getCity());
        u.setCountry(userRequest.getCountry());
        u.setPhoneNumber(userRequest.getPhoneNumber());
        u.setProfession(userRequest.getProfession());
        u.setCompanyInfo(userRequest.getCompanyInfo());
        u.setPenaltyPoints(0);
        u.setActivationToken(activationToken);



        return userRepository.save(u);
    }

    public User update(UpdateUserDto u){
        User user = userRepository.findById(u.getId()).orElse(null);
        user.setPassword(passwordEncoder.encode(u.getPassword()));
        user.setName(u.getName());
        user.setSurname(u.getSurname());
        user.setTown(u.getTown());
        user.setCountry(u.getCountry());
        user.setPhoneNumber(u.getPhoneNumber());
        user.setProfession(u.getProfession());
        user.setCompanyInfo(u.getCompanyInfo());
        userRepository.save(user);
        return user;
    }


    private String town;
    private String country;
    private String phoneNumber;
    private String profession;
    private String companyInfo;

}