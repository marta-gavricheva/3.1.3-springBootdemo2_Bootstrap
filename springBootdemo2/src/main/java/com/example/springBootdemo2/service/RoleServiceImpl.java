package com.example.springBootdemo2.service;

import com.example.springBootdemo2.dao.RoleDao;
import com.example.springBootdemo2.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {


    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role getByName(String roleUser) {
        return roleDao.getByName(roleUser);
    }

    @Override
    public void add(Role role) {
        roleDao.add(role);
    }

    @Override
    public Role getById(int id) {
        return roleDao.getById(id);
    }
}
