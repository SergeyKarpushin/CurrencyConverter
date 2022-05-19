package org.skarpushin.currencyconverter.model;

import java.math.BigDecimal;
import java.util.Date;

public class ConvertResponse {
    private boolean success;
    private ConvertQuery query;
    private ConvertInfo info;
    private Date date;
    private BigDecimal result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ConvertQuery getQuery() {
        return query;
    }

    public void setQuery(ConvertQuery query) {
        this.query = query;
    }

    public ConvertInfo getInfo() {
        return info;
    }

    public void setInfo(ConvertInfo info) {
        this.info = info;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }
}
