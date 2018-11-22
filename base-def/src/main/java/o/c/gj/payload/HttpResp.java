package o.c.gj.payload;

public class HttpResp extends Resp {

    private int httpStatus;

    private String path;

    private String trace;

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "HttpResp{" +
                "httpStatus=" + httpStatus +
                ", path='" + path + '\'' +
                ", trace='" + trace + '\'' +
                "} " + super.toString();
    }
}
