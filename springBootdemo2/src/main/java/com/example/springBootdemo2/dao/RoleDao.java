package com.example.springBootdemo2.dao;

import com.example.springBootdemo2.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {

    List<Role> findAll();

    Role getByName(String roleUser);

    void add(Role role);

    Role getById(int id);


}
