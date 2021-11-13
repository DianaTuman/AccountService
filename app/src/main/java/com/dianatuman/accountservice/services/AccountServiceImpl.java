package com.dianatuman.accountservice.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author Diana Tumanian dianatumanian@gmail.com
 */
@Service
public class AccountServiceImpl implements AccountService{

    HashMap<Integer, Long> accounts = new HashMap<>();

    @Override
    public Long getAmount(Integer id) {
        if (accounts.containsKey(id))
            return accounts.get(id);
        else
            return 0l;
    }

    @Override
    public void addAmount(Integer id, Long value) {
        if (!accounts.containsKey(id))
            accounts.put(id, value);
        else {
            Long prevValue = accounts.get(id);
            accounts.put(id, prevValue+value);
        }
    }
}
