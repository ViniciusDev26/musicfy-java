package com.github.viniciusdev26.musicfy.grpc.service;

import com.github.viniciusdev26.musicfy.dto.music.CreateMusicInput;
import com.github.viniciusdev26.musicfy.dto.music.CreateMusicOutput;
import com.github.viniciusdev26.musicfy.dto.music.ListMusicsInput;
import com.github.viniciusdev26.musicfy.dto.music.ListMusicsOutput;
import com.github.viniciusdev26.musicfy.entity.Music;
import com.github.viniciusdev26.musicfy.grpc.music.*;
import com.github.viniciusdev26.musicfy.service.MusicService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class MusicGrpcService extends MusicServiceGrpc.MusicServiceImplBase {

    private final MusicService musicService;

    @Override
    public void createMusic(CreateMusicRequest request, StreamObserver<CreateMusicResponse> responseObserver) {
        try {
            CreateMusicInput input = new CreateMusicInput(
                    request.getTitle(),
                    request.getArtist(),
                    request.getAlbum()
            );

            CreateMusicOutput output = musicService.createMusic(input);

            CreateMusicResponse response = CreateMusicResponse.newBuilder()
                    .setId(output.getId())
                    .setTitle(output.getName())
                    .setArtist(output.getArtist())
                    .setAlbum(output.getAudioUrl())
                    .setDuration(0)
                    .setCreatedAt("")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void getMusics(GetMusicsRequest request, StreamObserver<GetMusicsResponse> responseObserver) {
        try {
            ListMusicsInput input = new ListMusicsInput(
                    request.getPage(),
                    request.getSize(),
                    null,
                    null,
                    null
            );

            ListMusicsOutput output = musicService.listMusics(input);

            GetMusicsResponse.Builder responseBuilder = GetMusicsResponse.newBuilder()
                    .setTotal(output.getTotalCount())
                    .setPage(output.getPage() != null ? output.getPage() : 0)
                    .setSize(output.getPageSize() != null ? output.getPageSize() : 0);

            output.getMusics().forEach(music -> {
                MusicDto musicDto = MusicDto.newBuilder()
                        .setId(music.getId())
                        .setTitle(music.getName())
                        .setArtist(music.getArtist())
                        .setAlbum(music.getAudioUrl())
                        .setDuration(0)
                        .setCreatedAt("")
                        .build();
                responseBuilder.addMusics(musicDto);
            });

            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void getMusicById(GetMusicByIdRequest request, StreamObserver<MusicResponse> responseObserver) {
        try {
            Music music = musicService.findById(request.getId());

            MusicResponse response = MusicResponse.newBuilder()
                    .setId(music.getId())
                    .setTitle(music.getName())
                    .setArtist(music.getArtist())
                    .setAlbum(music.getAudioUrl())
                    .setDuration(0)
                    .setCreatedAt("")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.NOT_FOUND
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void updateMusic(UpdateMusicRequest request, StreamObserver<MusicResponse> responseObserver) {
        try {
            Music music = musicService.findById(request.getId());

            MusicResponse response = MusicResponse.newBuilder()
                    .setId(music.getId())
                    .setTitle(request.getTitle())
                    .setArtist(request.getArtist())
                    .setAlbum(request.getAlbum())
                    .setDuration(request.getDuration())
                    .setCreatedAt("")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void deleteMusic(DeleteMusicRequest request, StreamObserver<DeleteMusicResponse> responseObserver) {
        try {
            musicService.delete(request.getId());

            DeleteMusicResponse response = DeleteMusicResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Music deleted successfully")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void searchMusicsByTitle(SearchMusicsByTitleRequest request, StreamObserver<GetMusicsResponse> responseObserver) {
        try {
            ListMusicsInput input = new ListMusicsInput(
                    request.getPage(),
                    request.getSize(),
                    null,
                    request.getTitle(),
                    null
            );

            ListMusicsOutput output = musicService.listMusics(input);

            GetMusicsResponse.Builder responseBuilder = GetMusicsResponse.newBuilder()
                    .setTotal(output.getTotalCount())
                    .setPage(output.getPage() != null ? output.getPage() : 0)
                    .setSize(output.getPageSize() != null ? output.getPageSize() : 0);

            output.getMusics().forEach(music -> {
                MusicDto musicDto = MusicDto.newBuilder()
                        .setId(music.getId())
                        .setTitle(music.getName())
                        .setArtist(music.getArtist())
                        .setAlbum(music.getAudioUrl())
                        .setDuration(0)
                        .setCreatedAt("")
                        .build();
                responseBuilder.addMusics(musicDto);
            });

            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void searchMusicsByArtist(SearchMusicsByArtistRequest request, StreamObserver<GetMusicsResponse> responseObserver) {
        try {
            ListMusicsInput input = new ListMusicsInput(
                    request.getPage(),
                    request.getSize(),
                    request.getArtist(),
                    null,
                    null
            );

            ListMusicsOutput output = musicService.listMusics(input);

            GetMusicsResponse.Builder responseBuilder = GetMusicsResponse.newBuilder()
                    .setTotal(output.getTotalCount())
                    .setPage(output.getPage() != null ? output.getPage() : 0)
                    .setSize(output.getPageSize() != null ? output.getPageSize() : 0);

            output.getMusics().forEach(music -> {
                MusicDto musicDto = MusicDto.newBuilder()
                        .setId(music.getId())
                        .setTitle(music.getName())
                        .setArtist(music.getArtist())
                        .setAlbum(music.getAudioUrl())
                        .setDuration(0)
                        .setCreatedAt("")
                        .build();
                responseBuilder.addMusics(musicDto);
            });

            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
}
