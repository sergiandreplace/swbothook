package com.blindbugs.swbot.application.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.event.Level.DEBUG;

@RestController
@RequestMapping("/api/hook")
public class HookController {
    private static final Logger logger = LoggerFactory.getLogger("HookController");

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> get(@RequestBody String entry) {
        logger.debug("RECEIVED: " + entry);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
