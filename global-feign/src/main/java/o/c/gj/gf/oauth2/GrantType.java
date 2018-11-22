package o.c.gj.gf.oauth2;

public enum GrantType {
    password("密码授权模式"),
    implicit("简易模式"),
    authorization("授权模式"),
    client("客户端认证模式")
    ;
    private String desc;

    GrantType(String desc) {
        this.desc = desc;
    }
}
