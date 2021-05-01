package cn.wxxlamp.mvc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author wxxlamp
 * @date 2021/05/01~19:44
 */
public class IoUtils {

    public static String readFromProperties(String key) {
        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = IoUtils.class.getClassLoader().getResourceAsStream("wxx-mvc.properties");
        // 使用properties对象加载输入流
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取key对应的value值
        return properties.getProperty(key);
    }
}
