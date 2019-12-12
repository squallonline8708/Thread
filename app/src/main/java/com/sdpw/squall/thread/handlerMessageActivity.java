package com.sdpw.squall.thread;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class handlerMessageActivity extends AppCompatActivity
{

    private ProgressButton btn_downLoad_handlerMessId;
    private ProgressButton btn_upLoad_handlerMessId;
    private Button btn_ToastShow_handlerMess;
    private HandlerThread handlerThread_dl1;
    private HandlerThread handlerThread_dl2;
    private ProgressHandler progressHandler1;
    private ProgressHandler progressHandler2;
    private MainHandler mainHandler;
    private Button btn_Back;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread_dl1.quit();
        Log.d("TAG", "下载线程1销毁!");
        handlerThread_dl2.quit();
        Log.d("TAG", "下载线程2销毁!");
    }

    /**
     * 使用消息模型的好处在于当进行重复工作时不会重复产生线程，节省内存资源
     * 1）message（消息数据的载体）
     * 2）MessageQueue（消息队列用于存储多个消息）
     * 3）Looper（迭代器，负责迭代消息队列）
     * 4）Handler（处理器，负责发送和处理消息）
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_message);

        btn_downLoad_handlerMessId = (ProgressButton) findViewById(R.id.btn_downLoad_handlerMessId);
        btn_upLoad_handlerMessId = (ProgressButton) findViewById(R.id.btn_upLoad_handlerMessId);
        btn_ToastShow_handlerMess = (Button) findViewById(R.id.btn_ToastShow_handlerMess);
        btn_Back = (Button) findViewById(R.id.btn_backId);

        btn_downLoad_handlerMessId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //线程1：主线程开始发送消息给工作线程
                for(int i=0;i<=100;i++)
                {
                    //初始化消息对象
                    Message msg_DL1=Message.obtain();
                    msg_DL1.arg1=i;//发送消息内容
                    msg_DL1.what=100;//自定义消息编号
                    progressHandler1.sendMessage(msg_DL1);
                    Log.d("TAG","下载线程1发送消息给工作线程"+msg_DL1.what+", 线程名："+Thread.currentThread().getName());
                }
            }
        });

        btn_upLoad_handlerMessId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //线程2：主线程开始发送消息给工作线程
                for(int i=0;i<=100;i++)
                {
                    //初始化消息对象
                    Message msg_DL2=Message.obtain();
                    msg_DL2.arg1=i;//发送消息内容
                    msg_DL2.what=200;//自定义消息编号
                    progressHandler2.sendMessage(msg_DL2);
                    Log.d("TAG","下载线程2发送消息给工作线程"+msg_DL2.what+", 线程名："+Thread.currentThread().getName());
                }
            }
        });

        btn_ToastShow_handlerMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(handlerMessageActivity.this,"主线程信息显示",Toast.LENGTH_SHORT).show();
            }
        });

        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(handlerMessageActivity.this,MainActivity.class));
                finish();
            }
        });

        /**
         * 初始化HandlerThread
         * HandlerThread为一个已经对共享数据集加锁且定义了优先级的线程模型
         */
        handlerThread_dl1 = new HandlerThread("workThreadForDL1");
        handlerThread_dl2 = new HandlerThread("workThreadForDL2");

        handlerThread_dl1.start();
        Log.d("TAG","下载线程1启动");
        handlerThread_dl2.start();
        Log.d("TAG","下载线程2启动");

        //说明此handle在工作线程中执行
        progressHandler1 =new ProgressHandler(handlerThread_dl1.getLooper());
        progressHandler2 =new ProgressHandler(handlerThread_dl2.getLooper());
        //说明此handle在主线程中执行
        mainHandler=new MainHandler(Looper.getMainLooper());
    }

    /**
     * 自定义handler对象（获取数据一般处理耗时操纵）
     */
    class ProgressHandler extends Handler
    {
        public ProgressHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg)
        {
            //初始化消息对象
            Message msgDL1=Message.obtain();
            Message msgDL2=Message.obtain();
            msgDL1.what=101;
            msgDL2.what=201;
            if(msg.what==100)
            {
                msgDL1.arg1=msg.arg1;
                try
                {
                    //模拟下载延迟
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mainHandler.sendMessage(msgDL1);
                Log.d("TAG","下载线程1工作线程发送消息给主线程"+msgDL1.what+", 线程名："+Thread.currentThread().getName());
            }
            if(msg.what==200)
            {
                msgDL2.arg1=msg.arg1;
                try {
                    //模拟下载延迟
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mainHandler.sendMessage(msgDL2);
                Log.d("TAG","下载线程2工作线程发送消息给主线程"+msgDL2.what+", 线程名："+Thread.currentThread().getName());
            }
        }
    }

    /**
     * 自定义handler对象（更新UI）
     */
    class MainHandler extends Handler
    {
        public MainHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            if(msg.what==101)
            {
                int curstep_DL1=msg.arg1;
                btn_downLoad_handlerMessId.setTextColor(Color.RED);
                btn_downLoad_handlerMessId.setProgress(curstep_DL1*1.0f/100);
                btn_downLoad_handlerMessId.setText(curstep_DL1+"%");
                btn_downLoad_handlerMessId.invalidate();
                Log.d("TAG","下载1按钮更新UI，线程名："+Thread.currentThread().getName());
            }

            if(msg.what==201)
            {

                int curstep_DL1=msg.arg1;
                btn_upLoad_handlerMessId.setTextColor(Color.RED);
                btn_upLoad_handlerMessId.setProgress(curstep_DL1*1.0f/100);
                btn_upLoad_handlerMessId.setText(curstep_DL1+"%");
                btn_upLoad_handlerMessId.invalidate();
                Log.d("TAG","下载2按钮更新UI，线程名："+Thread.currentThread().getName());
            }
        }
    }

}
