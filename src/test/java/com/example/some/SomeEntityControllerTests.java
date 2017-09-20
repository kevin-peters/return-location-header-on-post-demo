package com.example.some;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SomeEntityController.class)
public class SomeEntityControllerTests {

    private static final String LOCATION_TEMPLATE = "http://localhost" + SomeEntityController.PATH + "/%s";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SomeEntityRepository someEntityRepository;

    @Test
    public void locationHeaderIsAvailableOnController() throws Exception {
        final SomeEntity entity = new SomeEntity();
        entity.setId(123L);

        given(someEntityRepository.save(any(SomeEntity.class))).willReturn(entity);

        final ResultActions result = mvc.perform(post(SomeEntityController.PATH).content("{\"value\" : \"someValue\"}").contentType(MediaType.APPLICATION_JSON_VALUE));
        result.andExpect(status().isCreated());
        result.andExpect(header().string(HttpHeaders.LOCATION, String.format(LOCATION_TEMPLATE, entity.getId())));
    }

}
