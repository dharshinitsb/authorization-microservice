package com.cts.spannerdemo.controller;



import com.cts.spannerdemo.configs.JwtRequestFilter;
import com.cts.spannerdemo.configs.JwtTokenUtil;
import com.cts.spannerdemo.exceptions.AuthorizationException;
import com.cts.spannerdemo.model.JwtResponse;
import com.cts.spannerdemo.model.customer;
import com.cts.spannerdemo.repo.CustomerRepository;
import com.cts.spannerdemo.model.JwtRequest;
import com.cts.spannerdemo.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.crypto.password.PasswordEncoder;


@RestController
@CrossOrigin
public class customerController {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

   
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody customer customer) throws Exception{
        userDetailsService.save(customer);
        return new ResponseEntity<String>("Customer Created Successfully!",HttpStatus.CREATED);

    }
    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws AuthorizationException {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    private Authentication  authenticate(String username, String password) throws AuthorizationException {
        try {
            Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return auth;
        } catch (DisabledException e) {
            throw new AuthorizationException("USER_DISABLED");
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new AuthorizationException("INVALID_CREDENTIALS");
        }
    }

}
