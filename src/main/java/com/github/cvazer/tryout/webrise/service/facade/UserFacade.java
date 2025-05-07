package com.github.cvazer.tryout.webrise.service.facade;

import com.github.cvazer.tryout.webrise.service.users.UserMapper;
import com.github.cvazer.tryout.webrise.service.users.UserService;
import com.github.cvazer.tryout.webrise.web.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserDto info() {
        var user = userService.getCurrentUser();
        return userMapper.fromEntity(user);
    }

    @Transactional
    public UserDto update(@Valid UserDto dto) {
        var user = userService.getCurrentUser();
        userMapper.mapFields(user, dto);
        return userMapper.fromEntity(user);
    }

    public void delete() {
        var user = userService.getCurrentUser();
        userService.delete(user);
    }
}
