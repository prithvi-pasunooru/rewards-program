
package com.rewards.service;

import java.util.List;

import com.rewards.dto.CustomerRewardResponse;

public interface RewardService {
	List<CustomerRewardResponse> getAll();

	List<CustomerRewardResponse> getCustomer(Long id);
}
