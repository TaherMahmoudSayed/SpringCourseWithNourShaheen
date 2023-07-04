package com.book.booksproject.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware {
    @Override
    public Optional getCurrentAuditor() {
        //should return userName from spring security
        return Optional.of("TEST USER");
    }
}
