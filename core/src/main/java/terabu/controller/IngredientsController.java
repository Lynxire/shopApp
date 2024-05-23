package terabu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import terabu.dto.ingredients.IngredientsRequest;
import terabu.dto.ingredients.IngredientsResponse;
import terabu.entity.Ingredients;
import terabu.service.IngredientsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("ingredients")
@Tag(name = "Контроллер для ингредиентов")
public class IngredientsController {
    private final IngredientsService ingredientsService;

    @Operation(summary = "Все ингредиенты")
    @GetMapping
    public List<IngredientsResponse> findAll() {
        return ingredientsService.findAll();
    }

    @Operation(summary = "Удаление ингредиентов по ID")
    @PostMapping("/delete")
    public void deleteById(@RequestParam @Min(1) @NotNull Long id) {
        ingredientsService.deleteById(id);
    }

    @Operation(summary = "Добавление ингредиентов")
    @PostMapping("/add")
    public IngredientsResponse add(@RequestBody @Valid IngredientsRequest ingredientsRequest) {
        return ingredientsService.save(ingredientsRequest);
    }

    @Operation(summary = "Поиск ингредиентов по ID")
    @GetMapping("/findById")
    public IngredientsResponse findById(@RequestParam @Min(1) @NotNull Long id) {
        return ingredientsService.findById(id);
    }

    @Operation(summary = "Найти товар по ID и обновить его количество")
    @PostMapping("/updateQuality")
    public IngredientsResponse updateQuality(@RequestParam @Min(1) @NotNull Long id, @RequestParam @Min(1) @NotNull Long quality) {
        return ingredientsService.updateQualityForIngredients(id,quality);
    }

}
