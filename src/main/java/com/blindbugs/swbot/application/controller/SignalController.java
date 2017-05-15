package com.blindbugs.swbot.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/check")
public class SignalController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> get() {
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
