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
import edu.hanu.cinematicket.model.Seat;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.MyViewHolder> {

    int rowIndex = -1;
    Context context;
    List<Seat> mSeat;
    public SeatAdapter(Context context, List<Seat> mSeat) {
        this.context = context;
        this.mSeat = mSeat;
    }

    @NonNull
    @Override
    public SeatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_seat, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") int position) {

        myViewHolder.tvSeat.setText(mSeat.get(position).getSeatNum());

        myViewHolder.constraintLayoutSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowIndex=position;
                notifyDataSetChanged();
            }
        });

        if (rowIndex==position) {
            myViewHolder.constraintLayoutSeat.setBackgroundResource(R.drawable.seat_bg_selected);
        } else {
            myViewHolder.constraintLayoutSeat.setBackgroundResource(R.drawable.seat_bg);
        }
    }

    @Override
    public int getItemCount() {
        return mSeat.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout constraintLayoutSeat;
        private TextView tvSeat;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSeat = itemView.findViewById(R.id.seat);
            constraintLayoutSeat = itemView.findViewById(R.id.constraintLayoutSeat);
        }
    }
}