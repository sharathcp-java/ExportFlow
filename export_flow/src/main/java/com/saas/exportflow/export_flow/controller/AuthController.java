package com.saas.exportflow.export_flow.controller;

import com.saas.exportflow.export_flow.model.UserInfo;
import com.saas.exportflow.export_flow.service.JwtService;
import com.saas.exportflow.export_flow.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final UserInfoService userInfoService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String register(@RequestBody UserInfo userInfo) {
        return userInfoService.addUser(userInfo);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserInfo request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));
        UserDetails user = userInfoService.loadUserByUsername(request.getName());
        return jwtService.generateToken(user.getUsername(), user.getAuthorities().iterator().next().getAuthority());
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to public endpoint!";
    }
}
