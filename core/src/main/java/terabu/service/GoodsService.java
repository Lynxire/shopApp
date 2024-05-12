package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import terabu.dto.goods.GoodsRequest;
import terabu.dto.goods.GoodsResponse;
import terabu.entity.*;
import terabu.exception.goods.GoodsNotFoundException;
import terabu.exception.ingredients.IngredientsNotFoundException;
import terabu.mapper.GoodsMapper;
import terabu.repository.GoodsRepository;
import terabu.repository.IngredientsRepository;


import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class GoodsService {
    private final GoodsRepository goodsRepository;
    private final GoodsMapper goodsMapper;
    private final IngredientsRepository ingredientsRepository;


    public GoodsResponse save(GoodsRequest goodsRequest) {
        List<Long> listIngredientsIdRequest = goodsRequest.getIngredientsId();
        listIngredientsIdRequest.forEach(ingredientsIdRequest -> {
            Ingredients ingredientsBD = ingredientsRepository.findById(ingredientsIdRequest).get();
            goodsRequest.getIngredientsQuality().forEach(quality -> {
               if(ingredientsBD.getQuantity() < quality || ingredientsBD.getQuantity() < 0) {
                   throw new IngredientsNotFoundException("Ингредиенты закончились или привышено кол-во доступных ингредиентов");
               }
               else {
                   ingredientsBD.setQuantity(ingredientsBD.getQuantity() - quality);
               }
            });
        });
        Goods goods = goodsMapper.toEntity(goodsRequest);
        goodsRepository.save(goods);
        return goodsMapper.toResponse(goods);

    }
    public List<GoodsResponse> findAll() {
        List<Goods> list = goodsRepository.findAll();
        List<GoodsResponse> responses = new ArrayList<>();
        list.stream().map(goodsMapper::toResponse).forEach(responses::add);
        return responses;
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
