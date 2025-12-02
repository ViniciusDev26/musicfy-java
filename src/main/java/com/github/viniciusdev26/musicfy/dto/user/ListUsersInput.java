package com.github.viniciusdev26.musicfy.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListUsersInput {
    private Integer page;
    private Integer pageSize;
}
