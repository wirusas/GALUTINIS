package lt.chomicenko.final_api.mapper;

import lt.chomicenko.final_api.dto.PosterDto;
import lt.chomicenko.final_api.dto.CreatePosterRequest;
import lt.chomicenko.final_api.dto.EditPosterRequest;
import lt.chomicenko.final_api.model.Poster;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PosterMapperImpl implements PosterMapper {
    @Override
    public Poster toPoster(CreatePosterRequest createPosterRequest) {
        if (createPosterRequest == null) {
            return null;
        }
        return new Poster(
                createPosterRequest.getPosterName(),
                createPosterRequest.getDescription(),
                createPosterRequest.getCity(),
                createPosterRequest.getImageUrl(),
                createPosterRequest.getPrice(),
                createPosterRequest.getCategory(),
                createPosterRequest.getComment(),
                createPosterRequest.getRating()
        );
    }

    @Override
    public Poster toPoster(EditPosterRequest editPosterRequest) {
        if (editPosterRequest == null) {
            return null;
        }
        return new Poster(
                editPosterRequest.getPosterName(),
                editPosterRequest.getDescription(),
                editPosterRequest.getCity(),
                editPosterRequest.getImageUrl(),
                editPosterRequest.getPrice(),
                editPosterRequest.getCategory(),
                editPosterRequest.getComment(),
                editPosterRequest.getRating()
        );
    }

    @Override
    public PosterDto toPosterDto(Poster poster) {
        if (poster == null) {
            return null;
        }
        String id = poster.getId() != null ? poster.getId() : "";
        String posterName = poster.getPosterName() != null ? poster.getPosterName() : "";
        String description = poster.getDescription() != null ? poster.getDescription() : "";
        String city = poster.getCity() != null ? poster.getCity() : "";
        String imageUrl = poster.getImageUrl() != null ? poster.getImageUrl() : "";
        String price = poster.getPrice() != null ? poster.getPrice() : "";
        String category = poster.getCategory() != null ? poster.getCategory() : "";
        Map<String, List<String>> comments = poster.getComment() != null ? poster.getComment() : Map.of();
        List<Integer> rating = poster.getRating() != null ? poster.getRating() : List.of();
        ZonedDateTime createdAt = poster.getCreatedAt() != null ? poster.getCreatedAt() : ZonedDateTime.now();

        List<PosterDto.UserDto> userDtos = poster.getUsers().stream()
                .map(user -> new PosterDto.UserDto(user.getUsername()))
                .collect(Collectors.toList());

        return new PosterDto(id, posterName, description, city, imageUrl, price, category, comments, rating, createdAt, userDtos);
    }
}
