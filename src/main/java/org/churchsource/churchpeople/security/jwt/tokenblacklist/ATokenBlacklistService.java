package org.churchsource.churchpeople.security.jwt.tokenblacklist;

import org.churchsource.churchpeople.security.jwt.JwtTokenService;
import org.churchsource.churchpeople.user.CPUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public abstract class ATokenBlacklistService implements IJwtTokenBlacklistService, ApplicationListener<BlacklistTokenEvent> {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    public void onApplicationEvent(BlacklistTokenEvent blackListTokenEvent) {
        String username = jwtTokenService.getUsernameFromToken(blackListTokenEvent.getToken());
        if(username != null && !"".equals(username)) {
            this.blacklistToken(username, blackListTokenEvent.getToken());
        }
    }
}
