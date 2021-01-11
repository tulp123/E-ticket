package com.bzcom.eticket.controller;

import com.bzcom.eticket.jwt.JwtUtils;
import com.bzcom.eticket.model.Member;
import com.bzcom.eticket.payloadLogin.request.LoginRequest;
import com.bzcom.eticket.payloadLogin.response.JwtResponse;
import com.bzcom.eticket.repository.MemberRepository;
import com.bzcom.eticket.repository.RoleRepository;
import com.bzcom.eticket.service.MemberService;
import com.bzcom.eticket.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateMember(@RequestBody LoginRequest loginRequest) {
        String user = loginRequest.getUsername();
        String pass = loginRequest.getPassword();
        UsernamePasswordAuthenticationToken tmp = new UsernamePasswordAuthenticationToken(user,pass);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt , (long) userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean checkAdmin(){
        return true;
    }

}
