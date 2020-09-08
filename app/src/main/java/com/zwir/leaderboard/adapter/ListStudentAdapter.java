package com.zwir.leaderboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zwir.leaderboard.R;
import com.zwir.leaderboard.model.Student;

import java.util.ArrayList;

public class ListStudentAdapter extends RecyclerView.Adapter<ListStudentAdapter.ListStudentRowHolder>{

    private ArrayList<Student> listStudent;
    private String leaderType;
    private Context mContext;

    public ListStudentAdapter(ArrayList<Student> listStudent, String leaderType,Context mContext) {
        this.listStudent= listStudent;
        this.leaderType = leaderType;
        this.mContext=mContext;
    }
    @Override
    public ListStudentRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.boarding_row_holder,parent,false);
        return new ListStudentRowHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull final ListStudentRowHolder holder, final int position) {
            Glide.with(mContext)
                    .load(listStudent.get(position).getBadgeUrl())
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(holder.badge);
            holder.name.setText(listStudent.get(position).getName());
            String description;
            if (leaderType.equals("skilliq"))
                description = listStudent.get(position).getScore() + " skill IQ Score, " + listStudent.get(position).getCountry();
            else
                description = listStudent.get(position).getHours() + " learning hours, " + listStudent.get(position).getCountry();
            holder.score.setText(description);
    }
    @Override
    public int getItemCount() {
        return (null != listStudent ? listStudent.size() : 0);
    }
    class ListStudentRowHolder extends RecyclerView.ViewHolder {
        ImageView badge;
        TextView name,score;
        ListStudentRowHolder(View itemView) {
            super(itemView);
            this.badge=itemView.findViewById(R.id.badge_img);
            this.name=itemView.findViewById(R.id.name);
            this.score=itemView.findViewById(R.id.score);
        }
    }

    public void setListStudent(ArrayList<Student> listStudent) {
        this.listStudent = listStudent;
        notifyDataSetChanged();
    }
}
