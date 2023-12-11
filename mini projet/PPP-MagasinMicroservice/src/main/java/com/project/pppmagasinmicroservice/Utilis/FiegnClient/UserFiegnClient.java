package com.project.pppmagasinmicroservice.Utilis.FiegnClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AuthMicroService", url = "http://localhost:8091")

public interface UserFiegnClient {
    @GetMapping("/api/user/{username}")
    UserClass getUserByUsername(@PathVariable("username") String username);

}
