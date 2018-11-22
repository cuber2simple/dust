package o.c.gj.starter.conf;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PasswordEncoderConf {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return RandomPasswordEncoder.instance();
    }

    private static class RandomPasswordEncoder implements PasswordEncoder {

        private static RandomPasswordEncoder randomPasswordEncoder = new RandomPasswordEncoder();
        private final Map<String, PasswordEncoder> idToPasswordEncoder;
        private static final String PREFIX = "{";
        private static final String SUFFIX = "}";
        private static final String[] STRINGS = new String[]{"bcrypt", "pbkdf2", "scrypt"};

        private RandomPasswordEncoder() {
            Map<String, PasswordEncoder> encoders = new HashMap<>();
            encoders.put("bcrypt", new BCryptPasswordEncoder());
            encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
            encoders.put("scrypt", new SCryptPasswordEncoder());
            idToPasswordEncoder = encoders;
        }

        protected static RandomPasswordEncoder instance() {
            return randomPasswordEncoder;
        }

        @Override
        public String encode(CharSequence rawPassword) {
            String randomId = STRINGS[RandomUtils.nextInt(0, STRINGS.length)];
            return PREFIX + randomId + SUFFIX + idToPasswordEncoder.get(randomId).encode(rawPassword);
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            if (rawPassword == null && encodedPassword == null) {
                return true;
            }
            String id = extractId(encodedPassword);
            PasswordEncoder delegate = this.idToPasswordEncoder.get(id);
            if (delegate == null) {
                return false;
            }
            String password = extractEncodedPassword(encodedPassword);
            return delegate.matches(rawPassword, password);
        }

        private String extractEncodedPassword(String prefixEncodedPassword) {
            int start = prefixEncodedPassword.indexOf(SUFFIX);
            return prefixEncodedPassword.substring(start + 1);
        }

        private String extractId(String prefixEncodedPassword) {
            if (prefixEncodedPassword == null) {
                return null;
            }
            int start = prefixEncodedPassword.indexOf(PREFIX);
            if (start != 0) {
                return null;
            }
            int end = prefixEncodedPassword.indexOf(SUFFIX, start);
            if (end < 0) {
                return null;
            }
            return prefixEncodedPassword.substring(start + 1, end);
        }
    }
}
