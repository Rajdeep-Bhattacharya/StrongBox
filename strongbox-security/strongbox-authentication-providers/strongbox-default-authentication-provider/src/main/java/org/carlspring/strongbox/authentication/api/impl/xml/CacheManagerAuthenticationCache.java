package org.carlspring.strongbox.authentication.api.impl.xml;

import org.carlspring.strongbox.data.CacheName;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class CacheManagerAuthenticationCache implements AuthenticationCache
{
    private StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder();

    @Cacheable(cacheNames = CacheName.User.AUTHENTICATIONS, key = "#p0", unless = "true")
    public UsernamePasswordAuthenticationToken getAuthenticationToken(String userName)
    {
        return null;
    }

    @Cacheable(cacheNames = CacheName.User.AUTHENTICATIONS, key = "#p0.principal")
    public UsernamePasswordAuthenticationToken putAuthenticationToken(UsernamePasswordAuthenticationToken authentication)
    {
        return createCachableAuthentication(authentication);
    }

    public UsernamePasswordAuthenticationToken createCachableAuthentication(UsernamePasswordAuthenticationToken authentication)
    {
        String encodedPassword = encode(authentication.getCredentials().toString());

        return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), encodedPassword);
    }

    public String encode(CharSequence rawPassword)
    {
        return standardPasswordEncoder.encode(rawPassword);
    }

    public boolean matches(CharSequence rawPassword,
                           String encodedPassword)
    {
        return standardPasswordEncoder.matches(rawPassword, encodedPassword);
    }

}
