package org.sumo.apiapp.security

import grails.plugin.springsecurity.userdetails.GormUserDetailsService
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetailsService extends GormUserDetailsService {

    protected UserDetails createUserDetails(user, Collection<GrantedAuthority> authorities) {

        return new CustomUserDetails(
                user.username, user.password, user.enabled,
                !user.accountExpired, !user.passwordExpired, !user.accountLocked,
                authorities ?: [NO_ROLE], user.id,
                false, "${user.lastName}, ${user.firstName}", user.email,
                "NO_DEP", "55555", user.username
        )

    }
}
