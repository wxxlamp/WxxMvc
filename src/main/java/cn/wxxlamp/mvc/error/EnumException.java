package cn.wxxlamp.mvc.error;

/**
 * @author wxxlamp
 * @date 2020/05/21~08:57
 */
public enum EnumException implements CommonException{
    // 1000 表示注解不对
    REQUEST_PARAM_ANNOTATION_IS_NULL(10001, "请求参数没有必须的注解"),
    METHOD_NEEDS_ANNOTATION(10002, "方法需要加上RequestMapping注解"),
    HTTP_BODY_IS_NOT_YSE(10003, "Http请求不符合RequestBody要求"),


    // 2000 表示URL不对
    URL_REPEAT(20001, "URL重复"),

    // 3000 表示传参不对
    NO_SUITABLE_RESOLVER_FOR_PARAMETER(30001, "参数无法解析");

    private final int code;
    private String msg;

    EnumException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getErrCode() {
        return this.code;
    }

    @Override
    public String getErrMsg() {
        return this.msg;
    }

    @Override
    public void setErrMsg(String msg) {
        this.msg = msg;
    }
}
