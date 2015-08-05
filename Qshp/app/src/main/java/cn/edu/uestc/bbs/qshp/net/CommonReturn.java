package cn.edu.uestc.bbs.qshp.net;

/**
 * Created by feifei on 15-8-4.
 * 网络返回信息的实体类
 * 包含一个Message表示信息，一个Data表示从服务器上下载的字符串数据，一个Status表示请求结果
 */
public class CommonReturn {
    private String Message;
    private String Data;
    private boolean Status;

    public CommonReturn() {
        this.Message="";
        this.Status=false;
        this.Data="";
    }

    public String getMessage() {
        return Message;
    }

    public String getData() {
        return Data;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public void setData(String data) {
        Data = data;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @Override
    public String toString() {
        return "网络请求结果{" +
                "说明信息='" + Message + '\'' +
                ", 返回数据='" + Data + '\'' +
                ", 请求是否成功=" + Status +
                '}';
    }
}
