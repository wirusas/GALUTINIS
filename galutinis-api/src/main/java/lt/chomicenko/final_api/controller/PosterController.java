package lt.chomicenko.final_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lt.chomicenko.final_api.dto.PosterDto;
import lt.chomicenko.final_api.dto.CreatePosterRequest;
import lt.chomicenko.final_api.dto.EditPosterRequest;
import lt.chomicenko.final_api.mapper.PosterMapper;
import lt.chomicenko.final_api.model.Poster;
import lt.chomicenko.final_api.security.CustomUserDetails;
import lt.chomicenko.final_api.service.PosterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static lt.chomicenko.final_api.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posters")
public class PosterController {
    private final PosterService posterService;
    private final PosterMapper posterMapper;

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PosterDto createPoster(@Valid @RequestBody CreatePosterRequest createPosterRequest) {
        Poster poster = posterMapper.toPoster(createPosterRequest);
        poster.setId(UUID.randomUUID().toString());

        return posterMapper.toPosterDto(posterService.savePoster(poster));
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public PosterDto editPoster(@Valid @RequestBody EditPosterRequest editPosterRequest, @PathVariable String id) {
        Poster poster = posterMapper.toPoster(editPosterRequest);
        return posterMapper.toPosterDto(posterService.editPoster(id, poster));
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @DeleteMapping("/{id}")
    public PosterDto deletePoster(@PathVariable String id) {
        Poster poster = posterService.validateAndGetPoster(id);
        posterService.deletePoster(poster);
        return posterMapper.toPosterDto(poster);
    }

    @GetMapping("/allPosters")
    public List<PosterDto> getAllPosters() {
        List<Poster> posters = posterService.getPosters();
        return posters.stream()
                .map(posterMapper::toPosterDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/allPagedPosters")
    public List<PosterDto> getAllPagedPosters(@RequestParam(defaultValue = "0") int page) {
        Page<Poster> posterPage = posterService.getAllPagedPosters(PageRequest.of(page, 9));
        return posterPage.getContent().stream()
                .map(posterMapper::toPosterDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/posterName")
    public List<PosterDto> getPosterByName(@RequestParam String posterName) {
        List<Poster> posters = posterService.findByNameContaining(posterName);
        return posters.stream()
                .map(posterMapper::toPosterDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/posterCategory")
    public List<PosterDto> getPosterByCategory(@RequestParam String category) {
        List<Poster> posters = posterService.findByCategory(category);
        return posters.stream()
                .map(posterMapper::toPosterDto)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{posterId}/comments")
    public PosterDto addCommentToPoster(@PathVariable String posterId,
                                        @RequestParam String comment) {
        Poster updatedPoster = posterService.addComentToPoster(posterId, comment);
        return posterMapper.toPosterDto(updatedPoster);
    }


    @GetMapping("/{id}")
    public PosterDto getPosterById(@PathVariable String id) {
        Poster poster = posterService.validateAndGetPoster(id);
        return posterMapper.toPosterDto(poster);
    }
}
