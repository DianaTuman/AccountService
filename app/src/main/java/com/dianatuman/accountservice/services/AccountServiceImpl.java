package com.dianatuman.accountservice.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccountServiceImpl implements AccountService {

    private final ConcurrentHashMap<Integer, Long> accounts = new ConcurrentHashMap<>();

    @Override
    public Long getAmount(Integer id) {
        return accounts.getOrDefault(id, 0L);
    }

    @Override
    public void addAmount(Integer id, Long value) {
        if (value < 0) {
            withdrawMoney(id, value);
        } else {
            var currentValue = getAmount(id);
            accounts.put(id, currentValue + value);
        }
    }

    private void withdrawMoney(Integer id, Long value) {
        var currentValue = getAmount(id);
        if (currentValue >= -value) {
            accounts.put(id, currentValue + value);
        } else {
            throw new IllegalArgumentException("There is not enough money on the account with an id " + id);
        }

    }
}
