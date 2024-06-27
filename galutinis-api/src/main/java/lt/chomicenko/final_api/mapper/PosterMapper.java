package lt.chomicenko.final_api.mapper;

import lt.chomicenko.final_api.dto.PosterDto;
import lt.chomicenko.final_api.dto.CreatePosterRequest;
import lt.chomicenko.final_api.dto.EditPosterRequest;
import lt.chomicenko.final_api.model.Poster;

public interface PosterMapper {
    Poster toPoster(CreatePosterRequest createPosterRequest);
    Poster toPoster(EditPosterRequest editPosterRequest);

    PosterDto toPosterDto(Poster poster);
}
