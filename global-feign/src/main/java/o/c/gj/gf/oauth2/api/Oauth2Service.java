package o.c.gj.gf.oauth2.api;

import o.c.gj.gf.oauth2.Authorize;
import o.c.gj.gf.oauth2.Client;
import o.c.gj.gf.oauth2.TokenResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 只有内部系统才可以调用
 */
@FeignClient("oauth2")
@RequestMapping("/api")
public interface Oauth2Service {

    @PostMapping("/check_token")
    Client checkToken(@RequestBody String token);


    @PostMapping("/token")
    TokenResp token(@RequestBody Authorize authorize);

}