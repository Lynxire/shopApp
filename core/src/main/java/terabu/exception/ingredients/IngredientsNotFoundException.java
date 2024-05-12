package terabu.exception.ingredients;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class IngredientsNotFoundException extends RuntimeException{
    public IngredientsNotFoundException(String message) {
        super(message);
    }
}
