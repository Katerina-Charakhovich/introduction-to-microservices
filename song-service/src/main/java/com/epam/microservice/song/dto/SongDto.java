package com.epam.microservice.song.dto;

import lombok.*;

@Data
@Builder
public class SongDto {
    private Long id;
    private String name;
    private String genre;
    private String artist;
    private String album;
    private int length;
    private int year;
    private Long resourceId;
}
