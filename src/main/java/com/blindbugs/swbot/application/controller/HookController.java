package com.blindbugs.swbot.application.controller;

import io.netty.util.internal.logging.Log4JLoggerFactory;
import org.slf4j.event.Level;
import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

import static org.slf4j.event.Level.DEBUG;

@RestController
@RequestMapping("/api/hook")
public class HookController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> get(@RequestBody String entry) {
        Logger.getLogger(this.getClass().getName()).log(java.util.logging.Level.ALL, entry);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
