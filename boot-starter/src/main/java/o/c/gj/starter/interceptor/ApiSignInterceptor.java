package o.c.gj.starter.interceptor;

import o.c.gj.cons.CustomConstant;
import o.c.gj.cons.GatewayResp;
import o.c.gj.gf.oauth2.Client;
import o.c.gj.gf.oauth2.api.Oauth2Service;
import o.c.gj.st.util.RespUtils;
import o.c.gj.st.util.SignUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class ApiSignInterceptor implements HandlerInterceptor {


    @Autowired(required = false)
    private Oauth2Service oauthService;

    private static final Logger logger = LoggerFactory.getLogger(ApiSignInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(CustomConstant.Http.Header.TOKEN);
        if (StringUtils.isEmpty(token)) {
            logger.error("token 不存在");
            RespUtils.writeResp(response, GatewayResp.TOKEN_NO_EXISTS);
            return false;
        }
        Client client = oauthService.checkToken(token);
        if (Objects.isNull(client)) {
            logger.error("无效token");
            RespUtils.writeResp(response, GatewayResp.TOKEN_INVALID);
            return false;
        }
        String requestBody = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
        String hmac = request.getHeader(CustomConstant.Http.Header.HMAC_SHA256);
        if (!SignUtils.is_check_hmac(client.getClientSecret(), requestBody, hmac)) {
            logger.error("签名校验不通过:sign:{},client:{},content:{}", hmac, client, requestBody);
            RespUtils.writeResp(response, GatewayResp.SIGNATURE_INVALID);
            return false;
        }
        request.setAttribute(CustomConstant.Http.Request.ONCE_PER_REQUEST_CLIENT, client);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Client client = (Client) request.getAttribute(CustomConstant.Http.Request.ONCE_PER_REQUEST_CLIENT);
        if (Objects.nonNull(client) && (response instanceof ContentCachingResponseWrapper)) {
            ContentCachingResponseWrapper respWrapper = (ContentCachingResponseWrapper) response;
            String respBody = new String(respWrapper.getContentAsByteArray(), respWrapper.getCharacterEncoding());
            if (StringUtils.isNotEmpty(respBody)) {
                String sign = SignUtils.hmacSign(client.getClientSecret(), respBody);
                response.setHeader(CustomConstant.Http.Header.HMAC_SHA256, sign);
            }
        }
    }
}
