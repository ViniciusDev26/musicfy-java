package com.github.viniciusdev26.musicfy.service;

import com.github.viniciusdev26.musicfy.dto.music.*;
import com.github.viniciusdev26.musicfy.entity.Music;
import com.github.viniciusdev26.musicfy.entity.PlaylistMusic;
import com.github.viniciusdev26.musicfy.exception.NotFoundException;
import com.github.viniciusdev26.musicfy.repository.MusicRepository;
import com.github.viniciusdev26.musicfy.repository.PlaylistMusicRepository;
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
public class MusicService {

    private final MusicRepository musicRepository;
    private final PlaylistMusicRepository playlistMusicRepository;

    @Transactional
    public CreateMusicOutput createMusic(CreateMusicInput input) {
        Music music = new Music(input.getName(), input.getArtist(), input.getAudioUrl());
        music = musicRepository.save(music);

        return new CreateMusicOutput(
                music.getId(),
                music.getName(),
                music.getArtist(),
                music.getAudioUrl()
        );
    }

    @Transactional(readOnly = true)
    public ListMusicsOutput listMusics(ListMusicsInput input) {
        List<MusicDto> musics;
        int totalCount;
        Integer totalPages = null;

        if (input.getPlaylistId() != null) {
            List<PlaylistMusic> playlistMusics = playlistMusicRepository
                    .findByPlaylistIdOrderByOrderAsc(input.getPlaylistId());

            musics = playlistMusics.stream()
                    .map(pm -> {
                        Music music = musicRepository.findById(pm.getMusicId())
                                .orElseThrow(() -> new NotFoundException("Music", pm.getMusicId()));
                        return toMusicDtoWithPlaylistInfo(music, pm.getOrder(), pm.getAddedAt());
                    })
                    .collect(Collectors.toList());
            totalCount = musics.size();
        } else if (input.getArtist() != null) {
            if (input.getPage() != null && input.getPageSize() != null) {
                Pageable pageable = PageRequest.of(input.getPage(), input.getPageSize());
                Page<Music> page = musicRepository.findByArtist(input.getArtist(), pageable);
                musics = page.getContent().stream()
                        .map(this::toMusicDto)
                        .collect(Collectors.toList());
                totalCount = (int) page.getTotalElements();
                totalPages = page.getTotalPages();
            } else {
                List<Music> musicList = musicRepository.findByArtist(input.getArtist());
                musics = musicList.stream()
                        .map(this::toMusicDto)
                        .collect(Collectors.toList());
                totalCount = musicList.size();
            }
        } else if (input.getSearchTerm() != null) {
            if (input.getPage() != null && input.getPageSize() != null) {
                Pageable pageable = PageRequest.of(input.getPage(), input.getPageSize());
                Page<Music> page = musicRepository.searchByName(input.getSearchTerm(), pageable);
                musics = page.getContent().stream()
                        .map(this::toMusicDto)
                        .collect(Collectors.toList());
                totalCount = (int) page.getTotalElements();
                totalPages = page.getTotalPages();
            } else {
                List<Music> musicList = musicRepository.searchByName(input.getSearchTerm());
                musics = musicList.stream()
                        .map(this::toMusicDto)
                        .collect(Collectors.toList());
                totalCount = musicList.size();
            }
        } else {
            if (input.getPage() != null && input.getPageSize() != null) {
                Pageable pageable = PageRequest.of(input.getPage(), input.getPageSize());
                Page<Music> page = musicRepository.findAll(pageable);
                musics = page.getContent().stream()
                        .map(this::toMusicDto)
                        .collect(Collectors.toList());
                totalCount = (int) page.getTotalElements();
                totalPages = page.getTotalPages();
            } else {
                List<Music> allMusics = musicRepository.findAll();
                musics = allMusics.stream()
                        .map(this::toMusicDto)
                        .collect(Collectors.toList());
                totalCount = allMusics.size();
            }
        }

        return new ListMusicsOutput(
                musics,
                totalCount,
                input.getPage(),
                input.getPageSize(),
                totalPages,
                input.getArtist(),
                input.getSearchTerm(),
                input.getPlaylistId()
        );
    }

    @Transactional(readOnly = true)
    public Music findById(Long id) {
        return musicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Music", id));
    }

    @Transactional
    public void delete(Long id) {
        if (!musicRepository.existsById(id)) {
            throw new NotFoundException("Music", id);
        }
        musicRepository.deleteById(id);
    }

    private MusicDto toMusicDto(Music music) {
        return new MusicDto(
                music.getId(),
                music.getName(),
                music.getArtist(),
                music.getAudioUrl(),
                null,
                null
        );
    }

    private MusicDto toMusicDtoWithPlaylistInfo(Music music, Integer order, java.time.LocalDateTime addedAt) {
        return new MusicDto(
                music.getId(),
                music.getName(),
                music.getArtist(),
                music.getAudioUrl(),
                order,
                addedAt
        );
    }
}
