package com.github.viniciusdev26.musicfy.dto.playlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListPlaylistsInput {
    private Integer page;
    private Integer pageSize;
    private Long userId;
    private Boolean systemOnly;
    private Boolean userOnly;
}
