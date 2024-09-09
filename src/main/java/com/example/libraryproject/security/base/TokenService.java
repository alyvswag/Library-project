package com.example.libraryproject.security.base;

import java.util.List;

public interface TokenService<T, V> {
    List<String> generate(T obj);

    V read(String token);

    String getEmail(String token);
}
