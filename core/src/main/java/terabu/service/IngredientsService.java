package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import terabu.entity.Ingredients;
import terabu.repository.IngredientsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientsService {
    private final IngredientsRepository ingredientsRepository;
    public List<Ingredients> findAll() {
        return ingredientsRepository.findAll();
    }

    public Ingredients findById(Long id) {
        return ingredientsRepository.findById(id).get();
    }
    //переписать под дто
    public Ingredients save(Ingredients ingredients) {
        return ingredientsRepository.save(ingredients);
    }

    public void deleteById(Long id) {
        ingredientsRepository.deleteById(id);
    }
}
