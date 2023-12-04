package com.epam.microservice.song.mapper;

import com.epam.microservice.song.dto.SongDto;
import com.epam.microservice.song.entity.SongEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SongMapper {
    public SongEntity  toSongEntity (SongDto  songDto) {
        SongEntity songEntity = new SongEntity();
        if (songDto.getId()!=null){
            songEntity.setId(songDto.getId());
        }
        songEntity.setAlbum(songDto.getAlbum());
        songEntity.setName(songDto.getName());
        songEntity.setArtist(songDto.getArtist());
        songEntity.setLength(songDto.getLength());
        songEntity.setYear(songDto.getYear());
        songEntity.setResourceId(songDto.getResourceId());
        if (!Objects.isNull(songDto.getGenre())){
            songEntity.setGenre(songDto.getGenre());
        }
        return songEntity;
    }
}
