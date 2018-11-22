package o.c.gj.st.util;

import o.c.gj.cons.CustomConstant;
import o.c.gj.st.json.GsonHolder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class SignUtils {

    private static final Logger logger = LoggerFactory.getLogger(SignUtils.class);

    public static boolean is_check_hmac(String key, String value, String hmac) {
        String sign = hmacSign(key, value);

        return StringUtils.isNotEmpty(sign) && sign.equals(hmac);
    }

    public static String hmacSign(String key, String value) {
        String result = null;
        try {
            Mac mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, key.getBytes(CustomConstant.Charset.UTF_8));
            result = Base64.encodeBase64String(mac.doFinal(value.getBytes(CustomConstant.Charset.UTF_8)));
        } catch (Exception e) {
            logger.error("加签出错", e);
        }
        return result;
    }

    public static String generateToken(Map<String, String> values) {
        String result = null;
        if (MapUtils.isNotEmpty(values)) {
            result = generateToken(result);
        }
        return result;
    }

    public static <T> String generateToken(T t) {
        return generateToken(GsonHolder.toJson(t));
    }

    public static String generateToken(String string) {
        String result = null;
        if (StringUtils.isNotEmpty(string)) {
            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                byte[] bytes = digest.digest(string.getBytes(StandardCharsets.UTF_8));
                result = String.format("%032x", new BigInteger(1, bytes));
            } catch (NoSuchAlgorithmException nsae) {
                throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).", nsae);
            }
        }
        return result;
    }
}
