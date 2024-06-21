package terabu.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import terabu.configs.RestTemplateConfig;
import terabu.exception.goods.GoodsAlreadyExistException;
import terabu.exception.goods.GoodsNotFoundException;
import terabu.exception.ingredients.IngredientsAlreadyExistException;
import terabu.exception.ingredients.IngredientsNotAllowedValueException;
import terabu.exception.ingredients.IngredientsNotFoundException;
import terabu.exception.orders.OrdersNotFoundException;
import terabu.exception.stocks.StocksNotFoundException;
import terabu.exception.user.UserAlreadyExistException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(GoodsNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(GoodsNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(GoodsAlreadyExistException.class)
    public ResponseEntity<String> handleResourceAlreadyExistException(GoodsAlreadyExistException ex) {
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
    public ResponseEntity<String> handleResourceAlreadyExistException(IngredientsAlreadyExistException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(IngredientsNotAllowedValueException.class)
    public ResponseEntity<String> handleResourceNotFoundException(IngredientsNotAllowedValueException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<String> handleResourceAlreadyExistException(UserAlreadyExistException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleResourceAlreadyExistException(UsernameNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        log.error(validationList.toString(), ex);
        return new ResponseEntity<>(validationList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleResourceException(HttpClientErrorException ex) {
        log.error(ex.getMessage(), ex);
        HttpStatusCode statusCode = ex.getStatusCode();
        if(statusCode == HttpStatus.UNAUTHORIZED) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Пользователь не авторизован");
        }
        return ResponseEntity.status(statusCode).body(ex.getMessage());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> handleResourceException(HttpServerErrorException ex) {
        log.error(ex.getMessage(), ex);
        HttpStatusCode statusCode = ex.getStatusCode();
        return ResponseEntity.status(statusCode).body(ex.getMessage());
    }



}
