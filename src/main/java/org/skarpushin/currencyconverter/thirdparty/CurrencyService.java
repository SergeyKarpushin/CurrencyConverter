package org.skarpushin.currencyconverter.thirdparty;

import org.skarpushin.currencyconverter.errorhandler.BadRequestException;
import org.skarpushin.currencyconverter.model.ConvertResponse;
import org.skarpushin.currencyconverter.model.SymbolsResponse;

public interface CurrencyService {

    ConvertResponse convert(String from, String to, String amount) throws BadRequestException;

    SymbolsResponse getCurrencies();
}
