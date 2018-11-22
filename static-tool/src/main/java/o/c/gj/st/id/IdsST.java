package o.c.gj.st.id;

import o.c.gj.cons.CustomConstant;
import o.c.gj.cons.SymbolConstant;
import o.c.gj.redis.RedisWrapService;
import org.apache.commons.collections4.MapUtils;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ST: static tool
 */
public class IdsST {

    public static String serviceId;

    private static RedisWrapService redisWrapService;

    private static volatile boolean initDoor = true;

    private static volatile int platformId = CustomConstant.Self.Work.INIT_PLATFORM_ID;

    private static Object LOCK_FOR_PLATFORM = new Object();

    private static Object LOCK_FOR_WORK = new Object();

    private static final ConcurrentHashMap<Long, SnowflakeIdGenerator> snowflakeIdFactories = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, Long> redisKeyPairs = new ConcurrentHashMap<>();

    private static String servicePre;

    public synchronized static void init(String serviceId, RedisWrapService redisWrapService) {
        if (initDoor) {
            IdsST.serviceId = serviceId;
            IdsST.redisWrapService = redisWrapService;
            servicePre = serviceId + SymbolConstant.COLON_DOUBLE;
            initDoor = false;
        }
    }

    public static int getPlatformId() {
        if (platformId == CustomConstant.Self.Work.INIT_PLATFORM_ID) {
            if (!initDoor) {
                synchronized (LOCK_FOR_PLATFORM) {
                    platformId = redisWrapService.getServerId(serviceId);
                }
            } else {
                throw new IllegalArgumentException("没有初始化工厂");
            }
        }
        return platformId;
    }

    public static SnowflakeIdGenerator loadByWorkId(long workId) {

        if (!snowflakeIdFactories.containsKey(workId) && !initDoor) {
            synchronized (LOCK_FOR_WORK) {
                SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(workId, getPlatformId());
                snowflakeIdFactories.put(workId, snowflakeIdGenerator);
            }
        }
        return snowflakeIdFactories.get(workId);
    }

    public static long nextId(long workId) {
        SnowflakeIdGenerator snowflakeIdGenerator = loadByWorkId(workId);
        return snowflakeIdGenerator.nextId();
    }

    public static Date findIdTime(long workId, long id) {
        SnowflakeIdGenerator snowflakeIdGenerator = loadByWorkId(workId);
        return snowflakeIdGenerator.findIdTime(id);
    }

    public static void loadRedisPair(Map<String, Long> pairs) {
        if (MapUtils.isNotEmpty(pairs)) {
            pairs.forEach((key, value) -> redisKeyPairs.put(key, value));
        }
    }

    public static void addPair(String key, long value) {
        if (!redisKeyPairs.containsKey(key)) {
            redisKeyPairs.put(key, value);
        }
    }

    public static long curval(String key) {
        if (!initDoor) {
            return redisWrapService.curval(serviceId, key);
        }
        throw new IllegalArgumentException("没有初始化工厂");
    }

    public static long nextval(String key) {

        long temp = CustomConstant.Self.Work.MAX_REDIS_SEQ;
        if (redisKeyPairs.containsKey(key)) {
            temp = redisKeyPairs.get(key);
        }
        return nextval(key, temp, false);
    }

    public static long nextval(String key, long max) {
        return nextval(key, max, true);
    }

    public static long nextval(String key, long max, boolean shouldTry) {
        if (!initDoor) {
            long temp = max;
            if (shouldTry && redisKeyPairs.containsKey(key)) {
                temp = redisKeyPairs.get(key);
            }
            return redisWrapService.nextval(serviceId, key, temp);
        }
        throw new IllegalArgumentException("没有初始化工厂");
    }

    public static boolean lock(String key, String content, long timeout) {
        if (!initDoor) {
            return redisWrapService.lock(
                    servicePre + key,
                    content, timeout);
        }
        throw new IllegalArgumentException("没有初始化工厂");
    }

    public static boolean unlock(String key) {
        if (!initDoor) {
            return redisWrapService.unlock(servicePre + key);
        }
        throw new IllegalArgumentException("没有初始化工厂");
    }


}
