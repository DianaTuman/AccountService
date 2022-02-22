package com.dianatuman.accountservice.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.dianatuman.accountservice.services.Utils.randomId;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountServiceTest {

    private final AccountService accountService = new AccountServiceImpl();

    @Test
    public void shouldReturnZeroIfThereWasNoPreviousInteractionsWithAnAccount() {
        // given
        var id = randomId();

        // when
        var actualValue = accountService.getAmount(id);

        // then
        assertEquals(0L, actualValue);
    }

    @Test
    public void shouldNotWithdrawMoneyIfAmountExceedsValueOfTheAccount() {
        // given
        var id = randomId();
        var initialValue = 500L;
        var valueToWithdraw = 1000L;
        accountService.addAmount(id, initialValue);

        // when
        assertThrows(IllegalArgumentException.class, () -> accountService.addAmount(id, -valueToWithdraw));
        var actualValue = accountService.getAmount(id);

        // then
        assertEquals(initialValue, actualValue);
    }

    @ParameterizedTest
    @CsvSource({
            "0,0,0",
            "0,100,100",
            "100,0,100",
            "100,400,500"
    })
    public void shouldHaveAddedAmountOfMoneyOnTheAccountAfterDeposit(Long initialValue, Long addedAmount, Long expectedValue) {
        // given
        var id = randomId();
        accountService.addAmount(id, initialValue);

        // when
        accountService.addAmount(id, addedAmount);
        var actualValue = accountService.getAmount(id);

        // then
        assertEquals(expectedValue, actualValue);
    }

    @ParameterizedTest
    @CsvSource({
            "0,0,0",
            "100,0,100",
            "100,100,0",
            "500,400,100"
    })
    public void shouldHaveRemainingAmountOfMoneyOnTheAccountAfterWithdrawal(Long initialValue, Long withdrawnAmount, Long expectedValue) {
        // given
        var id = randomId();
        accountService.addAmount(id, initialValue);

        // when
        accountService.addAmount(id, -withdrawnAmount);
        var actualValue = accountService.getAmount(id);

        // then
        assertEquals(expectedValue, actualValue);
    }


}
