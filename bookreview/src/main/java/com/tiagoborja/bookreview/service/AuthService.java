package com.tiagoborja.bookreview.service;

import com.tiagoborja.bookreview.model.dto.AccessDTO;
import com.tiagoborja.bookreview.model.dto.AuthenticationDTO;
import com.tiagoborja.bookreview.model.entity.UserDetailsImpl;
import com.tiagoborja.bookreview.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public AccessDTO login(AuthenticationDTO authDTO) {
        try {
            UsernamePasswordAuthenticationToken userAuth =
                    new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword());

            Authentication authentication = authenticationManager.authenticate(userAuth);
            UserDetailsImpl userAuthenticate = (UserDetailsImpl) authentication.getPrincipal();

            String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);


            return new AccessDTO(token);
        } catch (BadCredentialsException e) {

            //TODO Invalid credentials
            throw new RuntimeException(e.getMessage());
        }
    }
}
