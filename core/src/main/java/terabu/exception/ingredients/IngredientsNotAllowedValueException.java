package terabu.exception.ingredients;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IngredientsNotAllowedValueException extends RuntimeException {
    public IngredientsNotAllowedValueException(String message) {
        super(message);
    }
}
