package o.c.gj.gf.sso;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import o.c.gj.cons.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@ApiModel("登陆用户")
public class User implements UserDetails {

    private static final long serialVersionUID = -3568422755366679290L;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("用户登陆名")
    private String userName;

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户EMAIL")
    private String email;

    @ApiModelProperty("用户电话")
    private String mobile;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("微信公开ID")
    private String wxOpenId;

    @ApiModelProperty("钉钉公开ID")
    private String dingOpenId;

    @ApiModelProperty("所属权限")
    private String domain;

    @ApiModelProperty("登陆的ID,会发生变化")
    private String loginId;

    @ApiModelProperty("用户是否被锁定")
    private Status status;

    @ApiModelProperty("用户过期时间")
    private LocalDateTime expireDatetime;

    @ApiModelProperty("用户所属组")
    private List<UserGroup> userGroups;

    @ApiModelProperty("用户权限")
    private List<Role> roles;

    @ApiModelProperty("用户拥有的菜单")
    private Menu suitMenu;

    public Menu getSuitMenu() {
        return suitMenu;
    }

    public void setSuitMenu(Menu suitMenu) {
        this.suitMenu = suitMenu;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getDingOpenId() {
        return dingOpenId;
    }

    public void setDingOpenId(String dingOpenId) {
        this.dingOpenId = dingOpenId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getExpireDatetime() {
        return expireDatetime;
    }

    public void setExpireDatetime(LocalDateTime expireDatetime) {
        this.expireDatetime = expireDatetime;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return LocalDateTime.now().isBefore(expireDatetime);
    }

    @Override
    public boolean isAccountNonLocked() {
        return Status.success.equals(status);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", sex='" + sex + '\'' +
                ", avatar='" + avatar + '\'' +
                ", wxOpenId='" + wxOpenId + '\'' +
                ", dingOpenId='" + dingOpenId + '\'' +
                ", domain='" + domain + '\'' +
                ", loginId='" + loginId + '\'' +
                ", status='" + status + '\'' +
                ", expireDatetime=" + expireDatetime +
                ", userGroups=" + userGroups +
                ", roles=" + roles +
                ", suitMenu=" + suitMenu +
                '}';
    }
}
