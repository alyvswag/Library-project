package com.example.libraryproject.security.base;

public interface TokenGenerator <T> {

    String generate(T obj);

}
