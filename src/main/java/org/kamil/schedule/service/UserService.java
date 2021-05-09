package org.kamil.schedule.service;



import org.kamil.schedule.model.User;

public interface UserService {
    public User findById(Long id);
    public User findUserByUsername(String username);
    public User findUserByEmail(String email);
    public void save(User user);

    Long findRoleIdByUsername(String username);

}