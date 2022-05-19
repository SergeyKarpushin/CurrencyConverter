package org.skarpushin.currencyconverter.thirdparty;

import org.skarpushin.currencyconverter.errorhandler.BadRequestException;
import org.skarpushin.currencyconverter.model.ConvertResponse;

import java.math.BigDecimal;

public interface CurrencyService {

    ConvertResponse convert(String from, String to, BigDecimal amount) throws BadRequestException;
}
