package lt.chomicenko.final_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PosterCategoryExisting extends RuntimeException{
    public PosterCategoryExisting(String message){super(message);}
}
