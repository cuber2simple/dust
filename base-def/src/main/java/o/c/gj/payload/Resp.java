package o.c.gj.payload;

public class Resp {

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Resp{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}