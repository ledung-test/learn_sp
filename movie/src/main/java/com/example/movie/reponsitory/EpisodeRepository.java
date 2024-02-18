package com.example.movie.reponsitory;

import com.example.movie.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    public List<Episode> findByMovie_IdOrderByDisplayOrderAsc(Integer movieId);
    public List<Episode> findByMovie_IdAndStatusOrderByDisplayOrderAsc(Integer movieId, Boolean status);
    Optional<Episode> findByMovie_IdAndDisplayOrderAndStatus(Integer movieId, int i, boolean episodeStatus);
}
