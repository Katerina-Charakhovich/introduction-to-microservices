package com.epam.microservice.resource.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SongDto {
    private Long id;
    private String name;
    private String artist;
    private String album;
    private int length;
    private int year;
    private String genre;
    private Long resourceId;
}
