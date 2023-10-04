package com.epam.microservice.song.controller;

import com.epam.microservice.song.dto.SongDto;
import com.epam.microservice.song.entity.SongEntity;
import com.epam.microservice.song.exception.InvalidRequestException;
import com.epam.microservice.song.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/songs", produces = APPLICATION_JSON_VALUE)
public class SongController {
    @Autowired
    private SongService songService;

    @PostMapping()
    public ResponseEntity<?> addSong(@RequestBody SongDto songDto) {
        try {
            return new ResponseEntity<>(songService.add(songDto), HttpStatus.OK);
        } catch (InvalidRequestException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getSong(@PathVariable Long id) {
        try {
            Optional<SongEntity> songEntity = songService.getSong(id);
            if (songEntity.isPresent()) {
                return new ResponseEntity<>(songEntity.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    public ResponseEntity<?> delete(@RequestParam("ids") @NotNull List<Long> ids) {
        try {
            songService.delete(ids);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
