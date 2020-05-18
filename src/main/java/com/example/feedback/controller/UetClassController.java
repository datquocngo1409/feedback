package com.example.feedback.controller;

import com.example.feedback.model.UetClass;
import com.example.feedback.service.UetClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class UetClassController {
    @Autowired
    public UetClassService uetClassService;

    @RequestMapping(value = "/uetClasses/rate", method = RequestMethod.POST)
    public ResponseEntity<UetClass> rate(@RequestBody Map<String, String> map) {
        int uetClassId = Integer.parseInt(map.get("uetClassId"));
        double value = Double.parseDouble(map.get("value"));
        UetClass uetClass = uetClassService.findById(uetClassId);
        double rateAverage = (uetClass.getRateAverage() * uetClass.getCountRate() + value) / (uetClass.getCountRate() + 1);
        uetClass.setCountRate(uetClass.getCountRate() + 1);
        uetClass.setRateAverage(rateAverage);
        uetClassService.save(uetClass);
        return new ResponseEntity<UetClass>(uetClass, HttpStatus.OK);
    }

    @RequestMapping(value = "/uetClasses", method = RequestMethod.GET)
    public ResponseEntity<List<UetClass>> listAllUetClasses() {
        List<UetClass> accounts = uetClassService.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<UetClass>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<UetClass>>(accounts, HttpStatus.OK);
    }

    @RequestMapping(value = "/uetClasses/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UetClass> getUetClassById(@PathVariable("id") int id) {
        System.out.println("Fetching UetClass with id " + id);
        UetClass account = uetClassService.findById(id);
        if (account == null) {
            System.out.println("UetClass with id " + id + " not found");
            return new ResponseEntity<UetClass>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UetClass>(account, HttpStatus.OK);
    }

    @RequestMapping(value = "/uetClasses", method = RequestMethod.POST)
    public ResponseEntity<Void> createUetClass(@RequestBody UetClass uetClass, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating UetClass " + uetClass.getDisplayName());
        uetClassService.save(uetClass);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/uetClasses/{id}").buildAndExpand(uetClass.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/uetClasses/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<UetClass> updateAdmin(@PathVariable("id") int id, @RequestBody UetClass uetClass) {
        System.out.println("Updating UetClass " + id);

        UetClass curremUetClass = uetClassService.findById(id);

        if (curremUetClass == null) {
            System.out.println("UetClass with id " + id + " not found");
            return new ResponseEntity<UetClass>(HttpStatus.NOT_FOUND);
        }

        curremUetClass = uetClass;

        uetClassService.save(curremUetClass);
        return new ResponseEntity<UetClass>(curremUetClass, HttpStatus.OK);
    }

    @RequestMapping(value = "/uetClasses/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UetClass> deleteUetClass(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting UetClass with id " + id);

        UetClass uetClass = uetClassService.findById(id);
        if (uetClass == null) {
            System.out.println("Unable to delete. UetClass with id " + id + " not found");
            return new ResponseEntity<UetClass>(HttpStatus.NOT_FOUND);
        }

        uetClassService.delete(id);
        return new ResponseEntity<UetClass>(HttpStatus.NO_CONTENT);
    }
}
