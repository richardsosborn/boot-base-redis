package com.imp.base;

import com.imp.base.controller.RedisController;
import com.imp.base.repository.RedisRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(RedisController.class)
public class RedisApplicationTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RedisRepository redisRepository;

    @Test
	public void writeStaticDataTest() {
        RedisController redisController = new RedisController();
        ResponseEntity response = redisController.addDataObject("123412", "1234123412");
	}

}
