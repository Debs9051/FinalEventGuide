package events.tcs.com.events.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import events.tcs.com.events.R;
import events.tcs.com.events.constant.ApplicationData;
import events.tcs.com.events.utils.SharedPreferencesManager;


/**
 * Created by arun on 5/30/2018.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginButton;
    private TextView textViewUserTwo;
    private TextView textViewUserOne;
    private RelativeLayout relativeUserOne;
    private RelativeLayout relativeUserTwo;
    private String selectedUser;
    private Activity mActivity;
    private ArrayList<String> userList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUsers();
        initView();
        getExistingUser();
    }


    private void initView(){
        mActivity = this;
        loginButton = (Button) findViewById(R.id.btn_login);
        loginButton.setOnClickListener(this);
        textViewUserTwo = (TextView) findViewById(R.id.textViewUserName2);
        textViewUserOne = (TextView) findViewById(R.id.textViewUserName1);
        relativeUserOne = (RelativeLayout) findViewById(R.id.rl_user1);
        relativeUserTwo = (RelativeLayout) findViewById(R.id.rl_user2);
        relativeUserOne.setOnClickListener(this);
        relativeUserTwo.setOnClickListener(this);
        textViewUserOne.setText(userList.get(0));
        textViewUserTwo.setText(userList.get(1));
    }

    private void initUsers() {
        userList = new ArrayList<>();
        userList.add("Stephane");
        userList.add("David");
    }

    private void getExistingUser() {
        selectedUser = SharedPreferencesManager.readSharedPref(mActivity, ApplicationData.USER_KEY);
        setSelectedUserBackground();
    }

    private void setSelectedUserBackground() {
        relativeUserOne.setBackgroundResource(R.color.colorWhite);
        relativeUserTwo.setBackgroundResource(R.color.colorWhite);
        if (userList.get(0).equals(selectedUser))
            relativeUserOne.setBackgroundResource(R.color.colorUserSelected);
        else if (userList.get(1).equals(selectedUser))
            relativeUserTwo.setBackgroundResource(R.color.colorUserSelected);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                if (selectedUser.equals("")) {
                    Toast.makeText(mActivity, "Please select a user", Toast.LENGTH_LONG).show();
                } else {
                    SharedPreferencesManager.saveSharedPref(mActivity, ApplicationData.USER_KEY, selectedUser);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.rl_user1:
                selectedUser = userList.get(0);
                setSelectedUserBackground();
                break;
            case R.id.rl_user2:
                selectedUser = userList.get(1);
                setSelectedUserBackground();
                break;
            default:
                break;
        }
    }
}