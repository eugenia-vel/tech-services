package com.cdpo.notifier.client;

import com.cdpo.notifier.dto.UserDTO;

public interface UserClient {
    UserDTO getUserDataById(Long id);
}
