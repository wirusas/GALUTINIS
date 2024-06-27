package lt.chomicenko.final_api.repositorie;

import lt.chomicenko.final_api.model.Poster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PosterRepository extends JpaRepository<Poster, String> {

    Page<Poster> findByPosterNameContainingIgnoreCase(String posterName, Pageable pageable);

    Page<Poster> findAll(Pageable pageable);

    List<Poster> findByPosterNameContainingIgnoreCase(String posterName);
    List<Poster> findByCategoryContainingIgnoreCase(String category);
}
