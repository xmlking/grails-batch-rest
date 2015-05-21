package org.sumo.apiapp.security

import grails.plugin.springsecurity.userdetails.GrailsUser
import groovy.transform.ToString
import org.springframework.security.core.GrantedAuthority

/**
 * Created by schinth on 2/7/14.
 */
@ToString(includeNames=true, includeSuper=true)
class CustomUserDetails extends GrailsUser {

    final Boolean ldapAuth
    final String displayName
    final String email
    final String departmentNumber
    final String postalCode
    final String uid

    CustomUserDetails(String username, String password, boolean enabled,
                      boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
                      Collection<GrantedAuthority> authorities,
                      Object id, Boolean ldapAuth,
                      String displayName, String email,
                      String departmentNumber, String postalCode, String uid) {

        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities, id)

        this.ldapAuth = ldapAuth
        this.displayName = displayName
        this.email = email
        this.departmentNumber = departmentNumber
        this.postalCode = postalCode
        this.uid = uid
    }
}
