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
        // �������Լ���
        Properties properties = new Properties();
        // �������ļ�properties�еļ�ֵ�ԣ����浽Properties����
        try {
            properties.load(PropertiesUtil.class.getResourceAsStream("/param.properties"));
            image_address = properties.getProperty("images_address");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ȡͼƬ��ַ
     * @return
     */
    public static String getImage_address(){
        return image_address;
    }
}
