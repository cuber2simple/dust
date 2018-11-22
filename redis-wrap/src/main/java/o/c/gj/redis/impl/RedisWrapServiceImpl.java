package o.c.gj.redis.impl;

import o.c.gj.cons.CustomConstant;
import o.c.gj.cons.SymbolConstant;
import o.c.gj.redis.RedisWrapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RedisWrapServiceImpl implements RedisWrapService {

    private ConcurrentHashMap<String, RedisScript> loadScripts = new ConcurrentHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(RedisWrapServiceImpl.class);


    public RedisWrapServiceImpl() {
    }

    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    public static final String GET_SERVER_ID = "/lua/getServerId.lua";

    public static final String GET_TIME = "/lua/getTime.lua";

    public static final String LOCK = "/lua/lock.lua";

    public static final String NEXT_VAL = "/lua/nextval.lua";

    public static final String PRE_SERVER_ID = "SERVICE::";

    public static final String PRE_SEQ = "SEQ::";

    public static final String PRE_LOCK = "LOCK::";

    private int maxServiceId = (int) (-1 ^ (-1L << 5));

    public RedisWrapServiceImpl(int maxServiceId) {
        this.maxServiceId = maxServiceId;
    }

    @Override
    public int getServerId(String serviceId) {
        RedisScript<Integer> redisScript = loadByPath(GET_SERVER_ID, Integer.class);
        String serviceNow = PRE_SERVER_ID + serviceId;
        String serviceCur = serviceNow + SymbolConstant.COLON_DOUBLE + CustomConstant.Self.IP_MAC;
        int result = (int) redisTemplate.execute(redisScript, Arrays.asList(serviceCur, serviceNow), maxServiceId);
        return result;
    }

    @Override
    public boolean lock(String key, String content, long timeout) {
        RedisScript<Boolean> redisScript = loadByPath(LOCK, Boolean.class);
        boolean result = (boolean) redisTemplate.execute(redisScript, Arrays.asList(PRE_LOCK + key, content), timeout);
        return result;
    }

    @Override
    public boolean unlock(String key) {
        return redisTemplate.delete(PRE_LOCK + key);
    }

    @Override
    public long curval(String serviceId, String key) {
        String redisKey = redisKey(serviceId, key);
        return Long.parseLong((String) redisTemplate.opsForValue().get(redisKey));
    }

    private String redisKey(String serviceId, String key) {
        return PRE_SEQ + serviceId + SymbolConstant.COLON_DOUBLE + key;
    }

    @Override
    public long nextval(String serviceId, String key, long maxValue) {
        String redisKey = redisKey(serviceId, key);
        RedisScript<Long> redisScript = loadByPath(NEXT_VAL, Long.class);
        long result = (long) redisTemplate.execute(redisScript, Arrays.asList(redisKey), maxValue);
        return result;
    }

    @Override
    public long currentTimeMillis() {
        RedisScript<List> redisScript = loadByPath(GET_TIME, List.class);
        List result = (List) redisTemplate.execute(redisScript, Collections.EMPTY_LIST);
        Assert.isTrue(result.size() == 2, "取redis时间返回是一个list");
        int datetime = (int) result.get(0);
        int microis = (int) result.get(1);
        int millis = microis / 1000;
        logger.warn("使用redis得到时间,去掉了微妙后三位:{}", microis % 1000);
        return datetime * 1000l + millis;
    }

    private synchronized <T> RedisScript<T> loadByPath(String path, Class<T> resultType) {
        if (!loadScripts.containsKey(path)) {
            DefaultRedisScript<T> redisScript = new DefaultRedisScript<>();
            redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(path)));
            redisScript.setResultType(resultType);
            loadScripts.put(path, redisScript);
        }
        return loadScripts.get(path);
    }

}
