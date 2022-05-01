package org.jointheleague.api.yak.cityweather.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HomeController.class)
public class HomeControllerIntTest {
    @Autowired
    private MockMvc mockMvc;
}
