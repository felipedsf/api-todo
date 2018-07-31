package com.felipe.todo.security;

import com.felipe.todo.security.model.JwtAuthenticatorToken;
import com.felipe.todo.security.model.JwtUser;
import com.felipe.todo.security.model.JwtUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private JwtValidator validator;

    public JwtAuthenticationProvider(JwtValidator validator) {
        this.validator = validator;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        JwtAuthenticatorToken jwtAuthenticatorToken = (JwtAuthenticatorToken) usernamePasswordAuthenticationToken;
        String token = jwtAuthenticatorToken.getToken();
        JwtUser jwtUser = validator.validate(token);

        if (jwtUser == null)
            throw new RuntimeException("JWT Token is incorrect.");


        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(jwtUser.getRole());
        return new JwtUserDetails(jwtUser.getId(), jwtUser.getUsername(), token, grantedAuthorities);

    }
}
