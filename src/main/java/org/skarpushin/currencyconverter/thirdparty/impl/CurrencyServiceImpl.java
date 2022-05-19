package org.skarpushin.currencyconverter.thirdparty.impl;

import org.skarpushin.currencyconverter.model.ConvertResponse;
import org.skarpushin.currencyconverter.thirdparty.CurrencyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Value("${external.api.key}")
    private String apikey;

    @Override
    public BigDecimal convert(String from, String to, BigDecimal amount) {
        // call to external API to get the converted currency result
        WebClient client = WebClient.create();
        ConvertResponse response = client.get()
                .uri("https://api.apilayer.com/exchangerates_data/convert?to=" + to + "&from=" + from + "&amount=" + amount)
                .header("apikey", apikey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ConvertResponse.class)
                .block();  //might want to do it asynchronously instead
        //TODO: add error handling
        return response.getResult();
    }
}
