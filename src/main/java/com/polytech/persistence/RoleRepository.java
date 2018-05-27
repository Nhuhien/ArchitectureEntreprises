package com.polytech.persistence;

import java.util.List;

public interface RoleRepository {
    public List<String> getRoleNames(Long userId);
}
