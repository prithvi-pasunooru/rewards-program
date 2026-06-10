package com.rewards.service;

import com.rewards.entity.Transaction;
import com.rewards.repository.TransactionRepository;
import com.rewards.dto.CustomerRewardResponse;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Unit tests for RewardServiceImpl
 */
@ExtendWith(MockitoExtension.class)
class RewardServiceImplTest {

    @Mock
    private TransactionRepository repo;

    @InjectMocks
    private RewardServiceImpl service;

    @Test
    void testcalcAbove100() {
        assertEquals(90, service.calc(120));
    }

    @Test
    void testcalcBetween50And100() {
        assertEquals(25, service.calc(75));
    }

    @Test
    void testcalcBelow50() {
        assertEquals(0, service.calc(40));
    }

    @Test
    void testGetAllCustomers() {
        List<Transaction> data = List.of(
                new Transaction(1L, 120, LocalDate.of(2026, 1, 10)),
                new Transaction(1L, 75, LocalDate.of(2026, 2, 11))
        );

        when(repo.findAll()).thenReturn(data);

        List<CustomerRewardResponse> result = service.getAll();

        assertFalse(result.isEmpty());
        assertEquals(1L, result.get(0).getCustomerId());
        assertEquals(115, result.get(0).getTotalRewards());
    }

    @Test
    void testCustomerNotFound() {
        when(repo.findByCustomerId(99L)).thenReturn(List.of());

        assertThrows(RuntimeException.class, () -> service.getCustomer(99L));
    }
}