package com.api.dto;

public class CatQuoteDTO {

    private String fact;
    private int length;

    public String getFact() {
        return fact;
    }

    public CatQuoteDTO setFact(String fact) {
        this.fact = fact;
        return this;
    }

    public int getLength() {
        return length;
    }

    public CatQuoteDTO setLength(int length) {
        this.length = length;
        return this;
    }
}
