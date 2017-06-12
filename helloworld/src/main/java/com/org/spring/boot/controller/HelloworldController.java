package com.org.spring.boot.controller;

import com.codahale.metrics.annotation.Timed;
import com.org.spring.boot.service.HelloworldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * The Helloworld Controller
 */
@Slf4j
@RestController
@RequestMapping(value = "/helloworld")
public class HelloworldController {

    private final HelloworldService helloworldService;

    /**
     * Instantiates a new instance of the Helloworld Controller
     *
     * @param helloworldService
     *         the helloworld service
     */
    @Autowired
    public HelloworldController(HelloworldService helloworldService) {
        this.helloworldService = helloworldService;
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(name = "createNewUser", absolute = true)
    public ResponseEntity<Object> createNewSignature(@RequestParam(value = "signature", required = false) MultipartFile signatureFile) {
        try {
            final String fileName = signatureFile.getOriginalFilename();
            helloworldService.createUser();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (final Exception exception) {
            log.error("Exception during adding signature to user.", exception);
            throw new IllegalStateException("Failed to load signature");
        }
    }

}
