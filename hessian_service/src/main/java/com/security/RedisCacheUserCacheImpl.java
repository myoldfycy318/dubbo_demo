package com.security;

import com.alibaba.dubbo.common.utils.Assert;
import com.alibaba.fastjson.JSONObject;
import com.comm.utils.ObjectsTranscoder;
import com.comm.utils.RedisUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * RedisCacheUserCache
 *
 * @author Zhang ShanMin
 * @date 2016/4/6
 * @time 10:54
 */
@Component("redisCacheUserCache")
public class RedisCacheUserCacheImpl implements UserCache, InitializingBean {

    @Resource(name = "redisUtil")
    private RedisUtil redisUtil;

    private String key_prix = "hessian_service.cacheuser.";

    private static ObjectsTranscoder<UserDetails> objectsTranscoder = new ObjectsTranscoder<UserDetails>();

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(redisUtil, "redisUtils is null");
    }

    @Override
    public UserDetails getUserFromCache(String userName) {
        String key = key_prix + userName;
        byte[] val = redisUtil.getBytes(key.getBytes());
        UserDetails userDetails = objectsTranscoder.deserialize(val);
        System.out.println("getUserFromCache,userName=" + userName);
        System.out.println(",userDetails=" + userDetails);
        return userDetails;
    }

    @Override
    public void putUserInCache(UserDetails userDetails) {
        System.out.println("userDetails.getUsername=" + userDetails.getUsername());
        byte[] bytes = objectsTranscoder.serialize(userDetails);
        String key = key_prix + userDetails.getUsername();
        redisUtil.setex(key.getBytes(), 60 * 5, bytes);
    }

    @Override
    public void removeUserFromCache(String s) {
        redisUtil.del();
    }
}
