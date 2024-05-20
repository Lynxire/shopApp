package terabu.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import terabu.exception.goods.GoodsAlreadyExistException;
import terabu.exception.goods.GoodsNotFoundException;
import terabu.exception.ingredients.IngredientsAlreadyExistException;
import terabu.exception.ingredients.IngredientsNotAllowedValueException;
import terabu.exception.ingredients.IngredientsNotFoundException;
import terabu.exception.orders.OrdersNotFoundException;
import terabu.exception.stocks.StocksNotFoundException;
import terabu.exception.user.UserAlreadyExistException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(GoodsNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(GoodsNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(GoodsAlreadyExistException.class)
    public ResponseEntity<String> handleResourceNotFoundException(GoodsAlreadyExistException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(OrdersNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(OrdersNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(StocksNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(StocksNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IngredientsNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(IngredientsNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IngredientsAlreadyExistException.class)
    public ResponseEntity<String> handleResourceNotFoundException(IngredientsAlreadyExistException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(IngredientsNotAllowedValueException.class)
    public ResponseEntity<String> handleResourceNotFoundException(IngredientsNotAllowedValueException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<String> handleResourceNotFoundException(UserAlreadyExistException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
