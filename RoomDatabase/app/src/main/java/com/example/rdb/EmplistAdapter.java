package com.example.rdb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmplistAdapter extends RecyclerView.Adapter<EmplistAdapter.MyViewHolder>{

    private Context context;
    private List<EmpEntity> studentist;


    public EmplistAdapter(Context context, List<EmpEntity> empEntityList) {
        this.context = context;
        this.studentist = empEntityList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        EmpEntity studentEntity = studentist.get(position);
        //holder.userName.setText("   Name-->"+studentEntity.getUsername());
        if(studentEntity.getUsername().equals("")){
            holder.userName.setVisibility(View.GONE);
        } else{
            holder.userName.setVisibility(View.VISIBLE);
            holder.userName.setText(studentEntity.getUsername());
        }

        if(studentEntity.getEmpid().equals("")){
            holder.empid.setVisibility(View.GONE);
        } else{
            holder.empid.setVisibility(View.VISIBLE);
            holder.empid.setText(studentEntity.getEmpid());
        }

        if(studentEntity.getPassword().equals("")){
            holder.password.setVisibility(View.GONE);
        } else{
            holder.password.setVisibility(View.VISIBLE);
            holder.password.setText(studentEntity.getPassword());
        }

        if(studentEntity.getEmpid().equals("")){
            holder.email.setVisibility(View.GONE);
        } else{
            holder.email.setVisibility(View.VISIBLE);
            holder.email.setText(studentEntity.getEmail());
        }

    }

    @Override
    public int getItemCount() {
        return studentist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView userName,empid,password,email;
        ImageView iv_delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user);
            empid = itemView.findViewById(R.id.eid);
            password = itemView.findViewById(R.id.pass);
            email = itemView.findViewById(R.id.Email);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EmpEntity studentEntity = studentist.get(getAdapterPosition());
                    Intent intent = new Intent(context, DetailsUpdateActivity.class);
                    intent.putExtra("studentDetails",studentEntity);
                    context.startActivity(intent);
                }
            });

        }
    }
}
