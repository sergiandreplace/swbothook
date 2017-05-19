package com.blindbugs.swbot.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/check")
public class SignalController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> get() {
        return new ResponseEntity<String>("<html>\n" +
                "<body>\n" +
                "<img src=\"http://place-hoff.com/800/600\" />\n" +
                "<h1>The Hoff approves this!</h1>\n" +
                "</body>\n" +
                "</html>", HttpStatus.OK);
    }
}
