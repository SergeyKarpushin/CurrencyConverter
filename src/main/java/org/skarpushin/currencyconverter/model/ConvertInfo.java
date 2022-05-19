package org.skarpushin.currencyconverter.model;

import java.math.BigDecimal;

public class ConvertInfo {
    private long timestamp;
    private BigDecimal rate;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
