package o.c.gj.st.util;

import o.c.gj.cons.CustomConstant;
import o.c.gj.cons.GatewayResp;
import o.c.gj.payload.Resp;
import o.c.gj.st.json.GsonHolder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class RespUtils {

    public static Resp generate(GatewayResp gatewayResp) {
        Resp resp = null;
        if (Objects.nonNull(gatewayResp)) {
            resp = new Resp();
            resp.setCode(gatewayResp.getCode());
            resp.setMsg(gatewayResp.getDesc());
        }
        return resp;
    }

    public static String generateJsonResp(GatewayResp gatewayResp) {
        Resp resp = generate(gatewayResp);
        return GsonHolder.toJson(resp);
    }

    public static void writeResp(HttpServletResponse response, GatewayResp gatewayResp) throws Exception {
        response.setCharacterEncoding(CustomConstant.Charset.UTF_8);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        IOUtils.write(RespUtils.generateJsonResp(gatewayResp), response.getWriter());
    }

}
