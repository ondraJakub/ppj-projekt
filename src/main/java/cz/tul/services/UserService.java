package cz.tul.services;

import cz.tul.data.User;
import cz.tul.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void create(User user) {
        userRepository.save(user);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public User getUser(Integer id){
        return userRepository.findOne(id);
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        List<User> authors = StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return authors;
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void deleteUsers() {
        userRepository.deleteAll();
    }
}
