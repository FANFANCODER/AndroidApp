package cn.edu.uestc.bbs.qshp.net;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by feifei on 15-8-4.
 *
 * 继承异步任务类提供一个带环形progressDialog网络任务处理方法
 */
public class ProgressTask extends AsyncTask<Object,String,CommonReturn> {

    //进度对话框
    ProgressDialog pDialog;

    //任务监听器
    OnTaskDoneListener listener;
    WeakReference<Activity> reference;

    //构造函数，创建进度对话框实例，设置对话框显示的文字信息
    public ProgressTask(final Activity activity,String title)
    {
        reference=new WeakReference<Activity>(activity);
        pDialog=new ProgressDialog(reference.get(),0);
        pDialog.setCancelable(true);
    }

    /**
     * 传入数组参数数组，规定参数的第一个是请求类型，第二个是url,第三个是放有参数名和参数值的Map
     * */
    @Override
    protected CommonReturn doInBackground(Object... params) {
        boolean isGet=(boolean)params[0];
        String url=(String)params[1];
        Map<String,String> param=(HashMap)params[2];
        if (isGet)
        {
            return CommonHttpRequest.doGet(url,param);
        }
        else
        {
            return CommonHttpRequest.doPost(url,param);
        }
    }

    //执行之前显示对话框
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog.show();
    }

    //添加网络任务监听器
    public void setListener(OnTaskDoneListener listener)
    {
        if (listener!=null)
        {
            this.listener=listener;
        }
    }

    //判断网络请求执行结果并在成功时执行事件处理回调函数
    @Override
    protected void onPostExecute(CommonReturn result) {
        pDialog.dismiss();
        super.onPostExecute(result);
        if (result==null)
        {
            return;
        }
        else if (result.isStatus())
        {
            if (listener!=null)
            {
                listener.onTaskSuccess(result);
            }
        }
        else {
            listener.onTaskFailed(result);
        }
    }
}
