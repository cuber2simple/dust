package o.c.gj.util;

import o.c.gj.cons.SymbolConstant;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

public class HardwareUtils {
    private static final Logger logger = LoggerFactory.getLogger(HardwareUtils.class);

    public static String signature(InetAddress address) {
        String result = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            byte[] mac = NetworkInterface.getByInetAddress(address).getHardwareAddress();
            stringBuilder.append(address.toString())
                    .append(SymbolConstant.COLON_DOUBLE)
                    .append(StringUtils.upperCase(Hex.encodeHexString(mac)));
            result = stringBuilder.toString();
        } catch (Exception e) {
            logger.error("取地址签名报错", e);
        }

        return result;
    }

    public static String signatureLocal() {
        String signatureLocal = null;
        try {
            signatureLocal = signature(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            logger.error("取地址签名报错", e);
        }
        return signatureLocal;
    }

}
