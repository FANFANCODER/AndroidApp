package cn.edu.uestc.bbs.qshp.ui;

import android.app.Activity;
import butterknife.Bind;
import butterknife.OnClick;
import butterknife.ButterKnife;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import cn.edu.uestc.bbs.qshp.R;


public class LoginActivity extends Activity {
    //使用注解将控件以注入的方式绑定到布局文件里面
    @Bind(R.id.et_login_password)EditText password;
    @Bind(R.id.bt_login) Button login;
    @Bind(R.id.et_login_username)EditText username;
    @Bind(R.id.tv_login_result) TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }
    //使用注解绑定监听器
    @OnClick(R.id.bt_login)
    void login(View v)
    {
        Toast.makeText(this,"点击",Toast.LENGTH_SHORT).show();
    }

}
