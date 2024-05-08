package terabu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import terabu.service.GoodsService;

@RestController
@RequestMapping("goods")
@RequiredArgsConstructor
public class GoodsController {
    private final GoodsService goodsService;


}
