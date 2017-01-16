package com.example.administrator.giftelves.activitys;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.giftelves.R;
import com.example.administrator.giftelves.sqlutils.MySqlHelperUser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Administrator on 2017/1/11.
 */

public class RegistActivity extends AppCompatActivity {
    private EditText userName;
    private EditText userPass;
    private Button sub_btn;
    private MySqlHelperUser mysql;
    private List<String> mlist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
        initView();
        initListener();
    }
    private void initView() {
        userName = (EditText) findViewById(R.id.input_name);
        userPass = (EditText) findViewById(R.id.input_pass);
        sub_btn = (Button) findViewById(R.id.zhuceBtn);
    }
    private void initData(){
        mysql = new MySqlHelperUser(this);
        SQLiteDatabase db = mysql.getWritableDatabase();
        String sql = "insert into "+MySqlHelperUser.USER_TB+" (user_name,user_pass) " +
                "values (?,?)";
        db.execSQL(sql,new Object[]{userName.getText().toString(),userPass.getText().toString()});
        db.close();
    }
    private void initListener(){
        sub_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mysql = new MySqlHelperUser(RegistActivity.this);
                SQLiteDatabase db = mysql.getReadableDatabase();
                String sqling = "select user_name from "+MySqlHelperUser.USER_TB;
                Cursor cursor = db.rawQuery(sqling, null);
                if(TextUtils.isEmpty(userName.getText().toString())){
                    Toast.makeText(RegistActivity.this,"账号不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(userPass.getText().toString())){
                    Toast.makeText(RegistActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                mlist = new ArrayList<>();
                while(cursor.moveToNext()){
                    String username = cursor.getString(cursor.getColumnIndex("user_name"));
                    mlist.add(username);
                }
                boolean flag = mlist.contains(userName.getText().toString());
                if(flag == true){
                    Toast.makeText(RegistActivity.this,"账号已存在",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    initData();
                    Toast.makeText(RegistActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                }
                cursor.close();
                db.close();
                finish();
            }
        });
    }

    public void onClickBack(View view) {
        finish();
    }
}
