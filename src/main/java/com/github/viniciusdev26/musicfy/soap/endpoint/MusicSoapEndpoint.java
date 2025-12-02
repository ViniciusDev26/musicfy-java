package com.github.viniciusdev26.musicfy.soap.endpoint;

import com.github.viniciusdev26.musicfy.dto.music.CreateMusicInput;
import com.github.viniciusdev26.musicfy.dto.music.ListMusicsInput;
import com.github.viniciusdev26.musicfy.exception.BusinessException;
import com.github.viniciusdev26.musicfy.service.MusicService;
import com.github.viniciusdev26.musicfy.soap.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class MusicSoapEndpoint {

    private static final String NAMESPACE_URI = "http://musicfy.viniciusdev26.github.com/soap/musics";

    private final MusicService musicService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetMusicsRequest")
    @ResponsePayload
    public GetMusicsResponse getMusics(@RequestPayload GetMusicsRequest request) {
        var input = new ListMusicsInput(
                request.getPage(),
                request.getPageSize(),
                request.getArtist(),
                request.getSearchTerm(),
                request.getPlaylistId()
        );
        var result = musicService.listMusics(input);

        var response = new GetMusicsResponse();
        result.getMusics().forEach(m -> {
            var musicDto = new MusicSoapDto();
            musicDto.setId(m.getId());
            musicDto.setName(m.getName());
            musicDto.setArtist(m.getArtist());
            musicDto.setAudioUrl(m.getAudioUrl());
            response.getMusics().add(musicDto);
        });
        response.setTotalCount(result.getTotalCount());
        response.setPage(result.getPage());
        response.setPageSize(result.getPageSize());
        response.setTotalPages(result.getTotalPages());

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateMusicRequest")
    @ResponsePayload
    public MusicSoapDto createMusic(@RequestPayload CreateMusicRequest request) {
        try {
            var input = new CreateMusicInput(
                    request.getName(),
                    request.getArtist(),
                    request.getAudioUrl()
            );

            var result = musicService.createMusic(input);

            var response = new MusicSoapDto();
            response.setId(result.getId());
            response.setName(result.getName());
            response.setArtist(result.getArtist());
            response.setAudioUrl(result.getAudioUrl());

            return response;
        } catch (BusinessException ex) {
            throw new RuntimeException("Business Error: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Validation Error: " + ex.getMessage());
        }
    }
}
