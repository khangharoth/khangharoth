package com.domain;

/**
 * Created by raj on 02/09/14.
 */
public class TradeKey {

    private final Long id;
    private final Integer version;

    public TradeKey(Long id, Integer version) {
        this.id = id;
        this.version = version;
    }

    public Integer getVersion() {
        return version;
    }

    public Long getId() {
        return id;
    }

    public static TradeKey newKey(Long id, Integer version) {
        return new TradeKey(id, version);
    }
}
