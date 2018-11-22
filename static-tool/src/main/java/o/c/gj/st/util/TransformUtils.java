package o.c.gj.st.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.core.env.MapPropertySource;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class TransformUtils {

    private static Logger logger = LoggerFactory.getLogger(TransformUtils.class);

    public static <T> T copyP(Object bean, Class<T> tClass, String... ignoreProperties) {
        T t = null;
        if (Objects.nonNull(bean) && Objects.nonNull(tClass)) {
            try {
                Map<String, Object> dtoProperties = PropertyUtils.describe(bean);
                if (ignoreProperties != null && ignoreProperties.length > 0) {
                    Arrays.stream(ignoreProperties).forEach(property -> dtoProperties.remove(property));
                }
                MapPropertySource mapPropertySource = new MapPropertySource("tmp", dtoProperties);
                t = new Binder(ConfigurationPropertySources.from(mapPropertySource))
                        .bind("", tClass).get();
            } catch (Exception e) {
                logger.error("赋值失败", e);
            }
        }
        return t;
    }

    /**
     *  fast way  但是不能使用自动类型转换  sad
     * @param bean
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T copyF(Object bean, Class<T> tClass) {
        T t = null;
        if(Objects.nonNull(bean)){
            try{
                BeanCopier copier = BeanCopier.create(bean.getClass(), tClass, false);
                t = tClass.newInstance();
                copier.copy(bean, t, null);
            }catch (Exception e){
                logger.error("赋值失败", e);
            }
        }
        return t;
    }

}
