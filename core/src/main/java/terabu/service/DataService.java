package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import terabu.dto.data.DataRequest;
import terabu.dto.data.DataResponse;
import terabu.entity.Data;
import terabu.entity.User;
import terabu.mapper.DataMapper;
import terabu.repository.DataRepository;
import terabu.repository.UserRepositorySpringData;

@RequiredArgsConstructor
@Service
public class DataService {
    private final DataRepository dataRepository;
    private final UserRepositorySpringData userRepository;
    private final DataMapper dataMapper;
    public DataResponse update(DataRequest dataRequest, Long userId) {
        Data data = dataMapper.toEntity(dataRequest);
        User user = userRepository.findById(userId).get();
        data.setUser(user);
        dataRepository.save(data);
        return dataMapper.toResponse(data);
    }


}
