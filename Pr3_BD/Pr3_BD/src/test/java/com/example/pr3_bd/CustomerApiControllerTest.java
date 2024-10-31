package com.example.pr3_bd;

import com.example.pr3_bd.Controller.CustomerAPIContoller;
import com.example.pr3_bd.enity.Profile;
import com.example.pr3_bd.enity.customer;
import com.example.pr3_bd.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerAPIContoller.class)
public class CustomerApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCreateCustomer() throws Exception {
        Profile profile = new Profile();
        profile.setId(4L);
        profile.setAddress("Садовая улица, д. 10, посёлок Лунёво, городской округ Химки, Московская область");
        profile.setPhoneNumber("+1 (656) 125-4438");

        customer customer = new customer();
        customer.setId(13L);
        customer.setName("Катерина");
        customer.setEmail("vasiliev321@mail.ru");
        customer.setProfile(profile);
        customer.setOrders(new ArrayList<>());
        when(customerService.createCustomer(any(customer.class))).thenReturn(customer);

        mockMvc.perform(post("/v1/api/cus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Катерина\",\"email\":\"vasiliev321@mail.ru\",\"profile\":{\"id\":4,\"address\":\"Садовая улица, д. 10, посёлок Лунёво, " +
                                "городской округ Химки, Московская область\",\"phoneNumber\":\"+1 (656) 125-4438\"},\"orders\":[]}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("Катерина"))
                .andExpect(jsonPath("$.email").value("vasiliev321@mail.ru"))
                .andExpect(jsonPath("$.profile.id").value(4))
                .andExpect(jsonPath("$.profile.address").value("Садовая улица, д. 10, посёлок Лунёво, городской округ Химки, Московская область"))
                .andExpect(jsonPath("$.profile.phoneNumber").value("+1 (656) 125-4438"));
    }
    @Test
    public void testCreateCustomer2() throws Exception {
        Profile profile = new Profile();
        profile.setId(4L);
        profile.setAddress("Садовая улица, д. 10, посёлок Лунёво, городской округ Химки, Московская область");
        profile.setPhoneNumber("+1 (656) 125-4438");

        customer customer = new customer();
        customer.setId(13L);
        customer.setName("Катерина");
        customer.setEmail("vasiliev");
        customer.setProfile(profile);
        customer.setOrders(new ArrayList<>());
        when(customerService.createCustomer(any(customer.class))).thenReturn(customer);

        mockMvc.perform(post("/v1/api/cus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Катерина\",\"email\":\"vasiliev321@mail.ru\",\"profile\":{\"id\":4,\"address\":\"Садовая улица, д. 10, посёлок Лунёво, " +
                                "городской округ Химки, Московская область\",\"phoneNumber\":\"+1 (656) 125-4438\"},\"orders\":[]}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("Катерина"))
                .andExpect(jsonPath("$.email").value("vasiliev321@mail.ru"))
                .andExpect(jsonPath("$.profile.id").value(4))
                .andExpect(jsonPath("$.profile.address").value("Садовая улица, д. 10, посёлок Лунёво, городской округ Химки, Московская область"))
                .andExpect(jsonPath("$.profile.phoneNumber").value("+1 (656) 125-4438"));
    }
}
