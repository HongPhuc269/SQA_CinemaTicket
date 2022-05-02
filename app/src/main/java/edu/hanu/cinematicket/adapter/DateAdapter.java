package edu.hanu.cinematicket.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.hanu.cinematicket.R;
import edu.hanu.cinematicket.model.Date;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.MyViewHolder> {

    int rowIndex = -1;

    Context context;
    List<Date> mDate;

    public DateAdapter(Context context, List<Date> mDate) {
        this.context = context;
        this.mDate = mDate;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_date, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") int position) {

        myViewHolder.tvDate.setText(mDate.get(position).getDate());
        myViewHolder.tvWeekday.setText(mDate.get(position).getWeekday());

        myViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowIndex=position;
                notifyDataSetChanged();
            }
        });

        if (rowIndex==position) {
            myViewHolder.constraintLayout.setBackgroundResource(R.drawable.date_bg_selected);
        } else {
            myViewHolder.constraintLayout.setBackgroundResource(R.drawable.date_bg);
        }


    }

    @Override
    public int getItemCount() {
        return mDate.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintLayout;
        private TextView tvDate;
        private TextView tvWeekday;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            tvDate =itemView.findViewById(R.id.date);
            tvWeekday = itemView.findViewById(R.id.weekday);

        }
    }
}
