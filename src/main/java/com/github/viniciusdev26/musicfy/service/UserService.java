package com.github.viniciusdev26.musicfy.service;

import com.github.viniciusdev26.musicfy.dto.user.*;
import com.github.viniciusdev26.musicfy.entity.User;
import com.github.viniciusdev26.musicfy.exception.BusinessException;
import com.github.viniciusdev26.musicfy.exception.NotFoundException;
import com.github.viniciusdev26.musicfy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public CreateUserOutput createUser(CreateUserInput input) {
        if (userRepository.existsByEmail(input.getEmail())) {
            throw new BusinessException("Email already exists: " + input.getEmail());
        }

        User user = new User(input.getName(), input.getBirthDate(), input.getEmail());
        user = userRepository.save(user);

        return new CreateUserOutput(
                user.getId(),
                user.getName(),
                user.getBirthDate(),
                user.getEmail(),
                user.getAge()
        );
    }

    @Transactional(readOnly = true)
    public ListUsersOutput listUsers(ListUsersInput input) {
        List<UserDto> users;
        int totalCount;
        Integer totalPages = null;

        if (input.getPage() != null && input.getPageSize() != null) {
            Pageable pageable = PageRequest.of(input.getPage(), input.getPageSize());
            Page<User> page = userRepository.findAll(pageable);
            users = page.getContent().stream()
                    .map(this::toUserDto)
                    .collect(Collectors.toList());
            totalCount = (int) page.getTotalElements();
            totalPages = page.getTotalPages();
        } else {
            List<User> allUsers = userRepository.findAll();
            users = allUsers.stream()
                    .map(this::toUserDto)
                    .collect(Collectors.toList());
            totalCount = allUsers.size();
        }

        return new ListUsersOutput(users, totalCount, input.getPage(), input.getPageSize(), totalPages);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User", id));
    }

    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User", id);
        }
        userRepository.deleteById(id);
    }

    private UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getBirthDate(),
                user.getEmail(),
                user.getAge()
        );
    }
}
