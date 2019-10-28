package com.schien.demo;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//https://stackoverflow.com/questions/13364112/spring-profiles-and-testing
@ActiveProfiles("test") // use application-test.yml properties (in-memory DB)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class, // use transactional test execution
        DbUnitTestExecutionListener.class}) // to read datasets from file
@Transactional // rollback DB in between tests
@SpringBootTest
public class DemoApplicationTests {

    private MockMvc mvc;

    @Autowired
    private LecturerRestController lecturerRestController;


    @Autowired
    LecturerRepository lecturerRepository;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(lecturerRestController).build();
    }

    @Test
    @DatabaseSetup("/lecturer_test.xml") // read dataset from file
    public void basic_test() throws Exception {
        Optional<Lecturer> lecturer = lecturerRepository.findById(1L);
        if (lecturer.isPresent()) {
            Assert.assertTrue(lecturer.get().getFirstName().equals("Dan"));
        }

        MvcResult mvcResult = mvc.perform(get("/api/lecturer/1")).andExpect(status().isOk()).andReturn();
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().equals("{\"id\":1,\"firstName\":\"Dan\",\"lastName\":\"Schien\"}"));
    }


    @Test
    public void saveLecturer() throws Exception {
        mvc.perform(post("/api/savelecturer")
                .contentType(MediaType.APPLICATION_JSON).content(
                        "{\"firstName\":\"Ed\", \"lastName\":\"Sheeran\"}"
                )).andExpect(status().isOk());
    }

}
