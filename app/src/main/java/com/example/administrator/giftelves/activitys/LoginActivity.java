package com.example.administrator.giftelves.activitys;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.enties.User;
import com.example.administrator.giftelves.sqlutils.MySqlHelperUser;
import com.example.administrator.giftelves.utils.SharedPreferenceUtils;

/**
 *
 * Created by Administrator on 2017/1/11.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText userpass;
    private CheckBox chkAuto;
    private Button loginBtn;
    private Button registBtn;
    private MySqlHelperUser mysql;
    private User users;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
        initListener();
        checkAutoLogin();
    }
    private void initView() {
        username = (EditText) findViewById(R.id.userName);
        userpass = (EditText) findViewById(R.id.userPass);
        chkAuto = (CheckBox) findViewById(R.id.autoLogin);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        registBtn = (Button) findViewById(R.id.registBtn);
    }

    private void initListener() {
        registBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,
                        RegistActivity.class);
                startActivity(intent);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login(username.getText().toString(), userpass.getText()
                        .toString());

            }
        });
    }

    private void checkAutoLogin() {
        boolean flag = SharedPreferenceUtils.getBoolean(this, "loginstate");
        if(flag==true){
            String userName = SharedPreferenceUtils.getString(this, "username");
            String userPass = SharedPreferenceUtils.getString(this, "userpass");
            chkAuto.setChecked(true);
            username.setText(userName);
            userpass.setText(userPass);
        }else if(flag==false){
            chkAuto.setChecked(false);
        }
    }


    private void login(String userName, String userPass) {
        if (TextUtils.isEmpty(username.getText().toString())) {
            Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userpass.getText().toString())) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mysql = new MySqlHelperUser(this);
        SQLiteDatabase db = mysql.getReadableDatabase();
        String sql = "select * from " + MySqlHelperUser.USER_TB
                + " where user_name=?";
        Cursor cursor = db.rawQuery(sql, new String[] {username.getText()
                .toString() });

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor
                    .getColumnIndex("user_name"));
            String pass = cursor.getString(cursor
                    .getColumnIndex("user_pass"));
            users = new User(0, name, pass);
        }
        if (null==users){
            Toast.makeText(this,"账号不存在，请先注册",Toast.LENGTH_SHORT).show();
        }else if(users.getName().equals(userName)
                && users.getPass().equals(userPass)) {
            if (chkAuto.isChecked()) {
                SharedPreferenceUtils.saveString(this, "username", users.getName());
                SharedPreferenceUtils.saveString(this, "userpass", users.getPass());
                SharedPreferenceUtils.saveBoolean(this, "loginstate", true);
            }else if(!chkAuto.isChecked()){
                SharedPreferenceUtils.deleteString(this,"username");
                SharedPreferenceUtils.deleteString(this,"userpass");
                SharedPreferenceUtils.saveBoolean(this, "loginstate",false);
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        db.close();
    }

    public void onClickBack(View view) {
        finish();
    }
}
