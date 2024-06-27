package lt.chomicenko.final_api.service;

import lt.chomicenko.final_api.model.PosterCategory;

import java.util.List;

public interface PosterCategoryService {

    PosterCategory createCategory(PosterCategory posterCategory);

    void deleteCategory(Long categoryId);

    PosterCategory updateCategory(Long categoryId, PosterCategory updatedPosterCategory);

    List<PosterCategory> getAllCategories();
}
