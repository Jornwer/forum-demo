package com.jornwer.forumdemo.rest;

import com.jornwer.forumdemo.dto.UsernameDTO;
import com.jornwer.forumdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/settings")
public class RestSettingsController {

    private final UserService userService;

    @Autowired
    public RestSettingsController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/changeusername")
    public void changeUsername(@RequestBody UsernameDTO request){
        userService.updateUsername(request.getOldUsername(), request.getNewUsername());
    }
}
