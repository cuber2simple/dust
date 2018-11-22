package o.c.gj.starter.interceptor;

import o.c.gj.cons.CustomConstant;
import o.c.gj.cons.GatewayResp;
import o.c.gj.redis.RedisWrapService;
import o.c.gj.st.id.IdsST;
import o.c.gj.st.util.HttpUtils;
import o.c.gj.st.util.RespUtils;
import o.c.gj.st.util.SignUtils;
import o.c.gj.starter.filter.StubLoggingFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnBean({StubLoggingFilter.class, RedisWrapService.class})
@ConditionalOnExpression("${duplicate.enabled: true}")
@ConfigurationProperties("duplicate")
@Component
public class DuplicateReqInterceptor implements HandlerInterceptor {
    private Map<String, Long> lockMap = new HashMap<>();
    private static final long defaultLockTime = 30 * 1000;
    private static final String PRE_DUPLICATE_LOCK = "duplicate::";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        long timeout = defaultLockTime;
        if (lockMap.containsKey(url)) {
            timeout = lockMap.get(url);
        }
        Map<String, String> allContents = HttpUtils.generate(request);
        String md5Sign = PRE_DUPLICATE_LOCK + SignUtils.generateToken(allContents);
        if (!IdsST.lock(md5Sign, md5Sign, timeout)) {
            RespUtils.writeResp(response, GatewayResp.REQUEST_DUPLICATED);
            return false;
        }
        request.setAttribute(CustomConstant.Http.Request.ONCE_PER_REQUEST_MD5, md5Sign);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String md5Sign = (String) request.getAttribute(CustomConstant.Http.Request.ONCE_PER_REQUEST_MD5);
        IdsST.unlock(md5Sign);
    }
}
