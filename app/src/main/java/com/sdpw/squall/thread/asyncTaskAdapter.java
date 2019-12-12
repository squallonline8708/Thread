package com.sdpw.squall.thread;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class asyncTaskAdapter extends RecyclerView.Adapter<asyncTaskAdapter.MyRecyclerViewHolder> {
    private ArrayList<Student> students;

    public asyncTaskAdapter() {
    }

    public asyncTaskAdapter(ArrayList<Student> students) {
        this.students = students;
    }

    /**
     * 重写ViewHolder
     */
    class MyRecyclerViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView mImage;
        private TextView mName;
        private TextView mAge;
        private TextView mSex;
        private TextView mGrade_CN;
        private TextView mGrade_EN;
        private TextView mGrade_LG;

        public MyRecyclerViewHolder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.imageId);
            mName=(TextView) itemView.findViewById(R.id.tv_Name);
            mAge=(TextView) itemView.findViewById(R.id.tv_Age);
            mSex=(TextView) itemView.findViewById(R.id.tv_Sex);
            mGrade_CN=(TextView) itemView.findViewById(R.id.tv_grade_CN);
            mGrade_EN=(TextView) itemView.findViewById(R.id.tv_grade_EN);
            mGrade_LG=(TextView) itemView.findViewById(R.id.tv_grade_LG);
        }
    }

    /**
     * 饺子皮
     * @param viewGroup
     * @param i
     * @return
     */
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item_student, viewGroup,false);
        return new MyRecyclerViewHolder(view);
    }

    /**
     * 数据装入容器（类似于包饺子）
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(MyRecyclerViewHolder viewHolder, int i)
    {
        Student student=students.get(i);
        String mName=student.getName();
        int mImage=student.getImageID();
        int mAge=student.getAge();
        boolean mSex=student.isSex(); //true:male; false:female
        int mGradeCN=student.getGrade_CN();
        int mGradeLG=student.getGrade_LG();
        int mGradeEN=student.getGrade_EN();

        viewHolder.mImage.setImageResource(mImage);
        viewHolder.mName.setText("学员姓名: "+mName);
        viewHolder.mAge.setText("学员年龄: "+mAge);
        viewHolder.mSex.setText("学员性别: "+(mSex==true?"男":"女"));
        viewHolder.mGrade_CN.setText("学员中文: "+mGradeCN);
        viewHolder.mGrade_EN.setText("学员英文: "+mGradeEN);
        viewHolder.mGrade_LG.setText("学员思维: "+mGradeLG);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public ArrayList<Student> RefreshData(ArrayList<Student> NewStudent)
    {
        students.clear();
        students=NewStudent;
        return students;
    }
}
