package org.kamil.schedule.service;


import org.kamil.schedule.model.User;
import org.kamil.schedule.model.UserRole;
import org.kamil.schedule.repository.UserRepository;
import org.kamil.schedule.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Long findRoleIdByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        UserRole userRole = userRoleRepository.findAllByUser(user).get(0);

        Long roleId = userRole.getRole().getId();

        return roleId;

    }


}