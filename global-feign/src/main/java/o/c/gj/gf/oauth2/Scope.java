package o.c.gj.gf.oauth2;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import o.c.gj.cons.Status;

import java.util.Date;

@ApiModel("客户端权限")
public class Scope {

    @ApiModelProperty("权限ID")
    private String scopeId;

    @ApiModelProperty("权限名称")
    private String scopeName;

    @ApiModelProperty("权限类型")
    private String scopeType;

    @ApiModelProperty("权限图标")
    private String scopeIcon;

    @ApiModelProperty("父类权限ID")
    private String parentId;

    @ApiModelProperty("权限描述")
    private String scopeDesc;

    @ApiModelProperty("权限URL")
    private String scopeUrl;

    @ApiModelProperty("客户端ID")
    private Status status;

    @ApiModelProperty("更新的用户ID")
    private String updateUserId;

    @ApiModelProperty("创建的用户ID")
    private String createUserId;

    @ApiModelProperty("更新时间")
    private Date updateDatetime;

    @ApiModelProperty("创建时间")
    private Date createDatetime;

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

    public String getScopeType() {
        return scopeType;
    }

    public void setScopeType(String scopeType) {
        this.scopeType = scopeType;
    }

    public String getScopeIcon() {
        return scopeIcon;
    }

    public void setScopeIcon(String scopeIcon) {
        this.scopeIcon = scopeIcon;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getScopeDesc() {
        return scopeDesc;
    }

    public void setScopeDesc(String scopeDesc) {
        this.scopeDesc = scopeDesc;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
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

    public String getScopeUrl() {
        return scopeUrl;
    }

    public void setScopeUrl(String scopeUrl) {
        this.scopeUrl = scopeUrl;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }


    @Override
    public String toString() {
        return "Scope{" +
                "scopeId='" + scopeId + '\'' +
                ", scopeName='" + scopeName + '\'' +
                ", scopeType='" + scopeType + '\'' +
                ", scopeIcon='" + scopeIcon + '\'' +
                ", parentId='" + parentId + '\'' +
                ", scopeDesc='" + scopeDesc + '\'' +
                ", scopeUrl='" + scopeUrl + '\'' +
                ", status=" + status +
                ", updateUserId='" + updateUserId + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", updateDatetime=" + updateDatetime +
                ", createDatetime=" + createDatetime +
                '}';
    }
}
