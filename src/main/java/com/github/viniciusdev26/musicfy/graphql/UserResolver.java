package com.github.viniciusdev26.musicfy.graphql;

import com.github.viniciusdev26.musicfy.dto.user.CreateUserInput;
import com.github.viniciusdev26.musicfy.dto.user.CreateUserOutput;
import com.github.viniciusdev26.musicfy.dto.user.ListUsersInput;
import com.github.viniciusdev26.musicfy.dto.user.ListUsersOutput;
import com.github.viniciusdev26.musicfy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserResolver {

    private final UserService userService;

    @QueryMapping(name = "Users")
    public ListUsersOutput users(@Argument("input") ListUsersInput input) {
        return userService.listUsers(input);
    }

    @MutationMapping(name = "CreateUser")
    public CreateUserOutput createUser(@Argument("input") CreateUserInput input) {
        return userService.createUser(input);
    }
}
