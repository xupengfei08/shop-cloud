package com.wp.cloud.shop.service;

public interface IRedisSessionService {

    void add(String name, String wsSessionId);

    boolean del(String name);

    String get(String name);
}
