package com.dianatuman.accountservice.controllers;

import com.dianatuman.accountservice.services.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ResponseBody
    @GetMapping(value = "/balance")
    public Long getAmount(@RequestParam Integer id) {
        return accountService.getAmount(id);
    }

    @ResponseBody
    @PostMapping(value = "/balance")
    public Long addAmount(@RequestParam Integer id, @RequestParam Long value) {
        accountService.addAmount(id, value);
        return accountService.getAmount(id);
    }
}
