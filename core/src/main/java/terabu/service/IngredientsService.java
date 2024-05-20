package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import terabu.dto.ingredients.IngredientsRequest;
import terabu.dto.ingredients.IngredientsResponse;
import terabu.entity.Ingredients;
import terabu.exception.ingredients.IngredientsAlreadyExistException;
import terabu.exception.ingredients.IngredientsNotFoundException;
import terabu.mapper.IngredientsMapper;
import terabu.repository.IngredientsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class IngredientsService {
    private final IngredientsRepository ingredientsRepository;
    private final IngredientsMapper ingredientsMapper;


    public IngredientsResponse updateQualityForIngredients(Long id,Long quality) {
        Ingredients ingredients = ingredientsRepository.findById(id).orElseThrow(() -> new IngredientsNotFoundException("Ингредиент не найден"));
        ingredients.setQuantity(quality);
        ingredientsRepository.save(ingredients);
        return ingredientsMapper.toResponse(ingredients);
    }

    public List<IngredientsResponse> findAll() {
        List<IngredientsResponse> ingredientsList = new ArrayList<>();
        ingredientsRepository.findAll().forEach(ingredients -> {
            IngredientsResponse response = ingredientsMapper.toResponse(ingredients);
            ingredientsList.add(response);
        });
        return ingredientsList;
    }

    public IngredientsResponse findById(Long id) {
        Ingredients ingredients = ingredientsRepository.findById(id).orElseThrow(() -> new IngredientsNotFoundException("С таким ID нету ингредиентов"));
        return ingredientsMapper.toResponse(ingredients);
    }

    public IngredientsResponse save(IngredientsRequest ingredientsRequest) {
        Ingredients ingredients = ingredientsMapper.toEntity(ingredientsRequest);
        if(ingredientsRepository.findByName(ingredientsRequest.getName()) != null) {
            throw new IngredientsAlreadyExistException("С таким именем уже существует ингредиент");
        }
        ingredientsRepository.save(ingredients);
        return ingredientsMapper.toResponse(ingredients);

    }

    public void deleteById(Long id) {
        ingredientsRepository.deleteById(id);
    }
}
