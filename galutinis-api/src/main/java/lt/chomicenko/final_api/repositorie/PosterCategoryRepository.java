package lt.chomicenko.final_api.repositorie;

import lt.chomicenko.final_api.model.PosterCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosterCategoryRepository extends JpaRepository<PosterCategory, Long> {
    void deleteById(Long categoryId);

    boolean existsByPosterCategoryContainingIgnoreCase(String categoryName);
}
