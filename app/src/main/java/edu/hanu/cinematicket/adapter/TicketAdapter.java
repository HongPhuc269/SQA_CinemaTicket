package edu.hanu.cinematicket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.hanu.cinematicket.R;
import edu.hanu.cinematicket.model.Ticket;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.MyViewholder> {

    Context context;
    List<Ticket> mTicket;

    public TicketAdapter(Context context, List<Ticket> mTicket) {
        this.context = context;
        this.mTicket = mTicket;
    }

    @NonNull
    @Override
    public TicketAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ticket, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketAdapter.MyViewholder holder, int position) {

        holder.seat.setText(mTicket.get(position).getSeat());
        holder.date.setText(mTicket.get(position).getDate());
        holder.time.setText(mTicket.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return mTicket.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        private TextView seat;
        private TextView date;
        private TextView time;


        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            seat = itemView.findViewById(R.id.seat);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);

        }
    }
}
