package com.isakbamiani.app.ws.service.impl;

import com.isakbamiani.app.ws.UserRepository;
import com.isakbamiani.app.ws.io.entity.UserEntity;
import com.isakbamiani.app.ws.service.UserService;
import com.isakbamiani.app.ws.shared.dto.UserDto;
import com.isakbamiani.app.ws.shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Override
    public UserDto createUser(UserDto user) {


        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        // generate publicId
        String publicUserId = utils.generateUseId(15);

        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword("test");

        // save entities
        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto retunValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, retunValue);

        return retunValue;
    }

    @Override
    public UserDto getUserByUserId(String id) {

        UserDto retunValue = new UserDto();

        UserEntity userEntity = userRepository.findByUserId(id);

        if (userEntity == null)
            throw new RuntimeException("User name not found with id :" + id);

        BeanUtils.copyProperties(userEntity, retunValue);
        return retunValue;
    }

}