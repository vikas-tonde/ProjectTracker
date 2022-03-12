package com.project.ProjectTracker.controller;

import com.project.ProjectTracker.Dao.UserRepository;
import com.project.ProjectTracker.helper.JwtUtil;
import com.project.ProjectTracker.models.AuthenticationRequest;
import com.project.ProjectTracker.models.AuthenticationResponse;
import com.project.ProjectTracker.models.OtpRequest;
import com.project.ProjectTracker.models.UsernameModel;
import com.project.ProjectTracker.service.CustomUserDetailsService;
import com.project.ProjectTracker.service.ForgetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    ForgetPasswordService forgetPasswordService;

    @CrossOrigin(origins="*")
    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    String welcome()
    {
        return "Yes ";
    }

    @GetMapping(value = "/validatecookie")
    ResponseEntity<?> validateCookie(@RequestHeader(value = "Authorization") String jwt)
    {
//        jwt = jwt.substring(7);
//        String username=jwtTokenUtil.extractUsername(jwt);
//        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
//        Optional<User> user = userRepository.findByUsername(username);
//
//        user.orElseThrow(()-> new UsernameNotFoundException("Not Found: "+ username));
//
//        final UserDetails userDetails=user.map(CustomUserDetails::new).get();
//        System.out.println(jwt);
//        return jwtTokenUtil.validateToken(jwt,userDetails);
        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/forgot")
    public ResponseEntity<?> forgotPassword(@RequestBody UsernameModel user)
    {
        if(forgetPasswordService.sendMail(user.getUsername()))
        {
            return ResponseEntity.ok().body(true);
        }
        else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }


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

    @PostMapping(value = "/verifyotp")
    public ResponseEntity <?> otpVerification(@RequestBody OtpRequest otpRequest){
        if(forgetPasswordService.verifyOtp(otpRequest.getOtp(), otpRequest.getUsername())){
            return ResponseEntity.ok().body(true);
        }else{
            return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
        }

    }

//    @PostMapping(value = "/resetpass")
//    public ResponseEntity<?> resetPassword(@RequestBody AuthenticationRequest request)
//    {
//
//    }

}
