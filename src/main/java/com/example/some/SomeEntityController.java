package com.example.some;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class SomeEntityController {

    public static final String PATH = "/someEntitiesViaController";

    @Autowired
    private SomeEntityRepository someEntityRepository;

    @RequestMapping(path = PATH, method = RequestMethod.POST)
    public ResponseEntity<SomeEntity> createCustomer(final @RequestBody SomeEntity someEntity, final UriComponentsBuilder uriComponentsBuilder) {
        final SomeEntity savedEntity = someEntityRepository.save(someEntity);

        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path(PATH + "/{id}").buildAndExpand(savedEntity.getId()).toUri());

        return new ResponseEntity(someEntity, headers, HttpStatus.CREATED);
    }

}
