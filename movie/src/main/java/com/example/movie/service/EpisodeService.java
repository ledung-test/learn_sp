package com.example.movie.service;

import com.example.movie.entity.Episode;
import com.example.movie.reponsitory.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeService {
    @Autowired
    private EpisodeRepository episodeRepository;
    public List<Episode> getEpisodeListOfMovie(Integer movieId, Boolean status){
        return episodeRepository.findByMovie_IdAndStatusOrderByDisplayOrderAsc(movieId, status);
    }

    public Episode getCurrentEpisode(Integer movieId, String tap, boolean episodeStatus) {
        if(tap.equals("full")) {
            return episodeRepository.findByMovie_IdAndDisplayOrderAndStatus(movieId, 1, episodeStatus).orElse(null);
        } else {
            return episodeRepository.findByMovie_IdAndDisplayOrderAndStatus(movieId, Integer.parseInt(tap), episodeStatus).orElse(null);
        }
    }
}
