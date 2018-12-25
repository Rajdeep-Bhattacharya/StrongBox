package org.carlspring.strongbox.authentication.external.ldap;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.carlspring.strongbox.authentication.api.AuthenticationItemConfigurationManager;
import org.carlspring.strongbox.authentication.api.CustomAuthenticationItemMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class LdapAuthenticationConfigurationManager implements CustomAuthenticationItemMapper<LdapConfiguration>
{

    private static final String MANAGER_PASSWORD = "managerPassword";

    private static final String MANAGER_DN = "managerDn";

    public static final String AUTHENTICATION_ITEM_LDAP = "ldap";

    public static final String USER_DN_PATTERNS = "userDnPatterns";
    public static final String ROLES_MAPPING = "rolesMapping";
    public static final String CONVERT_TO_UPPER_CASE = "convertToUpperCase";
    public static final String ROLE_PREFIX = "rolePrefix";
    public static final String GROUP_ROLE_ATTRIBUTE = "groupRoleAttribute";
    public static final String GROUP_SEARCH_FILTER = "groupSearchFilter";
    public static final String SEARCH_SUBTREE = "searchSubtree";
    public static final String GROUP_SEARCH_BASE = "groupSearchBase";
    public static final String URL = "url";
    public static final String AUTHORITIES = "authorities";

    @Inject
    private AuthenticationItemConfigurationManager authenticationItemConfigurationManager;

    @Override
    public String getConfigurationItemId()
    {
        return AUTHENTICATION_ITEM_LDAP;
    }

    public LdapConfiguration getConfiguration()
    {
        return authenticationItemConfigurationManager.getCustomAuthenticationItem(this);
    }

    public void updateConfiguration(LdapConfiguration configuration)
        throws IOException
    {
        authenticationItemConfigurationManager.putCustomAuthenticationItem(configuration, this);
    }

    public void testConfiguration(String username,
                                  String password,
                                  LdapConfiguration configuration)
        throws IOException
    {
        authenticationItemConfigurationManager.testCustomAuthenticationItem(configuration, this, (c) -> {

            LdapUserDetailsService luds = (LdapUserDetailsService) c.getBean("ldapUserDetailsService");

            UserDetails user = luds.loadUserByUsername(username);
            if (!password.equals(user.getPassword()))
            {
                throw new BadCredentialsException("Credentials don't match.");
            }

            return false;
        });
    }

    public Map<String, Object> mapAuthorities(LdapAuthoritiesConfiguration source)
    {
        Map<String, Object> result = new HashMap<>();

        result.put(GROUP_SEARCH_BASE, source.getGroupSearch().getGroupSearchBase());
        result.put(GROUP_SEARCH_FILTER, source.getGroupSearch().getGroupSearchFilter());

        result.put(SEARCH_SUBTREE, source.isSearchSubtree());
        result.put(GROUP_ROLE_ATTRIBUTE, source.getGroupRoleAttribute());
        result.put(ROLE_PREFIX, source.getRolePrefix());
        result.put(CONVERT_TO_UPPER_CASE, source.isConvertToUpperCase());

        return result;
    }

    @Override
    public Map<String, Object> map(LdapConfiguration source)
    {
        Map<String, Object> result = new HashMap<>();

        result.put(URL, source.getUrl());
        result.put(MANAGER_DN, source.getManagerDn());
        result.put(MANAGER_PASSWORD, source.getManagerPassword());

        result.put(AUTHORITIES, mapAuthorities(source.getAuthoritiesConfiguration()));

        result.put(GROUP_SEARCH_BASE, source.getGroupSearch().getGroupSearchBase());
        result.put(GROUP_SEARCH_FILTER, source.getGroupSearch().getGroupSearchFilter());

        result.put(ROLES_MAPPING,
                   source.getRoleMappingList()
                         .stream()
                         .map(rm -> new HashMap<String, String>()
                         {
                             {
                                 put("ldapRole", rm.getLdapRole());
                                 put("strongboxRole", rm.getStrongboxRole());
                             }
                         })
                         .collect(Collectors.toList()));

        result.put(USER_DN_PATTERNS, source.getUserDnPatternList());

        return result;
    }

    public LdapAuthoritiesConfiguration mapAuthorities(Map<String, Object> source)
    {
        LdapAuthoritiesConfiguration result = new LdapAuthoritiesConfiguration();

        result.setSearchSubtree(Boolean.TRUE.equals(source.get(SEARCH_SUBTREE)));
        result.setGroupRoleAttribute((String) source.get(GROUP_ROLE_ATTRIBUTE));
        result.setRolePrefix((String) source.get(ROLE_PREFIX));
        result.setConvertToUpperCase(Boolean.TRUE.equals(source.get(CONVERT_TO_UPPER_CASE)));

        LdapGroupSearch groupSearch = new LdapGroupSearch();
        groupSearch.setGroupSearchBase((String) source.get(GROUP_SEARCH_BASE));
        groupSearch.setGroupSearchFilter((String) source.get(GROUP_SEARCH_FILTER));
        result.setGroupSearch(groupSearch);

        return result;
    }

    @Override
    public LdapConfiguration map(Map<String, Object> source)
    {
        LdapConfiguration result = new LdapConfiguration();

        result.setUrl((String) source.get(URL));
        result.setManagerDn((String) source.get(MANAGER_DN));
        result.setManagerPassword((String) source.get(MANAGER_PASSWORD));

        LdapGroupSearch groupSearch = new LdapGroupSearch();
        groupSearch.setGroupSearchBase((String) source.get(GROUP_SEARCH_BASE));
        groupSearch.setGroupSearchFilter((String) source.get(GROUP_SEARCH_FILTER));
        result.setGroupSearch(groupSearch);

        result.setAuthoritiesConfiguration(mapAuthorities((Map<String, Object>) source.get(AUTHORITIES)));

        result.setUserDnPatternList((List<String>) source.get(USER_DN_PATTERNS));

        result.setRoleMappingList(((List<Map<String, String>>) source.get(ROLES_MAPPING)).stream()
                                                                                         .map(rm -> new LdapRoleMapping(
                                                                                                 rm.get("ldapRole"),
                                                                                                 rm.get("strongboxRole")))
                                                                                         .collect(Collectors.toList()));

        return result;
    }

}
