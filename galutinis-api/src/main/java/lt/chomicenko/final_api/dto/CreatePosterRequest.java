package lt.chomicenko.final_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CreatePosterRequest {

    @Schema(example = "Sample Poster")
    @NotBlank
    private String posterName;

    @Schema(example = "This is a sample poster description.")
    @NotBlank
    private String description;

    @Schema(example = "1234567890")
    @NotBlank
    private String city;

    @Schema(example = "http://example.com/image.jpg")
    @NotBlank
    private String imageUrl;

    @Schema(example = "300")
    @NotBlank
    private String price;

    @Schema(example = "Fiction")
    @NotBlank
    private String category;

    @Schema(example = "{\"user1\": [\"Great poster!\"], \"user2\": [\"Loved it!\"]}")
    private Map<String, List<String>> comment;

    @Schema(example = "[5, 4]")
    private List<Integer> rating;
}
