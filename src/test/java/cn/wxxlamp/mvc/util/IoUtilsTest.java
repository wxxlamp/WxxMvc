package cn.wxxlamp.mvc.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author wxxlamp
 * @date 2021/05/01~19:47
 */
public class IoUtilsTest {

    @Test
    public void testPropertiesRead() {
        Assert.assertEquals(IoUtils.readFromProperties("packageScanScope"), "cn.wxxlamp.mvc.controller");
    }

}