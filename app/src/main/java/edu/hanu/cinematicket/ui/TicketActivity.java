package edu.hanu.cinematicket.ui;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.hanu.cinematicket.R;
import edu.hanu.cinematicket.adapter.DateAdapter;
import edu.hanu.cinematicket.adapter.TicketAdapter;
import edu.hanu.cinematicket.model.Ticket;

public class TicketActivity extends AppCompatActivity {

    private RecyclerView RVTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        RVTicket = findViewById(R.id.rvTicket);

        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket("A4", " Sept 27", "9:00"));
        tickets.add(new Ticket("B4", " Sept 27", "9:00"));
        tickets.add(new Ticket("A4", " Sept 27", "9:00"));
        tickets.add(new Ticket("A4", " Sept 27", "9:00"));
        tickets.add(new Ticket("A4", " Sept 27", "9:00"));

        TicketAdapter ticketAdapter =new TicketAdapter(this, tickets);
        RVTicket.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        RVTicket.setAdapter(ticketAdapter);

        getSupportActionBar().setTitle("Ticket Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary)));
    }
}