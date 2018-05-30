package events.tcs.com.events.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

import events.tcs.com.events.R;
import events.tcs.com.events.constant.ApplicationData;
import events.tcs.com.events.data.User;

/**
 * Created by arun on 5/30/2018.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginButton;
    private DatabaseReference mDatabase;
    private TextView textViewUserTwo;
    private TextView textViewUserOne;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userId).setValue(user);
    }

    private void initView(){
        loginButton = (Button) findViewById(R.id.btn_login);
        loginButton.setOnClickListener(this);
        textViewUserTwo = (TextView) findViewById(R.id.textViewUserName2);
        textViewUserOne = (TextView) findViewById(R.id.textViewUserName1);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}