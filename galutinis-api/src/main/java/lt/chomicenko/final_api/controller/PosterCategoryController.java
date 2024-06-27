package lt.chomicenko.final_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lt.chomicenko.final_api.model.PosterCategory;
import lt.chomicenko.final_api.model.User;
import lt.chomicenko.final_api.security.CustomUserDetails;
import lt.chomicenko.final_api.service.PosterCategoryService;
import lt.chomicenko.final_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lt.chomicenko.final_api.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class PosterCategoryController {

    private final PosterCategoryService posterCategoryService;
    private final UserService userService;

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PosterCategory createPosterCategory(@AuthenticationPrincipal CustomUserDetails currentUser,
                                               @RequestBody PosterCategory newPosterCategory) {
        User user = userService.validateAndGetUserByUsername(currentUser.getUsername());
        return posterCategoryService.createCategory(newPosterCategory);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @PutMapping("/{categoryId}")
    public PosterCategory updatePosterCategory(@AuthenticationPrincipal CustomUserDetails currentUser,
                                               @Valid @RequestBody PosterCategory newPosterCategory,
                                               @PathVariable Long categoryId) {
        User user = userService.validateAndGetUserByUsername(currentUser.getUsername());
        return posterCategoryService.updateCategory(categoryId, newPosterCategory);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @DeleteMapping("/{categoryId}")
    public void deletePosterCategory(@AuthenticationPrincipal CustomUserDetails currentUser,
                                     @PathVariable Long categoryId) {
        User user = userService.validateAndGetUserByUsername(currentUser.getUsername());
        posterCategoryService.deleteCategory(categoryId);
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping()
    public List<PosterCategory> getAllCategories(){
        return posterCategoryService.getAllCategories();
    }
}
