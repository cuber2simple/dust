package o.c.gj.gf.oauth2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("认证请求")
public class Authorize {

    @ApiModelProperty("客户端ID")
    private String clientId;

    @ApiModelProperty("授权模式")
    private GrantType grantType;

    @ApiModelProperty("授权权限")
    private String scope;

    @ApiModelProperty("状态码")
    private String status;

    @ApiModelProperty("重定义地址")
    private String redirectUrl;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public GrantType getGrantType() {
        return grantType;
    }

    public void setGrantType(GrantType grantType) {
        this.grantType = grantType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public String toString() {
        return "Authorize{" +
                "clientId='" + clientId + '\'' +
                ", grantType=" + grantType +
                ", scope='" + scope + '\'' +
                ", status='" + status + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                '}';
    }
}
