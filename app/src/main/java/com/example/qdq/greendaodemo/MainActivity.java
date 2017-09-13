package com.example.qdq.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qdq.greendaodemo.dao.UserDao;
import com.example.qdq.greendaodemo.entity.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edt_name;
    private EditText edt_age;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_name= (EditText) findViewById(R.id.edt_name);
        edt_age= (EditText) findViewById(R.id.edt_age);
        tv_result= (TextView) findViewById(R.id.tv_result);

    }
    public void add(View view){
        String userName=edt_name.getText().toString().trim();
        String age=edt_age.getText().toString().trim();
        if(TextUtils.isEmpty(userName)||TextUtils.isEmpty(age)){
            Toast.makeText(this,"不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        User user=new User();
        user.setName(userName);
        user.setAge(Integer.parseInt(age));
        addUser(user);
    }
    public void delete(View view){
        List<User> userList=getUserList();
        if(userList!=null&&userList.size()>0){
            deleteUser(userList.get(userList.size()-1));
        }
    }
    public void queryAll(View view){
        List<User> userList=getUserList();
        if(userList!=null){
            tv_result.setText(userList.toString());
        }else{
            tv_result.setText("");
        }

    }
    private void addUser(User user){
        UserDao userDao=MyApplication.instance.getDaoSession().getUserDao();
        userDao.insert(user);
        Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show();
    }
    private void deleteUser(User user){
        UserDao userDao=MyApplication.instance.getDaoSession().getUserDao();
        userDao.delete(user);
        Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();
    }
    private List<User> getUserList(){
        UserDao userDao=MyApplication.instance.getDaoSession().getUserDao();
        List<User> userList=userDao.loadAll();
        return userList;
    }
}
