package terabu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import terabu.dto.data.UserDataRequest;
import terabu.dto.data.UserDataResponse;
import terabu.entity.UserData;
import terabu.entity.User;
import terabu.logger.LoggerAnnotation;
import terabu.mapper.UserDataMapper;
import terabu.repository.UserDataRepository;
import terabu.repository.UserRepositorySpringData;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserDataService {
    private final UserDataRepository userDataRepository;
    private final UserRepositorySpringData userRepository;
    private final UserDataMapper userDataMapper;
    @LoggerAnnotation
    public UserDataResponse update(UserDataRequest userDataRequest) {
        User user = userRepository.findById(userDataRequest.getUserId()).get();
        if(userDataRequest.getLogin() != null){
            user.setLogin(userDataRequest.getLogin());
        }
        if(userDataRequest.getPassword() != null){
            user.setPassword(userDataRequest.getPassword());
        }
        if(userDataRequest.getEmail() != null){
            user.setEmail(userDataRequest.getEmail());
        }
        userRepository.save(user);
        UserData userData = userDataRepository.findDataByUserId(userDataRequest.getUserId()).orElseGet
                (() -> {
                    UserData newData = new UserData();
                    newData.setUser(user);
                    newData.setName(userDataRequest.getName());
                    newData.setSurname(userDataRequest.getSurname());
                    return userDataRepository.save(newData);
                });

        userData.setUser(user);
        userData.setName(userDataRequest.getName());
        userData.setSurname(userDataRequest.getSurname());
        userDataRepository.save(userData);
        log.info("UserData successfully updated");
        return userDataMapper.toResponse(userData);

    }


}
