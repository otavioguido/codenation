package com.challenge.endpoints;

import com.challenge.entity.User;
import com.challenge.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceInterface userServiceInterface;

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long userId) {
        return userServiceInterface.findById(userId).orElse(null);
    }

    @GetMapping
    public List<User> findByAll(
            @RequestParam("accelerationName") Optional<String> name,
            @RequestParam("companyId") Optional<Long> companyId) {
        return name
                .map(s -> ResponseEntity.ok(userServiceInterface.findByAccelerationName(s)))
                .orElseGet(() -> ResponseEntity.ok(userServiceInterface.findByCompanyId(companyId.get()))).getBody();
    }
}
