package com.dianatuman.accountservice.controllers;

import com.dianatuman.accountservice.exceptions.ExceptionAdvice;
import com.dianatuman.accountservice.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;
    Logger logger = LoggerFactory.getLogger(AccountController.class);


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
        logger.info("value " + value.toString());
        accountService.addAmount(id, value);
        return accountService.getAmount(id);
    }
}
