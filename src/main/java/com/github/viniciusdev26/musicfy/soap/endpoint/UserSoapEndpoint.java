package com.github.viniciusdev26.musicfy.soap.endpoint;

import com.github.viniciusdev26.musicfy.dto.user.CreateUserInput;
import com.github.viniciusdev26.musicfy.dto.user.ListUsersInput;
import com.github.viniciusdev26.musicfy.exception.BusinessException;
import com.github.viniciusdev26.musicfy.service.UserService;
import com.github.viniciusdev26.musicfy.soap.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class UserSoapEndpoint {

    private static final String NAMESPACE_URI = "http://musicfy.viniciusdev26.github.com/soap/users";

    private final UserService userService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetUsersRequest")
    @ResponsePayload
    public GetUsersResponse getUsers(@RequestPayload GetUsersRequest request) {
        var input = new ListUsersInput(request.getPage(), request.getPageSize());
        var result = userService.listUsers(input);

        var response = new GetUsersResponse();
        result.getUsers().forEach(u -> {
            var userDto = new UserSoapDto();
            userDto.setId(u.getId());
            userDto.setName(u.getName());
            userDto.setBirthDate(u.getBirthDate());
            userDto.setEmail(u.getEmail());
            userDto.setAge(u.getAge());
            response.getUsers().add(userDto);
        });
        response.setTotalCount(result.getTotalCount());
        response.setPage(result.getPage());
        response.setPageSize(result.getPageSize());
        response.setTotalPages(result.getTotalPages());

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateUserRequest")
    @ResponsePayload
    public UserSoapDto createUser(@RequestPayload CreateUserRequest request) {
        try {
            var input = new CreateUserInput(
                    request.getName(),
                    request.getBirthDate(),
                    request.getEmail()
            );

            var result = userService.createUser(input);

            var response = new UserSoapDto();
            response.setId(result.getId());
            response.setName(result.getName());
            response.setBirthDate(result.getBirthDate());
            response.setEmail(result.getEmail());
            response.setAge(result.getAge());

            return response;
        } catch (BusinessException ex) {
            throw new RuntimeException("Business Error: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Validation Error: " + ex.getMessage());
        }
    }
}
