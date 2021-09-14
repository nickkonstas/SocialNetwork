package com.udemy.socialnetwork.service;

import com.udemy.socialnetwork.model.entity.AppUser;
import com.udemy.socialnetwork.model.entity.TokenType;
import com.udemy.socialnetwork.model.entity.VerificationToken;
import com.udemy.socialnetwork.model.repository.AppUserDao;
import com.udemy.socialnetwork.model.repository.VerificationTokenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppUserService implements UserDetailsService {
    @Autowired
    private AppUserDao appUserDao;

    @Autowired
    private VerificationTokenDao verificationTokenDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(AppUser user) {
        user.setRole("ROLE_USER");

        //Now the encryption happens in the AppUser model with the plain password "hack"
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        appUserDao.save(user);
    }

    public void save(AppUser user) {
        appUserDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = appUserDao.findByEmail(email);

        if(user == null) {
            return null;
        }
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());

        String password = user.getPassword();
        Boolean enabled = user.getEnabled();
        return new User(email, password, enabled,true,true,true, authorities);
    }

    public String createEmailVerificationToken(AppUser user) {
        VerificationToken token = new VerificationToken(UUID.randomUUID().toString(), user, TokenType.REGISTRATION);
        verificationTokenDao.save(token);
        return token.getToken();
    }

    public VerificationToken getVerificationToken(String token) {
        return verificationTokenDao.findByToken(token);
    }

    public void deleteToken(VerificationToken token) {
        verificationTokenDao.delete(token);
    }

    public AppUser get(String email) {
        return appUserDao.findByEmail(email);
    }

    public AppUser get(Long id) {
        return appUserDao.getAppUserById(id);
    }
}
