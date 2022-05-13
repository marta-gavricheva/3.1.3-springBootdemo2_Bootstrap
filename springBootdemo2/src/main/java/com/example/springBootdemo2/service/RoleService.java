package com.example.springBootdemo2.service;

import com.example.springBootdemo2.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role getByName(String roleUser);

    void add(Role role);

    Role getById(int id);
}
