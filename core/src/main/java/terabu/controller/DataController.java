package terabu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import terabu.dto.data.DataRequest;
import terabu.dto.data.DataResponse;
import terabu.service.DataService;

@RestController
@RequiredArgsConstructor
@RequestMapping("data")
public class DataController {
    private final DataService dataService;
    @PostMapping("/update")
    public DataResponse updateData(@RequestBody DataRequest dataRequest){
        return dataService.update(dataRequest, 1L);
    }
}
