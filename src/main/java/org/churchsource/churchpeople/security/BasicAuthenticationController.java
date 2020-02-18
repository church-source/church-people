package org.churchsource.churchpeople.security;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller
@CrossOrigin
@RestController
public class BasicAuthenticationController {

    @GetMapping(path = "/basicauth")
    public Authentication authentication() {
        //throw new RuntimeException("Some Error has Happened! Contact Support at ***-***");
        return new Authentication("You are authenticated");
    }
}
