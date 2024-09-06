package com.example.libraryproject.security.base;

public interface TokenReader <T> {

    T read(String token);

}
