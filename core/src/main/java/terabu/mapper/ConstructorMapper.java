package terabu.mapper;

import org.mapstruct.Mapper;
import terabu.entity.Constructor;
import terabu.entity.Goods;

@Mapper(componentModel = "spring")
public interface ConstructorMapper {
    public Goods toGoods(Constructor constructor);
}
