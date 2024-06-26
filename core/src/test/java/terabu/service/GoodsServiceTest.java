package terabu.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import terabu.dto.goods.GoodsRequest;
import terabu.dto.goods.GoodsResponse;
import terabu.entity.Goods;
import terabu.entity.Ingredients;
import terabu.entity.status.GoodsType;
import terabu.exception.goods.GoodsAlreadyExistException;
import terabu.mapper.GoodsMapper;
import terabu.repository.GoodsRepository;
import terabu.repository.IngredientsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GoodsServiceTest {
    @Mock
    private GoodsRepository goodsRepository;
    @Mock
    private IngredientsRepository ingredientsRepository;
    @Mock
    private GoodsMapper goodsMapper;
    @InjectMocks
    private GoodsService goodsService;

    @Test
    public void testSaveGoods(){
        List<Ingredients> ingredientsList = List.of(getIngredients());
        when(goodsMapper.toEntity(any(GoodsRequest.class))).thenReturn(getGoods());
        when(ingredientsRepository.findByIdIn(any())).thenReturn(ingredientsList);
        when(goodsRepository.findByName(any(String.class))).thenReturn(Optional.empty());
        when(goodsRepository.save(any(Goods.class))).thenReturn(getGoods());
        when(goodsMapper.toResponse(any(Goods.class))).thenReturn(getGoodsResponse());

        GoodsResponse result = goodsService.save(getGoodsRequest());

        assertNotNull(result);
        assertEquals(getGoods().getId(), result.getId());
        assertEquals(getGoods().getName(), result.getName());

        verify(goodsMapper, times(1)).toEntity(any(GoodsRequest.class));
        verify(ingredientsRepository, times(1)).findByIdIn(any());
        verify(goodsRepository, times(1)).findByName(any(String.class));
        verify(goodsRepository, times(1)).save(any(Goods.class));
        verify(goodsMapper, times(1)).toResponse(any(Goods.class));
    }

    @Test
    public void testSaveGoodsWithException(){
        List<Ingredients> ingredientsList = List.of(getIngredients());
        Optional<Goods> goodsOptional = Optional.of(getGoods());
        when(goodsMapper.toEntity(any(GoodsRequest.class))).thenReturn(getGoods());
        when(ingredientsRepository.findByIdIn(any())).thenReturn(ingredientsList);
        when(goodsRepository.findByName(any(String.class))).thenReturn(goodsOptional);

        GoodsAlreadyExistException exception = assertThrows(GoodsAlreadyExistException.class, () -> goodsService.save(getGoodsRequest()));
        assertEquals("С таким именем уже существует товар", exception.getMessage());

        verify(goodsMapper, times(1)).toEntity(any(GoodsRequest.class));
        verify(ingredientsRepository, times(1)).findByIdIn(any());
        verify(goodsRepository, times(1)).findByName(any(String.class));

    }

    @Test
    public void testAllGoods() {
        Page<Goods> goodsPage = new PageImpl<>(List.of(new Goods(), new Goods(), new Goods()));

        when(goodsRepository.findAll(any(PageRequest.class))).thenReturn(goodsPage);
        when(goodsMapper.toResponse(any(Goods.class))).thenReturn(new GoodsResponse());


        List<GoodsResponse> result = goodsService.findAll(0, 10);

        assertEquals(3, result.size());
        verify(goodsRepository).findAll(any(PageRequest.class));
        verify(goodsMapper, times(3)).toResponse(any(Goods.class));
    }

    @Test
    public void testFindAllByType(){
        List<Goods> goodsList = List.of(getGoods());

        when(goodsRepository.findAllByType(any(GoodsType.class), any(PageRequest.class))).thenReturn(goodsList);
        when(goodsMapper.toResponse(any(Goods.class))).thenReturn(getGoodsResponse());


        List<GoodsResponse> result = goodsService.findAllByType("PIZZA",0, 10);

        assertEquals(1, result.size());
        assertEquals(result.get(0).getType(), "PIZZA");
        verify(goodsRepository).findAllByType(any(GoodsType.class), any(PageRequest.class));
        verify(goodsMapper, times(1)).toResponse(any(Goods.class));
    }

    @Test
    public void findGoodsById(){
        Optional<Goods> goodsOptional = Optional.of(getGoods());
        when(goodsRepository.findById(any(Long.class))).thenReturn(goodsOptional);
        when(goodsMapper.toResponse(any(Goods.class))).thenReturn(getGoodsResponse());

        GoodsResponse result = goodsService.findById(1L);
        verify(goodsRepository).findById(any(Long.class));
        verify(goodsMapper, times(1)).toResponse(any(Goods.class));

        assertNotNull(result);
    }

    @Test
    public void findGoodsByName(){
        Optional<Goods> goodsOptional = Optional.of(getGoods());
        when(goodsRepository.findByName(any(String.class))).thenReturn(goodsOptional);
        when(goodsMapper.toResponse(any(Goods.class))).thenReturn(getGoodsResponse());

        GoodsResponse result = goodsService.findByName("Test");
        verify(goodsRepository).findByName(any(String.class));
        verify(goodsMapper, times(1)).toResponse(any(Goods.class));

        assertNotNull(result);
        assertEquals(result.getName(), "Test");
    }

    @Test
    public void deleteGoodsById(){
        doNothing().when(goodsRepository).deleteById(any(Long.class));
        goodsService.deleteById(1L);
        verify(goodsRepository).deleteById(any(Long.class));
    }



    private Ingredients getIngredients(){
        Ingredients ingredients = new Ingredients();
        ingredients.setId(1L);
        ingredients.setName("Test");
        ingredients.setQuantity(10L);
        return ingredients;
    }
    private GoodsRequest getGoodsRequest(){
        GoodsRequest goodsRequest = new GoodsRequest();
        goodsRequest.setCount(10L);
        goodsRequest.setType("PIZZA");
        goodsRequest.setName("PIZZA");
        goodsRequest.setPrice(15.0);
        goodsRequest.setIngredientsId(List.of(1L));
        goodsRequest.setIngredientsQuality(List.of(10L));
        return goodsRequest;
    }

    private Goods getGoods(){
        Goods goods = new Goods();
        goods.setId(1L);
        goods.setCount(10L);
        goods.setName("Test");
        goods.setPrice(15.0);
        goods.setIngredients(new ArrayList<>());
        goods.setType(GoodsType.PIZZA);
        return goods;
    }

    private GoodsResponse getGoodsResponse(){
        GoodsResponse goodsResponse = new GoodsResponse();
        goodsResponse.setId(1L);
        goodsResponse.setCount(10L);
        goodsResponse.setType("PIZZA");
        goodsResponse.setName("Test");
        goodsResponse.setPrice(15.0);
        return goodsResponse;

    }



}
