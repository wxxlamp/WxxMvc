package cn.wxxlamp.mvc.util;

import org.junit.Test;

/**
 * @author wxxlamp
 * @date 2021/05/01~10:11
 */
public class ClazzUtilsTest {

    @Test
    public void testClass() {
        System.out.println(ClazzUtils.getClazzName("cn.wxxlamp.mvc", false));
    }

}