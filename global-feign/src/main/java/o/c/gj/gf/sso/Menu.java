package o.c.gj.gf.sso;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import o.c.gj.cons.Status;

import java.util.List;

@ApiModel("菜单")
public class Menu extends Resource implements Comparable<Menu> {
    private static final long serialVersionUID = -4014962413439164653L;

    @ApiModelProperty("父菜单ID")
    private String parentId;

    @ApiModelProperty("菜单排序")
    private int treeSort;

    @ApiModelProperty("是否叶子项目")
    private boolean treeLeaf;

    @ApiModelProperty("叶子层级")
    private int treeLevel;

    @ApiModelProperty("所属域名")
    private String domain;

    @ApiModelProperty("菜单状态")
    private Status status;

    @ApiModelProperty("子菜单")
    private List<Menu> subMenus;

    public List<Menu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<Menu> subMenus) {
        this.subMenus = subMenus;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getTreeSort() {
        return treeSort;
    }

    public void setTreeSort(int treeSort) {
        this.treeSort = treeSort;
    }

    public boolean isTreeLeaf() {
        return treeLeaf;
    }

    public void setTreeLeaf(boolean treeLeaf) {
        this.treeLeaf = treeLeaf;
    }

    public int getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(int treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Menu{" +
                "parentId='" + parentId + '\'' +
                ", treeSort=" + treeSort +
                ", treeLeaf=" + treeLeaf +
                ", treeLevel=" + treeLevel +
                ", domain='" + domain + '\'' +
                ", status='" + status + '\'' +
                ", subMenus=" + subMenus +
                "} " + super.toString();
    }

    @Override
    public int compareTo(Menu o) {
        return this.treeLevel - o.getTreeLevel();
    }
}
