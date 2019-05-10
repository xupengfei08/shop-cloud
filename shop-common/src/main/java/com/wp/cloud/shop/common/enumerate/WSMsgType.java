package com.wp.cloud.shop.common.enumerate;

public enum WSMsgType {

    PING(1001), UPLOAD_SNAP(1002);

    private int value;

    private WSMsgType(int value) {
        this.value = value;
    }
}
