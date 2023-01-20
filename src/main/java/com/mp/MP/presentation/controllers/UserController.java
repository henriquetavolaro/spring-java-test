package com.mp.MP.presentation.controllers;
import com.mp.MP.domain.models.User;
import com.mp.MP.domain.dto.UserJson;
import com.mp.MP.infra.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        var response = service.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable String email) {
        var response =  service.findByEmail(email);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid UserJson user, UriComponentsBuilder uriComponentsBuilder) {
        var newUser = new User(user);

        var response = service.save(newUser);

        var uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(newUser.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserJson(newUser));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity delete(@PathVariable String email) {
        User user = service.findByEmail(email);
        service.delete(user);

        return ResponseEntity.noContent().build();
    }
}
