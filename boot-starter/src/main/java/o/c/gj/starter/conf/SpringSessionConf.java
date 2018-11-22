package o.c.gj.starter.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionExpiredEvent;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 120)
public class SpringSessionConf {
    private static final Logger logger = LoggerFactory.getLogger(SpringSessionConf.class);

    @Bean
    public ThreadPoolTaskScheduler springSessionRedisTaskExecutor() {
        ThreadPoolTaskScheduler taskSchedule = new ThreadPoolTaskScheduler();
        taskSchedule.setPoolSize(3);
        return taskSchedule;
    }


    @Bean
    public DefaultCookieSerializer defaultCookieSerializer() {
        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
        defaultCookieSerializer.setCookiePath("/");
        return defaultCookieSerializer;
    }


    @EventListener
    public void onSessionExpired(SessionExpiredEvent expiredEvent) {
        String sessionId = expiredEvent.getSessionId();
        logger.info(expiredEvent.getSession().getAttribute("user"));
        logger.info("[{}]session过期", sessionId);
    }

    @EventListener
    public void onSessionDeleted(SessionDeletedEvent deletedEvent) {
        String sessionId = deletedEvent.getSessionId();
        logger.info(deletedEvent.getSession().getAttribute("user"));
        logger.info("删除session[{}]", sessionId);
    }

    @EventListener
    public void onSessionCreated(SessionCreatedEvent createdEvent) {
        String sessionId = createdEvent.getSessionId();
        logger.info(createdEvent.getSession().getAttribute("user"));
        logger.info("保存session[{}]", sessionId);
    }
}
