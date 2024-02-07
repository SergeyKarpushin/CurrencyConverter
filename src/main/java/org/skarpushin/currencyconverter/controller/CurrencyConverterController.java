package org.skarpushin.currencyconverter.controller;

import org.skarpushin.currencyconverter.model.ConvertResponse;
import org.skarpushin.currencyconverter.thirdparty.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@Validated
public class CurrencyConverterController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/convert")
    public ConvertResponse convertCurrency(@Valid @NotBlank @RequestParam String from,
                                           @Valid @NotBlank @RequestParam String to,
                                           @Valid @NotBlank @RequestParam String monetaryValue) {
        Mono<ConvertResponse> responseMono = currencyService.convert(from, to, monetaryValue);

        responseMono.subscribe(response -> {
            // Handle the conversion response here
            System.out.println("Converted amount from " + from + " to " + to + ": " + response.getResult());
        }, error -> {
            // Handle any errors that occurred during the conversion call
            System.out.println("Error during conversion: " + error.getMessage());
        });
        return null;
    }
}
