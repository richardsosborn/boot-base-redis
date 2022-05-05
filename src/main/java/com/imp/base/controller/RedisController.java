package com.imp.base.controller;

import java.util.Map;

import com.imp.base.repository.RedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Controller
@RequestMapping("/")
public class RedisController {

    Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisRepository staticDataRepository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/get-all-keys")
    public @ResponseBody Map<Object, Object> getAllKeys() {
        logger.debug("Getting all keys.");
        return staticDataRepository.getAllObjects();
    }

    @GetMapping("/get-all-data-objects")
    public @ResponseBody Map<Object, Object> getAllDataObjects() {
        logger.debug("Getting all data.");
        return staticDataRepository.getAllObjects();
    }

    @PostMapping(value = "/add-data-object")
    public ResponseEntity<String> addDataObject(@RequestParam String key, @RequestParam String value) {
        logger.debug("Adding new data object.");
        staticDataRepository.addObject(key, value);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-data-object-by-key")
    public @ResponseBody Object getDataObjectByKey(@RequestParam String id) {
        logger.debug("Getting one object.");
        return staticDataRepository.getObjectByKey(id);
    }

    @GetMapping(value = "/delete-all-keys")
    public ResponseEntity<String> deleteAllKeys() {
        logger.debug("Deleting key/value.");
        staticDataRepository.deleteAllKeyValues();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/delete-data-object-by-key")
    public ResponseEntity<String> deleteDataObjectByKey(@RequestParam String key) {
        logger.debug("Deleting data object by key.");
        staticDataRepository.deleteByKey(key);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
