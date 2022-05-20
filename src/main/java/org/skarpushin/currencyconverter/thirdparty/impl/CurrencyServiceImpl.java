package org.skarpushin.currencyconverter.thirdparty.impl;

import org.skarpushin.currencyconverter.errorhandler.BadRequestException;
import org.skarpushin.currencyconverter.model.ConvertResponse;
import org.skarpushin.currencyconverter.model.SymbolsResponse;
import org.skarpushin.currencyconverter.thirdparty.CurrencyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.skarpushin.currencyconverter.thirdparty.Constants.CONVERT_URI;
import static org.skarpushin.currencyconverter.thirdparty.Constants.SYMBOLS_URI;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Value("${external.api.key}")
    private String apikey;

    @Value("${external.api.timeout}")
    private long timeout;

    @Override
    public ConvertResponse convert(String from, String to, String amount) {
        // call to external API to get the converted currency result
        WebClient client = WebClient.create();

        ConvertResponse response = client.get()
                .uri(String.format(CONVERT_URI, from, to, amount))
                .header("apikey", apikey)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().isError()) {
                        return clientResponse.bodyToMono(String.class)
                                .flatMap(resp -> Mono.error(new BadRequestException(resp)));
                    }
                    return clientResponse.bodyToMono(ConvertResponse.class);
                })
                .block(Duration.ofSeconds(timeout));  //might want to do it asynchronously instead

        return response;
    }

    @Override
    public SymbolsResponse getCurrencies() {
        WebClient client = WebClient.create();

        SymbolsResponse response = client.get()
                .uri(SYMBOLS_URI)
                .header("apikey", apikey)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().isError()) {
                        return clientResponse.bodyToMono(String.class)
                                .flatMap(resp -> Mono.error(new BadRequestException(resp)));
                    }
                    return clientResponse.bodyToMono(SymbolsResponse.class);
                })
                .block(Duration.ofSeconds(timeout));

        return response;
    }
}
