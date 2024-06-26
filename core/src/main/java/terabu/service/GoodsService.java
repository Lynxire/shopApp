package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import terabu.dto.goods.GoodsRequest;
import terabu.dto.goods.GoodsResponse;
import terabu.entity.*;
import terabu.entity.status.GoodsType;
import terabu.exception.goods.GoodsAlreadyExistException;
import terabu.exception.goods.GoodsNotFoundException;
import terabu.exception.ingredients.IngredientsNotAllowedValueException;
import terabu.mapper.GoodsMapper;
import terabu.repository.GoodsRepository;
import terabu.repository.IngredientsRepository;


import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class GoodsService {
    private final GoodsRepository goodsRepository;
    private final GoodsMapper goodsMapper;
    private final IngredientsRepository ingredientsRepository;

    public GoodsResponse save(GoodsRequest goodsRequest) {
        Goods goods = goodsMapper.toEntity(goodsRequest);
        List<Ingredients> ingredientsList = ingredientsRepository.findByIdIn(goodsRequest.getIngredientsId());
        ingredientsList.forEach(ingredients -> {
            int count = 0;
            count = (int) (ingredients.getId() - 1);
            if(ingredients.getQuantity() < goodsRequest.getIngredientsQuality().get(count) || ingredients.getQuantity() < 0){
                throw new IngredientsNotAllowedValueException("Недостаточно ингредиентов");
            }
            ingredients.setQuantity(ingredients.getQuantity() - goodsRequest.getIngredientsQuality().get(count));
        });
        goods.setIngredients(ingredientsList);

        Optional<Goods> optionalGoods = goodsRepository.findByName(goodsRequest.getName());
        if(optionalGoods.isPresent()){
            throw new GoodsAlreadyExistException("С таким именем уже существует товар");
        }

        goodsRepository.save(goods);
        return goodsMapper.toResponse(goods);

    }
    public List<GoodsResponse> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id"));
        Page<Goods> goodsPage = goodsRepository.findAll(pageRequest);
        return goodsPage.getContent().stream().map(goodsMapper::toResponse).toList();
    }

    public List<GoodsResponse> findAllByType(String type, int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size, Sort.by("id"));
        List<Goods> goodsList = goodsRepository.findAllByType(GoodsType.valueOf(type), pageRequest);
        return goodsList.stream().map(goodsMapper::toResponse).toList();

    }

    public GoodsResponse findById(Long id) {
        Goods goods = goodsRepository.findById(id).orElseThrow(() -> new GoodsNotFoundException("Неверный ID, товар не существует"));
        return goodsMapper.toResponse(goods);
    }

    public GoodsResponse findByName(String name) {
        Goods goods = goodsRepository.findByName(name).orElseThrow(() -> new GoodsNotFoundException("Неверное название, товар не существует"));
        return goodsMapper.toResponse(goods);
    }

    public void deleteById(Long id) {
        goodsRepository.deleteById(id);
    }
}
