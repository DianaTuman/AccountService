package com.dianatuman.accountservice.controllers;

import com.dianatuman.accountservice.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Diana Tumanian dianatumanian@gmail.com
 */
@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ResponseBody
    @GetMapping(value = "/getAmount")
    public ResponseEntity<Long> getAmount(@RequestParam Integer id) {
        return new ResponseEntity<>(accountService.getAmount(id), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/addAmount")
    public ResponseEntity<Long> addAmount(@RequestParam Integer id, @RequestParam Long value){
        accountService.addAmount(id, value);
        return new ResponseEntity<>(accountService.getAmount(id), HttpStatus.OK);
    }
}
