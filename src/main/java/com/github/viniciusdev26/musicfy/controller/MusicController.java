package com.github.viniciusdev26.musicfy.controller;

import com.github.viniciusdev26.musicfy.dto.music.CreateMusicInput;
import com.github.viniciusdev26.musicfy.dto.music.CreateMusicOutput;
import com.github.viniciusdev26.musicfy.dto.music.ListMusicsInput;
import com.github.viniciusdev26.musicfy.dto.music.ListMusicsOutput;
import com.github.viniciusdev26.musicfy.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/musics")
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;

    @GetMapping
    public ResponseEntity<ListMusicsOutput> getMusics(
            @RequestParam(required = false) Long playlistId,
            @RequestParam(required = false) String artist,
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize
    ) {
        ListMusicsInput input = new ListMusicsInput(page, pageSize, artist, searchTerm, playlistId);
        ListMusicsOutput output = musicService.listMusics(input);
        return ResponseEntity.ok(output);
    }

    @PostMapping
    public ResponseEntity<CreateMusicOutput> createMusic(@RequestBody CreateMusicInput input) {
        CreateMusicOutput output = musicService.createMusic(input);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(output.getId())
                .toUri();

        return ResponseEntity.created(location).body(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusic(@PathVariable Long id) {
        musicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
