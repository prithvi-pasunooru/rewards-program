package com.rewards.controller;

import com.rewards.service.RewardService;
import com.rewards.dto.CustomerRewardResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.MockMvc;

/**
 * Controller tests using MockMvc
 */
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardService service;

    @Test
    void testGetAllCustomers() throws Exception {

        CustomerRewardResponse response =
                new CustomerRewardResponse(
                        1L,
                        Map.of("2026-01", 90),
                        90
                );

        when(service.getAll()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerId").value(1));
    }

    @Test
    void testGetCustomerById() throws Exception {

        CustomerRewardResponse response =
                new CustomerRewardResponse(
                        1L,
                        Map.of("2026-01", 90),
                        90
                );

        when(service.getCustomer(1L)).thenReturn(List.of(response));

        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].totalRewards").value(90));
    }

    @Test
    void testCustomerNotFound() throws Exception {

        when(service.getCustomer(99L))
                .thenThrow(new RuntimeException("Customer not found"));

        mockMvc.perform(get("/api/customers/99"))
                .andExpect(status().isInternalServerError());
    }
}