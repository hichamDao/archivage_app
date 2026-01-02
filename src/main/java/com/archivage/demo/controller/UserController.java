package com.archivage.demo.controller;

import com.archivage.demo.model.User;
import com.archivage.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // Rechercher un utilisateur par ID
    @GetMapping("/find/{id}")
    public Optional<User> findById(@PathVariable String id) {
        return userService.findById(id);
    }

    // Rechercher un utilisateur par email
    @GetMapping("/findByEmail")
    public Optional<User> findByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        Optional<User> userOpt = userService.findByUsername(username);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur introuvable");
        }

        User user = userOpt.get();

        // ✅ Utilisation de checkPassword
        if (!userService.checkPassword(user, password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect");
        }

        return ResponseEntity.ok(user);
    }


}
