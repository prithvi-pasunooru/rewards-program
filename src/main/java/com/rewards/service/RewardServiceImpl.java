
package com.rewards.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rewards.dto.CustomerRewardResponse;
import com.rewards.entity.Transaction;
import com.rewards.repository.TransactionRepository;

@Service
public class RewardServiceImpl implements RewardService {
	private final TransactionRepository repo;
	@Value("${rewards.months}")
	private int months;

	public RewardServiceImpl(TransactionRepository r) {
		this.repo = r;
	}

	int calc(double amt) {
		int p = 0;
		if (amt > 100) {
			p += 50 + (int) ((amt - 100) * 2);
		} else if (amt > 50) {
			p += (int) (amt - 50);
		}
		return p;
	}

	public List<CustomerRewardResponse> buildRewardResponse(List<Transaction> transactions) {

		return transactions.stream().collect(Collectors.groupingBy(Transaction::getCustomerId)).entrySet().stream()
				.map(entry -> {

					Long customerId = entry.getKey();

					Map<String, Integer> monthlyRewards = entry.getValue().stream()
							.collect(Collectors.groupingBy(t -> YearMonth.from(t.getTransactionDate()).toString(),
									Collectors.summingInt(t -> calc(t.getAmount()))));

					int total = monthlyRewards.values().stream().mapToInt(Integer::intValue).sum();

					return new CustomerRewardResponse(customerId, monthlyRewards, total);
				}).collect(Collectors.toList());
	}

	@Override
	public List<CustomerRewardResponse> getAll() {

	    LocalDate endDate = LocalDate.now();
	    LocalDate startDate = endDate.minusMonths(months);

	    List<Transaction> filtered = repo.findAll()
	            .stream()
	            .filter(t -> !t.getTransactionDate().isBefore(startDate)
	                      && !t.getTransactionDate().isAfter(endDate))
	            .toList();

	    return buildRewardResponse(filtered);
	}

	@Override
	public List<CustomerRewardResponse> getCustomer(Long id) {

	    LocalDate endDate = LocalDate.now();
	    LocalDate startDate = endDate.minusMonths(months);

	    List<Transaction> list = repo.findByCustomerId(id)
	            .stream()
	            .filter(t -> !t.getTransactionDate().isBefore(startDate)
	                      && !t.getTransactionDate().isAfter(endDate))
	            .toList();

	    if (list.isEmpty()) {
	        throw new RuntimeException("Customer not found");
	    }

	    return buildRewardResponse(list);
	}

}
