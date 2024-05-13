package terabu.exception.goods;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class GoodsAlreadyExistException extends RuntimeException {
    public GoodsAlreadyExistException(String message) {
        super(message);
    }
}
