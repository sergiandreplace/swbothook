package com.blindbugs.swbot.application.controller;
import com.blindbugs.swbot.application.action.SubjectAction;
import com.blindbugs.swbot.application.request.AIRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/api/hook")
public class HookController {
    private static final Logger logger = LoggerFactory.getLogger("HookController");

    private final SubjectAction subjectAction;

    @Inject
    public HookController(SubjectAction subjectAction) {
        this.subjectAction = subjectAction;
    }


    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> get(@RequestBody AIRequest request) {
        logger.info(request.toString());
        String response = subjectAction.getCharacter(request);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
