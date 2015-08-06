package cn.edu.uestc.bbs.qshp.ui;

import android.app.Activity;
import butterknife.Bind;
import butterknife.OnClick;
import butterknife.ButterKnife;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import cn.edu.uestc.bbs.qshp.R;
import cn.edu.uestc.bbs.qshp.net.Apis;
import cn.edu.uestc.bbs.qshp.net.CommonReturn;
import cn.edu.uestc.bbs.qshp.net.OnTaskDoneListener;
import cn.edu.uestc.bbs.qshp.net.ProgressTask;


public class LoginActivity extends Activity {
    //登陆使用的Task
    private ProgressTask loginTask;
    private Map<String,String> paras;

    //使用注解将控件以注入的方式绑定到布局文件里面
    @Bind(R.id.et_login_password)EditText password;
    @Bind(R.id.bt_login) Button login;
    @Bind(R.id.et_login_username)EditText username;
    @Bind(R.id.tv_login_result) TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginTask=new ProgressTask(this,this.getString(R.string.login_tip));
        paras=new HashMap<String,String>();
        loginTask.setListener(new OnTaskDoneListener() {
            @Override
            public void onTaskSuccess(CommonReturn result) {
                resultTextView.setText("成功"+result.getData());
                Log.d("info",result.getData());
            }

            @Override
            public void onTaskFailed(CommonReturn result) {
                try {
                    resultTextView.setText(result.getMessage().getBytes("utf-8").toString());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //使用注解绑定监听器
    @OnClick(R.id.bt_login)
    void login(View v)
    {
        String usernameStr=username.getText().toString();
        String passwordStr=password.getText().toString();
        paras.put("username",usernameStr);
        paras.put("password",passwordStr);
        if (usernameStr.equals(null)||password.equals(null))
        {
            Toast.makeText(this,"用户名或者密码不能为空",Toast.LENGTH_SHORT).show();
        }
        else
        {
            loginTask.execute(false, Apis.USER_LOGIN,paras);
        }
    }

}
