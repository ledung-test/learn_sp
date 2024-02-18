package com.example.movie.service.admin;

import com.example.movie.entity.Episode;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.reponsitory.EpisodeRepository;
import com.example.movie.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AdminEpisodeService {
    @Autowired
    private EpisodeRepository episodeRepository;
    @Autowired
    private VideoService videoService;
    public List<Episode> getEpisodeListOfMovie(Integer movieId) {
        return episodeRepository.findByMovie_IdOrderByDisplayOrderAsc(movieId);
    }

    public void uploadFile(MultipartFile file, Integer id) {
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tập phim có id là " + id));

        //Upload
        String videoUrl = videoService.uploadVideo(file);
        episode.setVideoUrl(videoUrl);
        episodeRepository.save(episode);
    }
}
