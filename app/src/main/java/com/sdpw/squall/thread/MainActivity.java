package com.sdpw.squall.thread;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_handleId;
    private Button btn_AsyncTaskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_handleId = (Button) findViewById(R.id.btn_handleId);
        btn_AsyncTaskId = (Button) findViewById(R.id.btn_AsyncTaskId);

        btn_handleId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,handlerMessageActivity.class));
            }
        });

        btn_AsyncTaskId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,asyncTaskActivity.class));
            }
        });
    }
}
