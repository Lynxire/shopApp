package terabu.mapper;

import org.mapstruct.Mapper;
import terabu.dto.goods.GoodsRequest;
import terabu.dto.goods.GoodsResponse;
import terabu.entity.Goods;

@Mapper(componentModel = "spring")
public interface GoodsMapper {
    public Goods toEntity(GoodsRequest goodsRequest);
    public GoodsResponse toResponse(Goods goods);
}
