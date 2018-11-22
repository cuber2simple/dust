package o.c.gj.gf.oauth2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import o.c.gj.payload.Resp;

@ApiModel("token请求响应")
public class TokenResp extends Resp {

    @ApiModelProperty("token串")
    private String accessToken;

    @ApiModelProperty("刷新token 暂时不可用")
    private String refreshToken;

    @ApiModelProperty("token类型,JWT/bearer")
    private String tokenType;

    @ApiModelProperty("过期时间")
    private int expireIn;

    @ApiModelProperty("权限")
    private String scope;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
