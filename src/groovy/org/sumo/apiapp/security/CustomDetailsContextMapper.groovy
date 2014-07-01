package org.sumo.apiapp.security

import grails.plugin.springsecurity.SpringSecurityUtils
import org.springframework.ldap.core.DirContextAdapter
import org.springframework.ldap.core.DirContextOperations
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper

/**
 * Created by schinth on 2/7/14.
 */

class CustomDetailsContextMapper implements UserDetailsContextMapper {
    static final GrantedAuthority NO_ROLE = new SimpleGrantedAuthority(SpringSecurityUtils.NO_ROLE)

    private Map groupToRoleMap

    public void setGroupToRoleMap(Map groupToRoleMap) {
        this.groupToRoleMap = groupToRoleMap;
    }

    UserDetails mapUserFromContext(DirContextOperations ctx, String username,
                                   Collection authorities) {

        authorities = authorities.collect {
            groupToRoleMap[it.authority]? new SimpleGrantedAuthority(groupToRoleMap[it.authority]) : null
        } - null

        return new CustomUserDetails(
                username, "",  true, true, true, true,
                authorities ?: [NO_ROLE], 12,
                true, ctx.getStringAttribute("displayName"),
                ctx.getStringAttribute("title") ,
                ctx.getStringAttribute("mail"),
                ctx.getStringAttribute("departmentNumber"),
                ctx.getStringAttribute("postalCode"),
                ctx.getStringAttribute("uid")
        )
    }

    void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
        throw new IllegalStateException("Only retrieving data from AD is currently supported")
    }
}

