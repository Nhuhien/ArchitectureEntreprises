package com.polytech.persistence;

import com.polytech.services.AppUser;

public interface UserRepository {
    void save(AppUser user);
    AppUser findUserByUserName(String username);
}
