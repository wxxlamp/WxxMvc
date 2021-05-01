package cn.wxxlamp.mvc.error;

/**
 * @author wxxlamp
 * @date 2020/05/21~08:55
 */
public class WxxException extends RuntimeException implements CommonException{

    CommonException commonException;

    public WxxException(CommonException e) {
        this.commonException = e;
    }

    public WxxException(CommonException e, String msg) {
        this.commonException = e;
        this.commonException.setErrMsg(msg);
    }
    @Override
    public int getErrCode() {
        return this.commonException.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonException.getErrMsg();
    }

    @Override
    public void setErrMsg(String msg) {
        this.commonException.setErrMsg(msg);
    }
}
