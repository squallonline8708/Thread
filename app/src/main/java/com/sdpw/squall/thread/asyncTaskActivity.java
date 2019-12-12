package com.sdpw.squall.thread;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class asyncTaskActivity extends AppCompatActivity {

    private Button btn_backId;
    private SwipeRefreshLayout swipeRefreshLayoutId;
    private RecyclerView recyclerViewId;

    public ArrayList<Student> loadStudentData()
    {
        ArrayList<Student> stuList=new ArrayList<>();
        Student student1=new Student(R.drawable.png_01,"李雷",
                18,true,98,86,95);
        Student student2=new Student(R.drawable.png_02,"春丽",
                21,false,87,99,89);
        Student student3=new Student(R.drawable.png_03,"邓艾",
                27,false,89,92,100);
        Student student4=new Student(R.drawable.png_04,"艾夫斯",
                26,true,98,99,94);
        Student student5=new Student(R.drawable.png_05,"詹姆士",
                20,true,100,87,90);
        stuList.add(student1);
        stuList.add(student2);
        stuList.add(student3);
        stuList.add(student4);
        stuList.add(student5);
        return stuList;
    }

    public ArrayList<Student> ReloadStudentData()
    {
        ArrayList<Student> stuList=new ArrayList<>();
        Student newStudent1=new Student(R.drawable.png_06,"王芳",
                22,false,100,87,87);
        Student newStudent2=new Student(R.drawable.png_07,"怜心",
                17,false,88,90,95);
        Student newStudent3=new Student(R.drawable.png_08,"马超",
                27,true,100,87,98);
        Student newStudent4=new Student(R.drawable.png_09,"易峰",
                30,true,98,99,99);
        Student newStudent5=new Student(R.drawable.png_10,"宋菲雅",
                20,false,100,87,90);
        stuList.add(newStudent1);
        stuList.add(newStudent2);
        stuList.add(newStudent3);
        stuList.add(newStudent4);
        stuList.add(newStudent5);
        return stuList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        btn_backId = (Button) findViewById(R.id.btn_backId);
        swipeRefreshLayoutId = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayoutId);
        recyclerViewId = (RecyclerView) findViewById(R.id.recyclerViewId);
        //添加Item分割线
        recyclerViewId.addItemDecoration(new DividerItemDecoration(asyncTaskActivity.this,DividerItemDecoration.VERTICAL));
        initialRecyclerView();


        swipeRefreshLayoutId.setColorSchemeResources(
                android.R.color.holo_green_dark,
                android.R.color.holo_green_light,
                android.R.color.holo_blue_dark
        );

        btn_backId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(asyncTaskActivity.this,MainActivity.class));
               finish();
            }
        });

       swipeRefreshLayoutId.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {
              new myAsyncTask(recyclerViewId).execute(ReloadStudentData());
           }
       });

    }

    private void initialRecyclerView() {
        asyncTaskAdapter asyncTaskAdapter=new asyncTaskAdapter(loadStudentData());
        recyclerViewId.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(asyncTaskActivity.this);
        recyclerViewId.setLayoutManager(linearLayoutManager);
        recyclerViewId.setAdapter(asyncTaskAdapter);
        swipeRefreshLayoutId.setRefreshing(false);
    }

    class myAsyncTask extends AsyncTask<ArrayList<Student>,Void,ArrayList<Student>>
    {
        private  RecyclerView mRecyclerView;
        public myAsyncTask(RecyclerView recyclerView) {
            this.mRecyclerView=recyclerView;
        }

        @Override
        protected ArrayList<Student> doInBackground(ArrayList<Student>... arrayLists) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return arrayLists[0];

        }

        @Override
        protected void onPostExecute(ArrayList<Student> students) {
            super.onPostExecute(students);
            asyncTaskAdapter adapter=(asyncTaskAdapter)mRecyclerView.getAdapter();
            adapter.RefreshData(ReloadStudentData());
            adapter.notifyDataSetChanged();
            swipeRefreshLayoutId.setRefreshing(false);
        }
    }
}
