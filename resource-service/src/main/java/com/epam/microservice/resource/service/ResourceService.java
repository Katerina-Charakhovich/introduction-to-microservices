package com.epam.microservice.resource.service;

import com.epam.microservice.resource.clients.SongServiceFClient;
import com.epam.microservice.resource.dao.ResourceRepository;
import com.epam.microservice.resource.dto.ResourceCreateResponseDto;
import com.epam.microservice.resource.dto.ResourceDeleteResponseDto;
import com.epam.microservice.resource.dto.SongDto;
import com.epam.microservice.resource.entity.ResourceEntity;
import com.epam.microservice.resource.exception.InvalidRequestException;
import com.epam.microservice.resource.parser.MP3Parser;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private MP3Parser mp3Parser;

    @Autowired
    private SongServiceFClient songServiceFClient;

    @Transactional
    public ResourceCreateResponseDto upload(MultipartFile file) throws InvalidRequestException {
        try {
            InputStream inputStream = file.getInputStream();
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            if (!isValidMP3(inputStream)) {
                throw new InvalidRequestException("File validation error");
            }
            ResourceEntity entityModel = new ResourceEntity();
            entityModel.setFileName(file.getOriginalFilename());
            entityModel.setFile(buffer);
            var resource = resourceRepository.save(entityModel);
            process(resource);
            return ResourceCreateResponseDto.builder()
                    .id(resource.getId())
                    .build();
        } catch (IOException ex) {
            throw new InvalidRequestException(ex.getMessage());
        }
    }

    public void process(ResourceEntity resource) {
        SongDto songDto = mp3Parser.parse(resource.getFile());
        songDto.setResourceId(resource.getId());
        songServiceFClient.addSong(songDto);
    }

    @Transactional
    public ResourceDeleteResponseDto delete(List<Long> ids) {
        List<ResourceEntity> files = new ArrayList<>();
        for (long i : ids
        ) {
            Optional<ResourceEntity> file = resourceRepository.findById(i);
            file.ifPresent(files::add);
        }
        resourceRepository.deleteAll(files);
        songServiceFClient.delete(ids);
        return ResourceDeleteResponseDto.builder()
                .ids(files.stream().map(ResourceEntity::getId).collect(Collectors.toList()))
                .build();
    }

    @Transactional
    public byte[] getFile(Long id) {
        Optional<ResourceEntity> file = resourceRepository.findById(id);
        return file.map(ResourceEntity::getFile).orElse(null);
    }

    private boolean isValidMP3(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return false;
        }
        Tika tika = new Tika();
        String mediaType = tika.detect(inputStream);
        return (mediaType.equals("application/octet-stream"));
    }
}
