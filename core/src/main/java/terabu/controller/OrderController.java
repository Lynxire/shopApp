package terabu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import terabu.dto.orders.OrderResponse;
import terabu.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
@Tag(name = "Контроллер для заказов")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Просмотр заказов по ID пользователя")
    @GetMapping("/byUserId")
    public List<OrderResponse> getOrderByUserId(@RequestParam @Min(1) @NotNull Long userId) {
        return orderService.getOrderByUserId(userId);
    }
}
