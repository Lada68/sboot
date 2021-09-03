package com.example.sboot.service;

import com.example.sboot.model.Role;
import com.example.sboot.model.User;
import com.example.sboot.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = (User) userRepository.findByUsername(s);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword()
                , mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());

    }


    @Override
    public Object findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Object findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override

    public void save(User user) {

        userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(userRepository.findById(id));
    }

    public void updateUser(int id, User user) {
        User userForDB = userRepository.findById(id);
        userForDB.setName(user.getName());
        userForDB.setSurname(user.getSurname());
        userForDB.setAge(user.getAge());
        userRepository.save(userForDB);
    }
}
