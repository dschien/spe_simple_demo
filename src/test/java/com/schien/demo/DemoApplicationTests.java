package com.schien.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//https://stackoverflow.com/questions/13364112/spring-profiles-and-testing
@ActiveProfiles("test") // use application-test.yml properties (in-memory DB)
@SpringBootTest
public class DemoApplicationTests {

    private MockMvc mvc;

    @Autowired
    private LecturerRestController lecturerRestController;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(lecturerRestController).build();
    }

    @Test
    public void saveLecturer() throws Exception {
        mvc.perform(post("/api/savelecturer")
                .contentType(MediaType.APPLICATION_JSON).content(
                        "{\"firstName\":\"Ed\", \"lastName\":\"Sheeran\"}"
                )).andExpect(status().isOk());
    }

}
