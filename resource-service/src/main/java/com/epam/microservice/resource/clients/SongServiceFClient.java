package com.epam.microservice.resource.clients;

import com.epam.microservice.resource.dto.SongDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@FeignClient(name = "songServiceClient", url = "${song.service.url}")
public interface SongServiceFClient {
    @PostMapping(value = "/songs")
    ResponseEntity<?> addSong(@RequestBody SongDto songDto);

    @GetMapping(value = "/songs/{id}")
    ResponseEntity<?> getSong(@PathVariable Long id);

    @DeleteMapping(value = "/songs")
    ResponseEntity<?> delete(@RequestParam("ids") @NotNull List<Long> ids);
}
