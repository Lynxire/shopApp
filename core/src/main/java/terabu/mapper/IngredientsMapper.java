package terabu.mapper;

import org.mapstruct.Mapper;
import terabu.dto.ingredients.IngredientsRequest;
import terabu.dto.ingredients.IngredientsResponse;
import terabu.entity.Ingredients;

@Mapper(componentModel = "spring")
public interface IngredientsMapper {
    public Ingredients toEntity(IngredientsRequest ingredientsRequest);
    public IngredientsResponse toResponse(Ingredients ingredients);
}
