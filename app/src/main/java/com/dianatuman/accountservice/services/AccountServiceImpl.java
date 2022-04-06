package com.dianatuman.accountservice.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccountServiceImpl implements AccountService {

    private static final long DEFAULT_VALUE = 0L;
    private final ConcurrentHashMap<Integer, Long> accounts = new ConcurrentHashMap<>();

    @Override
    public Long getAmount(Integer id) {
        return accounts.getOrDefault(id, DEFAULT_VALUE);
    }

    @Override
    public void addAmount(Integer id, Long value) {
        if (value < 0) {
            withdrawMoney(id, value);
        } else {
            accounts.compute(id, (key, val) -> (val == null) ? (DEFAULT_VALUE + value) : (val + value));
        }
    }

    private void withdrawMoney(Integer id, Long value) {
        accounts.compute(id,
                (key, val) -> (val == null || val < -value) ? throwNotEnoughMoney(key) : (val + value));
    }

    private Long throwNotEnoughMoney(Integer id) {
        throw new IllegalArgumentException("There is not enough money on the account with an id " + id);
    }
}
