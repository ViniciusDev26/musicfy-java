package com.github.viniciusdev26.musicfy.dto.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMusicInput {
    private String name;
    private String artist;
    private String audioUrl;
}
