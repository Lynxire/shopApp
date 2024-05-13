package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import terabu.entity.Ingredients;

import java.util.List;
import java.util.Optional;

public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
    public List<Ingredients> findByIdIn(List<Long> ids);
    public Ingredients findByName(String name);
}
