package com.aliyun.dingtalk.cache;

import com.dingtalk.api.response.OapiProcessinstanceGetResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalCache {

    private static final LocalCache instance = new LocalCache();

    private Map<String, OapiProcessinstanceGetResponse.ProcessInstanceTopVo> cacheMap = new ConcurrentHashMap<>();

    private LocalCache() {
    }

    public static LocalCache getInstance() {
        return instance;
    }

    public Map<String, OapiProcessinstanceGetResponse.ProcessInstanceTopVo> getCacheMap() {
        return cacheMap;
    }
}
