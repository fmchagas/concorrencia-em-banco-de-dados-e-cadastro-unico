package br.com.fmchagas.supremovideo.video;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class VideoController {

    private final VideoRepository videoRepository;

    public VideoController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @PostMapping("/videos")
    @Transactional
    public ResponseEntity<Object> cadastrar(@Valid @RequestBody NovoVideoRequest request, UriComponentsBuilder uriComponentsBuilder){
        final Video video = request.toModel();

        videoRepository.save(video);

        URI location = uriComponentsBuilder.path("videos/{id}")
                .buildAndExpand(video.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/videos/{id}/visualizar")
    @Transactional
    public ResponseEntity<Void> visualizar(@PathVariable Long id){
        Video video = videoRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontramos video"));

        video.incrementaVisualizacao();

        videoRepository.save(video);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/videos/{id}/gostar")
    @Transactional
    public ResponseEntity<Void> gostar(@PathVariable Long id){
        Video video = videoRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontramos video"));

        video.incrementaGostar();

        videoRepository.save(video);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/videos/{id}/nao-gostar")
    @Transactional
    public ResponseEntity<Void> naoGostar(@PathVariable Long id){
        Video video = videoRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontramos video"));

        video.incrementaNaoGostar();

        videoRepository.save(video);

        return ResponseEntity.noContent().build();
    }
}