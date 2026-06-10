package com.rewards.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test.
 */
@SpringBootTest
@AutoConfigureMockMvc
class RewardsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAllCustomersAPI() throws Exception {
        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk());
    }

    @Test
    void testSingleCustomerAPI() throws Exception {
        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk());
    }
}