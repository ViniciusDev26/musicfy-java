package com.github.viniciusdev26.musicfy.controller;

import com.github.viniciusdev26.musicfy.dto.user.CreateUserInput;
import com.github.viniciusdev26.musicfy.dto.user.CreateUserOutput;
import com.github.viniciusdev26.musicfy.dto.user.ListUsersInput;
import com.github.viniciusdev26.musicfy.dto.user.ListUsersOutput;
import com.github.viniciusdev26.musicfy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ListUsersOutput> getUsers(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize
    ) {
        ListUsersInput input = new ListUsersInput(page, pageSize);
        ListUsersOutput output = userService.listUsers(input);
        return ResponseEntity.ok(output);
    }

    @PostMapping
    public ResponseEntity<CreateUserOutput> createUser(@RequestBody CreateUserInput input) {
        CreateUserOutput output = userService.createUser(input);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(output.getId())
                .toUri();

        return ResponseEntity.created(location).body(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
