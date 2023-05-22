package com.yuriybishel.tasktracker.controller;

import com.yuriybishel.tasktracker.model.User;
import com.yuriybishel.tasktracker.repository.UserRepository;
import com.yuriybishel.tasktracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user";
    }


    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        // Ищем пользователя в базе данных по id
        Optional<User> existingUser = userRepository.findById(id);

        // Если пользователь с указанным id не найден, возвращаем null
        if (!existingUser.isPresent()) {
            return null;
        }

        // Обновляем данные существующего пользователя и сохраняем его
        User updatedUser = existingUser.get();
        updatedUser.setUsername(User.getUsername()); // предполагая, что у вас есть поле name в классе User
        // Делаем это для всех полей, которые вы хотите обновить

        userRepository.save(updatedUser);

        // Возвращаем обновленного пользователя
        return updatedUser;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}