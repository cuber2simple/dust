package o.c.gj.cons;

import o.c.gj.util.HardwareUtils;

public class CustomConstant {
    public static class Http {

        public static class Header {
            /**
             * 打印日志的trace_id
             */
            public static final String TRACE_ID = "X-O-C-Trace-Id";
            /**
             * 从那个APP发过来的
             */
            public static final String SOURCE_APP = "X-O-C-Source-App";
            /**
             * 来源于那个APP
             */
            public static final String SERVER_APP = "X-O-C-Server-App";
            /**
             * 校验串
             */
            public static final String HMAC_SHA256 = "X-O-C-Hmac-Sha256";
            /**
             * token信息
             */
            public static final String TOKEN = "X-O-C-Token";
            /**
             * web hook 通知时间类型
             */
            public static final String NOTIFY_EVENT = "X-O-C-Event";
            /**
             * 响应时间.
             */
            public static final String TIME_GMT_8 = "X-O-C-Time";
        }

        public static class Request {

            public static final String ONCE_PER_REQUEST_CLIENT = "X-O-C-Client";

            public static final String ONCE_PER_REQUEST_MD5 = "X-O-C-Md5";

            public static final String ONCE_PER_REQUEST_QUERY_STRING = "X-O-C-Query-String";

            public static final String ONCE_PER_REQUEST_BODY = "X-O-C-Body";

            public static final String REQUEST_REDIRECT = "";
        }

        public static class Response {

            public static final String UNDEFINED_ERROR = "undefined error";
        }

        public static final String ENV = "X-O-C-Env";

        public static final String PRODUCT = "X-O-C-Product";
    }

    public static class Charset {

        public static final String UTF_8 = "utf-8";
    }

    public static class Self {
        public static final String IP_MAC;

        static {
            IP_MAC = HardwareUtils.signatureLocal();
        }

        public static class Work {

            public static final int INIT_PLATFORM_ID = -1;

            public static final long MAX_REDIS_SEQ = 999999;

        }
    }
}
