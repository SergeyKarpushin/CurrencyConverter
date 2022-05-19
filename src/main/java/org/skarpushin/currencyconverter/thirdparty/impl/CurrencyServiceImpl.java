package org.skarpushin.currencyconverter.thirdparty.impl;

import org.skarpushin.currencyconverter.errorhandler.BadRequestException;
import org.skarpushin.currencyconverter.model.ConvertResponse;
import org.skarpushin.currencyconverter.thirdparty.CurrencyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Value("${external.api.key}")
    private String apikey;

    @Value("${external.api.timeout}")
    private long timeout;

    @Override
    public ConvertResponse convert(String from, String to, BigDecimal amount) throws BadRequestException {
        // call to external API to get the converted currency result
        WebClient client = WebClient.create();
        ResponseEntity<ConvertResponse> response = client.get()
                .uri("https://api.apilayer.com/exchangerates_data/convert?to=" + to + "&from=" + from + "&amount=" + amount)
                .header("apikey", apikey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(
                        status -> status.value() == HttpStatus.BAD_REQUEST.value(),
                        clientResponse -> Mono.empty()
                )
                .toEntity(ConvertResponse.class)
                .block(Duration.ofSeconds(timeout));  //might want to do it asynchronously instead

        if (response.getStatusCodeValue() == HttpStatus.BAD_REQUEST.value()) {
            throw new BadRequestException("Can't process currency conversion request");
        }

        return response.getBody();
    }
}
