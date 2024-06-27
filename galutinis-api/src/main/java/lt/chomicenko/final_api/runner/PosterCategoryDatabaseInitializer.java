package lt.chomicenko.final_api.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.chomicenko.final_api.model.PosterCategory;
import lt.chomicenko.final_api.service.PosterCategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class PosterCategoryDatabaseInitializer implements CommandLineRunner {

    private final PosterCategoryService posterCategoryService;

    @Override
    public void run(String... args) {
        if (!posterCategoryService.getAllCategories().isEmpty()) {
            return;
        }
        CATEGORIES.forEach(category -> {
            posterCategoryService.createCategory(category);
        });
        log.info("Database initialized with poster categories");
    }

    private static final List<PosterCategory> CATEGORIES = Arrays.asList(
            new PosterCategory("Cars"),
            new PosterCategory("Bikes"),
            new PosterCategory("Services"),
            new PosterCategory("Inflatables")
    );
}
