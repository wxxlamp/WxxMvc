package cn.wxxlamp.mvc.controller;

import cn.wxxlamp.mvc.annotation.RequestMapping;
import cn.wxxlamp.mvc.annotation.RequestMethod;

/**
 * @author wxxlamp
 * @date 2021/04/30~15:32
 */
@RequestMapping
public class Demo {

    @RequestMapping(value = "/666", method = RequestMethod.POST)
    public String getOne(String a) {
        return a;
    }
}
