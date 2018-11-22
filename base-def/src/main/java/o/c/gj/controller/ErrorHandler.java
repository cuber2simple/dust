package o.c.gj.controller;

import o.c.gj.cons.CustomConstant;
import o.c.gj.cons.GatewayResp;
import o.c.gj.payload.HttpResp;
import o.c.gj.payload.Resp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
@Component
public class ErrorHandler extends DustController implements ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping("/")
    public String login() {
        return CustomConstant.Http.Request.REQUEST_REDIRECT + this.env + "/sso/login.htm";
    }

    private Map<String, Object> getErrorAttributes(WebRequest request, boolean includeStackTrace) {
        return errorAttributes.getErrorAttributes(request, includeStackTrace);
    }

    @RequestMapping(value = "/error", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Resp error(WebRequest webRequest) {
        return buildBody(webRequest, !this.isProduct);
    }

    private Resp buildBody(WebRequest webRequest, boolean includeStackTrace) {
        HttpResp httpResp = new HttpResp();
        Map<String, Object> errorAttributes = getErrorAttributes(webRequest, includeStackTrace);
        int status = (Integer) errorAttributes.get("status");
        String path = (String) errorAttributes.get("path");
        String messageFound = (String) errorAttributes.get("message");
        String message = "";
        if (!StringUtils.isEmpty(path)) {
            message = String.format("Requested path [%s] with result [%s]", path, messageFound);
        }
        if (includeStackTrace) {
            httpResp.setTrace((String) errorAttributes.get("trace"));
        }

        httpResp.setCode(GatewayResp.HTTP_ERROR.getCode());
        httpResp.setHttpStatus(status);
        httpResp.setMsg(message);
        httpResp.setPath(path);
        return httpResp;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
