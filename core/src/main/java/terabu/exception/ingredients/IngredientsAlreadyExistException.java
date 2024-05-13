package terabu.exception.ingredients;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IngredientsAlreadyExistException extends RuntimeException {
    public IngredientsAlreadyExistException(String message) {
        super(message);
    }
}
