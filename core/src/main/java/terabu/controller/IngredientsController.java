package terabu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public void deleteById(@RequestParam Long id) {
        ingredientsService.deleteById(id);
    }

    @Operation(summary = "Добавление ингредиентов")
    @PostMapping("/add")
    public IngredientsResponse add(@RequestBody IngredientsRequest ingredientsRequest) {
        return ingredientsService.save(ingredientsRequest);
    }

    @Operation(summary = "Поиск ингредиентов по ID")
    @GetMapping("/findById")
    public IngredientsResponse findById(@RequestParam Long id) {
        return ingredientsService.findById(id);
    }

    @Operation(summary = "Найти товар по ID и обновить его количество")
    @PostMapping("/updateQuality")
    public IngredientsResponse updateQuality(@RequestParam Long id, Long quality) {
        return ingredientsService.updateQualityForIngredients(id,quality);
    }

}
