package o.c.gj.gf.oauth2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import o.c.gj.cons.Status;

import java.util.Date;
import java.util.List;

@ApiModel("客户端")
public class Client {

    @ApiModelProperty("客户端ID")
    private String clientId;

    @ApiModelProperty("客户端密钥")
    private String clientSecret;

    @ApiModelProperty("客户端名称")
    private String clientName;

    @ApiModelProperty("客户端类型")
    private ClientType clientType;

    @ApiModelProperty("主页地址")
    private String homeUri;

    @ApiModelProperty("webhook地址")
    private String webhookUri;

    @ApiModelProperty("访问TOKEN有效期")
    private int accessTokenValidity;

    @ApiModelProperty("刷新TOKEN有效期")
    private int refreshTokenValidity;

    @ApiModelProperty("服务端头像")
    private String avatar;

    @ApiModelProperty("状态")
    private Status status;

    @ApiModelProperty("私钥")
    private String rsaPrivateKey;

    @ApiModelProperty("公钥")
    private String rsaPublicKey;

    @ApiModelProperty("APP类型的客户ID")
    private String appCustomerId;

    @ApiModelProperty("拥有者的开发的用户ID,只针对server")
    private String ownerOperatorId;

    @ApiModelProperty("过期时间")
    private Date expireDatetime;

    @ApiModelProperty("更新时间")
    private Date updateDatetime;

    @ApiModelProperty("创建时间")
    private Date createDatetime;

    @ApiModelProperty("客户权限")
    private List<Scope> scopes;


    public List<Scope> getScopes() {
        return scopes;
    }

    public void setScopes(List<Scope> scopes) {
        this.scopes = scopes;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public String getHomeUri() {
        return homeUri;
    }

    public void setHomeUri(String homeUri) {
        this.homeUri = homeUri;
    }

    public String getWebhookUri() {
        return webhookUri;
    }

    public void setWebhookUri(String webhookUri) {
        this.webhookUri = webhookUri;
    }

    public int getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(int accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public int getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(int refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRsaPublicKey() {
        return rsaPublicKey;
    }

    public void setRsaPublicKey(String rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }

    public String getOwnerOperatorId() {
        return ownerOperatorId;
    }

    public void setOwnerOperatorId(String ownerOperatorId) {
        this.ownerOperatorId = ownerOperatorId;
    }

    public Date getExpireDatetime() {
        return expireDatetime;
    }

    public void setExpireDatetime(Date expireDatetime) {
        this.expireDatetime = expireDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public String getAppCustomerId() {
        return appCustomerId;
    }

    public void setAppCustomerId(String appCustomerId) {
        this.appCustomerId = appCustomerId;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }

    @Override
    public String toString() {

        return "Client{" +
                "clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientType=" + clientType +
                ", homeUri='" + homeUri + '\'' +
                ", webhookUri='" + webhookUri + '\'' +
                ", accessTokenValidity=" + accessTokenValidity +
                ", refreshTokenValidity=" + refreshTokenValidity +
                ", avatar='" + avatar + '\'' +
                ", status=" + status +
                ", rsaPrivateKey='" + rsaPrivateKey + '\'' +
                ", rsaPublicKey='" + rsaPublicKey + '\'' +
                ", appCustomerId='" + appCustomerId + '\'' +
                ", ownerOperatorId='" + ownerOperatorId + '\'' +
                ", expireDatetime=" + expireDatetime +
                ", updateDatetime=" + updateDatetime +
                ", createDatetime=" + createDatetime +
                ", scopes=" + scopes +
                '}';
    }

    public enum ClientType{
        app,
        server;
    }
}
