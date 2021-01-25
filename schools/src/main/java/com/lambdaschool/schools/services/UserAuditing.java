package com.lambdaschool.schools.services;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAuditing implements AuditorAware<String>
{
    /**
     * The current user
     *
     * @return Optional(String) of current user
     */
    @Override
    public Optional<String> getCurrentAuditor()
    {
        String uname;
        uname = "SYSTEM";
        return Optional.of(uname);
    }
}
