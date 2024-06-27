package lt.chomicenko.final_api.service;

import lombok.RequiredArgsConstructor;
import lt.chomicenko.final_api.exception.PosterCategoryExisting;
import lt.chomicenko.final_api.model.PosterCategory;
import lt.chomicenko.final_api.repositorie.PosterCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PosterCategoryServiceImpl implements PosterCategoryService {
    private final PosterCategoryRepository posterCategoryRepository;

    @Override
    public PosterCategory createCategory(PosterCategory posterCategory) {
        String categoryName = posterCategory.getPosterCategory();
        if (posterCategoryRepository.existsByPosterCategoryContainingIgnoreCase(categoryName)) {
            throw new PosterCategoryExisting("Category already exists: " + categoryName);
        }
        return posterCategoryRepository.save(posterCategory);
    }


    @Override
    public void deleteCategory(Long categoryId) {
        posterCategoryRepository.deleteById(categoryId);
    }


    @Override
    public PosterCategory updateCategory(Long categoryId, PosterCategory updatedPosterCategory) {
        PosterCategory existingCategory = posterCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        existingCategory.setPosterCategory(updatedPosterCategory.getPosterCategory());
        return posterCategoryRepository.save(existingCategory);
    }

    @Override
    public List<PosterCategory> getAllCategories() {
        return posterCategoryRepository.findAll();
    }
}
