package com.appstester_ctrlservice.controller;

import com.appstester_ctrlservice.service.CtrlserviceRestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class CtrlserviceController {

    CtrlserviceRestService ctrlserviceRestService = new CtrlserviceRestService();

    Logger ctrlserviceControllerLogger = Logger.getLogger("CtrlserviceController");


    @GetMapping(path = "/init")
    public ResponseEntity<Object> requestCommservice() {
        String attempts;
        while (true) {
            String moodleResponse = ctrlserviceRestService.requestCommserviceToMoodle();
            if (!moodleResponse.isEmpty()) {
                ctrlserviceControllerLogger.log(Level.INFO, "Moodle returned response.");
                attempts = ctrlserviceRestService.requestCommserviceGetAttempts(moodleResponse);
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return new ResponseEntity<>(attempts, HttpStatus.OK);
    }
}
