package com.zwl.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/3 22:49
 */
public class PropertiesUtil {
    private static String image_address = null;
    static{
        // 创建属性集合
        Properties properties = new Properties();
        // 将配置文件properties中的键值对，保存到Properties集合
        try {
            properties.load(PropertiesUtil.class.getResourceAsStream("/param.properties"));
            image_address = properties.getProperty("images_address");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取图片地址
     * @return
     */
    public static String getImage_address(){
        return image_address;
    }
}
