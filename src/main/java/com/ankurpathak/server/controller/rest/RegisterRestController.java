package com.ankurpathak.server.controller.rest;

import com.ankurpathak.server.controller.exception.ValidationException;
import com.ankurpathak.server.dto.UserDto;
import com.ankurpathak.server.validation.constraints.groups.Sequence;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ankur on 03-02-2017.
 */
@RestController
@RequestMapping("/api/v2")
public class RegisterRestController {

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody @Validated(Sequence.class) UserDto userDto, BindingResult result){
        result.rejectValue("email", "Exists.userDto.email", new Object[]{"Email"}, "Exists.userDto.email");
        if(result.hasErrors()){
           throw new ValidationException(result);
        }
        return ResponseEntity.noContent().build();
    }
}
