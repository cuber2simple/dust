package o.c.gj.cons;

public enum GatewayResp {
    TOKEN_NO_EXISTS("-10", "token no exists"),
    TOKEN_INVALID("-9", "token invalid"),
    SIGNATURE_INVALID("-8", "signature invalid"),
    REQUEST_DUPLICATED("-7","request duplicated"),
    HTTP_ERROR("-6","server error"),
    APP_INVALID("-5","app invalid")
    ;
    private String code;
    private String desc;

    GatewayResp(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
