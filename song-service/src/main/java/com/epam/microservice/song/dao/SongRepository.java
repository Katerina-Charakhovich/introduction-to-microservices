package com.epam.microservice.song.dao;

import com.epam.microservice.song.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Long> {
        Optional<SongEntity> findById(Long id);
        Optional<SongEntity> findByResourceId(Long id);
}