package com.github.viniciusdev26.musicfy.grpc.service;

import com.github.viniciusdev26.musicfy.dto.user.CreateUserInput;
import com.github.viniciusdev26.musicfy.dto.user.CreateUserOutput;
import com.github.viniciusdev26.musicfy.dto.user.ListUsersInput;
import com.github.viniciusdev26.musicfy.dto.user.ListUsersOutput;
import com.github.viniciusdev26.musicfy.entity.User;
import com.github.viniciusdev26.musicfy.grpc.user.*;
import com.github.viniciusdev26.musicfy.service.UserService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.time.format.DateTimeFormatter;

@GrpcService
@RequiredArgsConstructor
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    private final UserService userService;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<CreateUserResponse> responseObserver) {
        try {
            CreateUserInput input = new CreateUserInput(
                    request.getName(),
                    null,
                    request.getEmail()
            );

            CreateUserOutput output = userService.createUser(input);

            CreateUserResponse response = CreateUserResponse.newBuilder()
                    .setId(output.getId())
                    .setName(output.getName())
                    .setEmail(output.getEmail())
                    .setCreatedAt(output.getBirthDate() != null ? output.getBirthDate().toString() : "")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void getUsers(GetUsersRequest request, StreamObserver<GetUsersResponse> responseObserver) {
        try {
            ListUsersInput input = new ListUsersInput(
                    request.getPage(),
                    request.getSize()
            );

            ListUsersOutput output = userService.listUsers(input);

            GetUsersResponse.Builder responseBuilder = GetUsersResponse.newBuilder()
                    .setTotal(output.getTotalCount())
                    .setPage(output.getPage() != null ? output.getPage() : 0)
                    .setSize(output.getPageSize() != null ? output.getPageSize() : 0);

            output.getUsers().forEach(user -> {
                UserDto userDto = UserDto.newBuilder()
                        .setId(user.getId())
                        .setName(user.getName())
                        .setEmail(user.getEmail())
                        .setCreatedAt(user.getBirthDate() != null ? user.getBirthDate().toString() : "")
                        .build();
                responseBuilder.addUsers(userDto);
            });

            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void getUserById(GetUserByIdRequest request, StreamObserver<UserResponse> responseObserver) {
        try {
            User user = userService.findById(request.getId());

            UserResponse response = UserResponse.newBuilder()
                    .setId(user.getId())
                    .setName(user.getName())
                    .setEmail(user.getEmail())
                    .setCreatedAt(user.getBirthDate() != null ? user.getBirthDate().toString() : "")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.NOT_FOUND
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void updateUser(UpdateUserRequest request, StreamObserver<UserResponse> responseObserver) {
        try {
            User user = userService.findById(request.getId());
            user.changeName(request.getName());
            user.changeEmail(request.getEmail());

            UserResponse response = UserResponse.newBuilder()
                    .setId(user.getId())
                    .setName(user.getName())
                    .setEmail(user.getEmail())
                    .setCreatedAt(user.getBirthDate() != null ? user.getBirthDate().toString() : "")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void deleteUser(DeleteUserRequest request, StreamObserver<DeleteUserResponse> responseObserver) {
        try {
            userService.delete(request.getId());

            DeleteUserResponse response = DeleteUserResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("User deleted successfully")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
}
