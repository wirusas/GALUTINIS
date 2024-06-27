package lt.chomicenko.final_api.service;

import lt.chomicenko.final_api.model.Poster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PosterService {

    List<Poster> getPosters();

    Poster validateAndGetPoster(String id);

    Poster savePoster(Poster poster);

    void deletePoster(Poster poster);

    Optional<Poster> getPosterById(String id);

    Page<Poster> getAllPagedPosters(Pageable pageable);

    Page<Poster> findPosterByName(String posterName, Pageable pageable);

    Page<Poster> findAllPosters(Pageable pageable);

    List<Poster> findByNameContaining(String posterName);

    List<Poster> findByCategory(String category);


    Poster addComentToPoster(String posterId, String comment);


    Poster editPoster(String id, Poster poster);

}
