package com.github.viniciusdev26.musicfy.graphql;

import com.github.viniciusdev26.musicfy.dto.music.CreateMusicInput;
import com.github.viniciusdev26.musicfy.dto.music.CreateMusicOutput;
import com.github.viniciusdev26.musicfy.dto.music.ListMusicsInput;
import com.github.viniciusdev26.musicfy.dto.music.ListMusicsOutput;
import com.github.viniciusdev26.musicfy.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MusicResolver {

    private final MusicService musicService;

    @QueryMapping(name = "Musics")
    public ListMusicsOutput musics(@Argument("input") ListMusicsInput input) {
        return musicService.listMusics(input);
    }

    @MutationMapping(name = "CreateMusic")
    public CreateMusicOutput createMusic(@Argument("input") CreateMusicInput input) {
        return musicService.createMusic(input);
    }
}
