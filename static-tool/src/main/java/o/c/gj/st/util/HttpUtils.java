package o.c.gj.st.util;

import o.c.gj.cons.CustomConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(SignUtils.class);

    public static Map<String, String> generate(HttpServletRequest request) {
        Map<String, String> container = new HashMap<>();
        if (Objects.nonNull(request)) {
            String queryString = request.getQueryString();
            container.put(CustomConstant.Http.Request.ONCE_PER_REQUEST_QUERY_STRING, queryString);
            if (request instanceof ContentCachingRequestWrapper) {
                ContentCachingRequestWrapper wrapper = (ContentCachingRequestWrapper) request;
                byte[] content = wrapper.getContentAsByteArray();
                try {
                    String reqBody = new String(content, request.getCharacterEncoding());
                    container.put(CustomConstant.Http.Request.ONCE_PER_REQUEST_BODY, reqBody);
                } catch (Exception e) {
                    logger.error("不可能出错,request 出来的charset 怎么会不存在");
                }
            }
        }
        return container;
    }
}
