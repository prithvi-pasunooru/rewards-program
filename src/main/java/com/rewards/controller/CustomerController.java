
package com.rewards.controller;

import org.springframework.web.bind.annotation.*;

import com.rewards.dto.CustomerRewardResponse;
import com.rewards.service.RewardService;
import java.util.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	private final RewardService service;

	public CustomerController(RewardService s) {
		this.service = s;
	}

	@GetMapping
	public List<CustomerRewardResponse> getAll() {
	    return service.getAll();
	}

	@GetMapping("/{id}")
	public List<CustomerRewardResponse> getCustomer(@PathVariable Long id) {
	    return service.getCustomer(id);
	}

}
