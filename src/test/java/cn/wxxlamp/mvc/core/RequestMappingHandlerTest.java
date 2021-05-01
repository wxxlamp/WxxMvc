package cn.wxxlamp.mvc.core;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author wxxlamp
 * @date 2021/05/01~14:28
 */
public class RequestMappingHandlerTest {

    @Test
    public void testInitMapping() {
        RequestMappingHandler requestMappingHandler = new RequestMappingHandler();
        Assert.assertTrue(requestMappingHandler.hasMapping());
    }
}