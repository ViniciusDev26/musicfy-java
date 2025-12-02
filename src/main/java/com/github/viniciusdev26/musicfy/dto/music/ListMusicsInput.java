package com.github.viniciusdev26.musicfy.dto.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListMusicsInput {
    private Integer page;
    private Integer pageSize;
    private String artist;
    private String searchTerm;
    private Long playlistId;
}
