package org.iesvdm.restapiecommercejpa.service;

import org.iesvdm.restapiecommercejpa.model.User;
import org.iesvdm.restapiecommercejpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // al usar hibernate heredando en userRepository tenemos acceso a métodos ya definidos y podemos usarlos en service
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
