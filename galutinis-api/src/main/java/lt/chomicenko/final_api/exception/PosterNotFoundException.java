package lt.chomicenko.final_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PosterNotFoundException extends RuntimeException {

    public PosterNotFoundException(String message) {
        super(message);
    }
}
