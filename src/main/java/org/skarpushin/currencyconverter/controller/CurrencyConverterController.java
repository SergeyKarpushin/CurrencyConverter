package org.skarpushin.currencyconverter.controller;

import org.skarpushin.currencyconverter.thirdparty.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@RestController
@Validated
public class CurrencyConverterController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/convert")
    public String convertCurrency(@Valid @NotBlank @RequestParam String from,
                                  @Valid @NotBlank @RequestParam String to,
                                  @Valid @NotNull @RequestParam BigDecimal monetaryValue) {
        return currencyService.convert(from, to, monetaryValue).toString();
    }
}
