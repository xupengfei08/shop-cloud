package com.wp.cloud.shop;

public enum ConnectionType {

    USER(10), GROUP(20), CUSTOMER(30);

    private int value;
    
    private ConnectionType(int value) {
        this.value = value;
    }
}
