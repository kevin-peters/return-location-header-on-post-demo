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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReturnLocationHeaderOnPostDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SomeEntityRepositoryTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void locationHeaderIsAvailableOnController() throws Exception {
        final SomeEntity entity = new SomeEntity();
        entity.setId(123L);

        final ResponseEntity<SomeEntity> responseEntity = restTemplate.postForEntity("http://localhost:8080/someEntities", entity, SomeEntity.class);
        assertThat(responseEntity.getHeaders().get("Location").get(0)).isEqualTo("http://localhost:8080/someEntities/1");
    }

}
