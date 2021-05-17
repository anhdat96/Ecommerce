package com.example.demo.controller;

import com.example.demo.constant.ERole;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.repository.IUserRepository;
import com.example.demo.security.UserDetailImpl;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.payload.request.LonginRequest;
import com.example.demo.security.payload.request.SignUpRequest;
import com.example.demo.security.payload.response.JwtResponse;
import com.example.demo.security.payload.response.MessageResponse;
import com.example.demo.service.IUserService;
import com.example.demo.service.dto.UserDTO;
import com.example.demo.service.impl.RoleServiceImpl;
import com.example.demo.service.mapper.IUserMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {
    private final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
    private final IUserMapper iUserMapper;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    IRoleRepository iRoleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    private final IUserService iUserService;

    @Autowired
    JwtUtils jwtUtils;
    /* 1. receive HTTP reuqest , filter */
    /* 2. authenticate */
    /* 3. store authentication data */
    /* 4. generate token */
    /* 5. get user details*/
    /* 6. authorize */
    /* 7. handle exception */

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid LonginRequest loginRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt, "Bearer",
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password ", e);
        }

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid SignUpRequest signUpRequest, Model model) {
        if (userRepository.existsByUserFirstName(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByUserEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        UserDTO userDTO = new UserDTO(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        List<Long> roleIds = new ArrayList<>();

        if (strRoles == null) {
            Role userRole = iRoleRepository.findByName(ERole.USER.toString())
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roleIds.add(userRole.getRoleID());
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = iRoleRepository.findByName(ERole.ADMIN.toString())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roleIds.add(adminRole.getRoleID());

                        break;
                    case "mod":
                        Role modRole = iRoleRepository.findByName(ERole.MODERATOR.toString()).get();

//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        if (!(modRole != null)) {
                            log.info("role is not found !");
                            break;
                        }
                        roleIds.add(modRole.getRoleID());
                        log.info("User registered successfully!");

                        break;
                    default:
                        Role userRole = iRoleRepository.findByName(ERole.USER.toString())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roleIds.add(userRole.getRoleID());
                }
            });
        }

        userDTO.setRoleIds(roleIds);
        User user = iUserMapper.toEntity(userDTO);
        userRepository.save(user);
//        model.addAttribute("user",user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    @GetMapping("/test")
    public UserDTO getusername(@RequestParam String name )
    {
        return iUserService.findusername(name);
    }

}

