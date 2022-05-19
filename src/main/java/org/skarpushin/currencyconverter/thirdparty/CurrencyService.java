package org.skarpushin.currencyconverter.thirdparty;

import java.math.BigDecimal;

public interface CurrencyService {

    BigDecimal convert(String from, String to, BigDecimal amount);
}
