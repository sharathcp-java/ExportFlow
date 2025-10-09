package com.saas.exportflow.export_flow.controller;

import com.saas.exportflow.export_flow.dto.WelcomeRequestDTO;
import com.saas.exportflow.export_flow.model.ExportRequest;
import com.saas.exportflow.export_flow.model.UserInfo;
import com.saas.exportflow.export_flow.service.ExportflowService;
import com.saas.exportflow.export_flow.service.JwtService;
import com.saas.exportflow.export_flow.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final JwtService jwtService;
    private final UserInfoService userInfoService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private ExportflowService exportflowService;

    @PostMapping("/register")
    public String register(@RequestBody UserInfo userInfo) {
        log.info("New member registered :"+userInfo.getName());
        return userInfoService.addUser(userInfo);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserInfo request) {
        log.info("Member Login token generation started for user :"+request.getName());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));
        UserDetails user = userInfoService.loadUserByUsername(request.getName());
        log.info("Member Login succesfull : "+request.getName());
        return jwtService.generateToken(user.getUsername(), user.getAuthorities().iterator().next().getAuthority());
    }

    @GetMapping("/welcome")
    public ResponseEntity<WelcomeRequestDTO> getAllDataforUser(Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        WelcomeRequestDTO welcomeRequestDTO= exportflowService.getallExportRequestByUser(userDetails.getUsername());
        return ResponseEntity.ok(welcomeRequestDTO);
    }
}
