package org.skarpushin.currencyconverter.thirdparty;

import org.skarpushin.currencyconverter.errorhandler.BadRequestException;
import org.skarpushin.currencyconverter.model.ConvertResponse;
import org.skarpushin.currencyconverter.model.SymbolsResponse;
import reactor.core.publisher.Mono;

public interface CurrencyService {

    Mono<ConvertResponse> convert(String from, String to, String amount) throws BadRequestException;

    SymbolsResponse getCurrencies();
}
