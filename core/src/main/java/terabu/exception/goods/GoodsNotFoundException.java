package terabu.exception.goods;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GoodsNotFoundException extends RuntimeException {
   public GoodsNotFoundException(String message) {
        super(message);
    }
    public GoodsNotFoundException(){
        super();
    }
}
