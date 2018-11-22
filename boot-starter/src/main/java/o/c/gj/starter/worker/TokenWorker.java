package o.c.gj.starter.worker;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import com.github.benmanes.caffeine.cache.LoadingCache;
import o.c.gj.gf.oauth2.Authorize;
import o.c.gj.gf.oauth2.GrantType;
import o.c.gj.gf.oauth2.TokenResp;
import o.c.gj.gf.oauth2.api.Oauth2Service;
import o.c.gj.st.id.IdsST;
import o.c.gj.st.json.GsonHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Configuration
@Component
public class TokenWorker {

    private final static String STATUS = "status";

    private final static String TOKEN_KEY = "::token";

    @Autowired(required = false)
    private Oauth2Service oauth2Service;

    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    private Authorize load(String serviceId) {
        Authorize authorize = new Authorize();
        authorize.setClientId(serviceId);
        authorize.setGrantType(GrantType.client);
        authorize.setStatus(String.valueOf(IdsST.nextval(STATUS)));
        return authorize;
    }

    private TokenResp loadRedis(String serviceId) {
        TokenResp result = null;
        String tokenKey = serviceId + TOKEN_KEY;
        String resp = (String) redisTemplate.opsForValue().get(tokenKey);
        if (StringUtils.isNotEmpty(resp)) {
            result = GsonHolder.fromJson(resp, TokenResp.class);
        }
        return result;
    }

    private synchronized TokenResp loadByOauthServer(String serviceId) {
        Authorize authorize = load(serviceId);
        String tokenKey = serviceId + TOKEN_KEY;
        TokenResp tokenResp = oauth2Service.token(authorize);
        redisTemplate.opsForValue().set(tokenKey, GsonHolder.toJson(tokenResp), tokenResp.getExpireIn() - 1, TimeUnit.SECONDS);
        return tokenResp;
    }

    private TokenResp token(String serviceId) {
        TokenResp resp = loadRedis(serviceId);
        if (Objects.isNull(resp)) {
            resp = loadByOauthServer(serviceId);
        }
        return resp;
    }

    public TokenResp token() {
        return loadingCache.get(IdsST.serviceId);
    }


    LoadingCache<String, TokenResp> loadingCache = Caffeine.newBuilder()
            .expireAfter(new Expiry<String, TokenResp>() {
                @Override
                public long expireAfterCreate(@Nonnull String s, @Nonnull TokenResp tokenResp, long l) {
                    int seconds = tokenResp.getExpireIn() - 1;
                    return TimeUnit.SECONDS.toNanos(seconds);
                }

                @Override
                public long expireAfterUpdate(@Nonnull String s, @Nonnull TokenResp tokenResp, long l, long l1) {
                    int seconds = tokenResp.getExpireIn() - 1;
                    return TimeUnit.SECONDS.toNanos(seconds);
                }

                @Override
                public long expireAfterRead(@Nonnull String s, @Nonnull TokenResp tokenResp, long l, long l1) {
                    int seconds = tokenResp.getExpireIn() - 1;
                    return TimeUnit.SECONDS.toNanos(seconds);
                }
            })
            .build(key -> token(key));


}
