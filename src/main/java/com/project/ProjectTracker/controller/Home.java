package com.project.ProjectTracker.controller;

import com.project.ProjectTracker.helper.JwtUtil;
import com.project.ProjectTracker.models.AuthenticationRequest;
import com.project.ProjectTracker.models.AuthenticationResponse;
import com.project.ProjectTracker.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*")
public class Home {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @CrossOrigin(origins="*")
    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    String welcome()
    {
        return "Yes ";
    }

    @GetMapping(value = "/validatecookie")
    boolean validateCookie(String jwt)
    {
        String username=jwtTokenUtil.extractUsername(jwt);
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.validateToken(jwt,userDetails);
    }

//    @CrossOrigin(origins="*")
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect Username or Password", e);
        }
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
