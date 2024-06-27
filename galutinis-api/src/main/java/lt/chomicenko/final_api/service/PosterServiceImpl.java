package lt.chomicenko.final_api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lt.chomicenko.final_api.exception.PosterNotFoundException;
import lt.chomicenko.final_api.exception.UserNotFoundException;
import lt.chomicenko.final_api.mapper.PosterMapper;
import lt.chomicenko.final_api.model.Poster;
import lt.chomicenko.final_api.model.User;
import lt.chomicenko.final_api.repositorie.PosterRepository;
import lt.chomicenko.final_api.repositorie.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class PosterServiceImpl implements PosterService {

    private final PosterRepository posterRepository;
    private final PosterMapper posterMapper;
    private final UserRepository userRepository;

    @Override
    public List<Poster> getPosters() {
        return posterRepository.findAll();
    }

    @Override
    public Poster validateAndGetPoster(String id) {
        return posterRepository.findById(id).orElseThrow(() -> new PosterNotFoundException("Poster not found with ID: " + id));
    }

    @Override
    public Poster savePoster(Poster poster) {
        return posterRepository.save(poster);
    }

    @Override
    @Transactional
    public Poster editPoster(String id, Poster updatedPoster) {
        Poster existingPoster = posterRepository.findById(id)
                .orElseThrow(() -> new PosterNotFoundException("Poster not found with ID: " + id));

        existingPoster.setPosterName(updatedPoster.getPosterName());
        existingPoster.setDescription(updatedPoster.getDescription());
        existingPoster.setCity(updatedPoster.getCity());
        existingPoster.setImageUrl(updatedPoster.getImageUrl());
        existingPoster.setPrice(updatedPoster.getPrice());
        existingPoster.setCategory(updatedPoster.getCategory());
        existingPoster.setComment(updatedPoster.getComment());

        return posterRepository.save(existingPoster);
    }

    @Override
    @Transactional
    public void deletePoster(Poster poster) {
        Poster existingPoster = posterRepository.findById(poster.getId())
                .orElseThrow(() -> new PosterNotFoundException("Poster not found with ID: " + poster.getId()));

        
        existingPoster.getComment().size();

        posterRepository.delete(existingPoster);
    }

    @Override
    public Optional<Poster> getPosterById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return posterRepository.findById(id);
    }

    @Override
    public Page<Poster> getAllPagedPosters(Pageable pageable) {
        return posterRepository.findAll(pageable);
    }

    @Override
    public Page<Poster> findPosterByName(String posterName, Pageable pageable) {
        return posterRepository.findByPosterNameContainingIgnoreCase(posterName, pageable);
    }

    @Override
    public Page<Poster> findAllPosters(Pageable pageable) {
        return posterRepository.findAll(pageable);
    }

    @Override
    public List<Poster> findByNameContaining(String posterName) {
        if (posterName == null) {
            throw new PosterNotFoundException("Poster you searched was not found");
        }
        return posterRepository.findByPosterNameContainingIgnoreCase(posterName);
    }

    @Override
    public List<Poster> findByCategory(String category) {
        if (category == null) {
            throw new PosterNotFoundException("Poster you searched was not found");
        }
        return posterRepository.findByCategoryContainingIgnoreCase(category);
    }

    @Override
    public Poster addComentToPoster(String posterId, String comment) {
        Poster poster = posterRepository.findById(posterId)
                .orElseThrow(() -> new PosterNotFoundException("Poster not found with ID: " + posterId));

        Map<String, List<String>> existingComments = poster.getComment();


        List<String> comments = existingComments.getOrDefault("Anonymous", new ArrayList<>());


        comments.add(comment);


        existingComments.put("Anonymous", comments);


        poster.setComment(existingComments);

        return posterRepository.save(poster);
    }


}
