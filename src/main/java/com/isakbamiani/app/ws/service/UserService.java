package com.isakbamiani.app.ws.service;

import com.isakbamiani.app.ws.shared.dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto getUserByUserId(String id);
}