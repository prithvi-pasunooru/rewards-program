package com.rewards.dto;

import java.util.Map;

/**
 * DTO for customer reward response.
 */
public class CustomerRewardResponse {

	private Long customerId;
	private Map<String, Integer> monthlyRewards;
	private int totalRewards;

	public CustomerRewardResponse(Long customerId, Map<String, Integer> monthlyRewards, int totalRewards) {
		this.customerId = customerId;
		this.monthlyRewards = monthlyRewards;
		this.totalRewards = totalRewards;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public Map<String, Integer> getMonthlyRewards() {
		return monthlyRewards;
	}

	public int getTotalRewards() {
		return totalRewards;
	}
}
