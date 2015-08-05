package cn.edu.uestc.bbs.qshp.net;

/**
 * Created by feifei on 15-8-4.
 *
 * 当网络任务处理完成的时候，回调处理网络事件
 */
public interface OnTaskDoneListener {
    //当网络请求成功完成之后会回调这个函数之后会
    public void onTaskSuccess(CommonReturn result);

    //当网络请求失败时回调这个函数
    public void onTaskFailed(CommonReturn result);
}
