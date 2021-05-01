package cn.wxxlamp.mvc.error;

/**
 * @author wxxlamp
 * @date 2020/05/21~08:58
 */
public interface CommonException {
    /**
     * 获得错误码
     * @return code
     */
    int getErrCode();

    /**
     * 获得错误信息
     * @return errMsg
     */
    String getErrMsg();

    /**
     * 设置错误信息
     * @param msg msg
     */
    void setErrMsg(String msg);
}
