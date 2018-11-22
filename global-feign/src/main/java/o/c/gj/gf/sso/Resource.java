package o.c.gj.gf.sso;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Objects;

@ApiModel("资源")
public class Resource implements Serializable {

    private static final long serialVersionUID = 4214349564120072593L;

    @ApiModelProperty("资源ID")
    private String resourceId;

    @ApiModelProperty("资源名称--英文")
    private String resourceName;

    @ApiModelProperty("资源描述")
    private String resourceDesc;

    @ApiModelProperty("资源URL")
    private String resourceUrl;

    @ApiModelProperty("资源ICON")
    private String resourceIcon;

    @ApiModelProperty("资源中文名")
    private String resourceZhName;

    @ApiModelProperty("资源i18n")
    private String i18nKey;

    private Type resourceType;


    public Type getResourceType() {
        return resourceType;
    }

    public void setResourceType(Type resourceType) {
        this.resourceType = resourceType;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceIcon() {
        return resourceIcon;
    }

    public void setResourceIcon(String resourceIcon) {
        this.resourceIcon = resourceIcon;
    }

    public String getResourceZhName() {
        return resourceZhName;
    }

    public void setResourceZhName(String resourceZhName) {
        this.resourceZhName = resourceZhName;
    }

    public String getI18nKey() {
        return i18nKey;
    }

    public void setI18nKey(String i18nKey) {
        this.i18nKey = i18nKey;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        // 如果传入的对象为空,则返回false
        if (obj == null) {
            return false;
        }

        // 如果两者属于不同的类型,不能相等
        if (getClass() != obj.getClass()) {
            return false;
        }

        Resource resource = (Resource) obj;
        return StringUtils.equals(resource.getResourceId(), resourceId);
    }

    @Override
    public int hashCode() {
        return Objects.isNull(resourceId) ? -1 : resourceId.hashCode();
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId='" + resourceId + '\'' +
                ", resourceName='" + resourceName + '\'' +
                ", resourceDesc='" + resourceDesc + '\'' +
                ", resourceUrl='" + resourceUrl + '\'' +
                ", resourceIcon='" + resourceIcon + '\'' +
                ", resourceZhName='" + resourceZhName + '\'' +
                ", i18nKey='" + i18nKey + '\'' +
                ", resourceType=" + resourceType +
                '}';
    }

    public enum Type {
        menu,
        element;
    }
}
