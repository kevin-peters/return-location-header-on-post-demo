package com.example.some;


import com.example.ReturnLocationHeaderOnPostDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReturnLocationHeaderOnPostDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SomeEntityRepositoryTests {

    private static final String PATH = "http://localhost:8080/someEntities";
    private static final String LOCATION_TEMPLATE = PATH + "/%s";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SomeEntityRepository someEntityRepository;

    @Test
    @Transactional
    public void locationHeaderIsAvailableOnController() throws Exception {
        final ResponseEntity<SomeEntity> responseEntity = restTemplate.postForEntity(PATH, new SomeEntity(), SomeEntity.class);

        final SomeEntity savedEntity = someEntityRepository.findAll().iterator().next();
        assertThat(responseEntity.getHeaders().get(HttpHeaders.LOCATION).get(0)).isEqualTo(String.format(LOCATION_TEMPLATE, savedEntity.getId()));
    }

}
