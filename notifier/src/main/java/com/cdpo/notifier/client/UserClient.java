package com.cdpo.notifier.client;

import com.cdpo.notifier.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "users", url = "http://localhost:8088/api/v1/users")
public interface UserClient {
    @GetMapping("/{id}")
    UserDTO getUserDataById(@PathVariable Long id);
}
