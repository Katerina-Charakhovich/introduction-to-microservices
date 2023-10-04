package com.epam.microservice.song.service;

import com.epam.microservice.song.dao.SongRepository;
import com.epam.microservice.song.dto.SongDto;
import com.epam.microservice.song.entity.SongEntity;
import com.epam.microservice.song.exception.InvalidRequestException;
import com.epam.microservice.song.mapper.SongMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongMapper songMapper;

    @Transactional
    public long add(SongDto song) throws InvalidRequestException {
        if (!isValidSongDto(song)){
            throw new InvalidRequestException ("Song metadata missing validation error");
        }
        SongEntity entityModel = songMapper.toSongEntity(song);
        return songRepository.save(entityModel).getId();
    }

    @Transactional
    public void  delete(List<Long> ids) {
        List<SongEntity> songEntities = new ArrayList<>();
        for (long i : ids
        ) {
            Optional<SongEntity> songEntity = songRepository.findById(i);
            songEntity.ifPresent(songEntities::add);
        }
         songRepository.deleteAll(songEntities);
    }

    @Transactional
    public Optional<SongEntity> getSong(Long id) {
        return songRepository.findById(id);
    }

    private List<Long> getIds(String stringIds) {
        return Arrays.stream(stringIds.split(",")).map(Long::parseLong)
                .collect(Collectors.toList());
    }

    private boolean isValidSongDto (SongDto songDto) {
        return songDto != null && songDto.getResourceId() != 0;
    }
}
