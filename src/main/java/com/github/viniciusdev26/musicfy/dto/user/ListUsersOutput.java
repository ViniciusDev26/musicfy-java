package com.github.viniciusdev26.musicfy.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListUsersOutput {
    private List<UserDto> users;
    private Integer totalCount;
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
}
