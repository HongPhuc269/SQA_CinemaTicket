package edu.hanu.cinematicket.ui;

import static android.content.ContentValues.TAG;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.hanu.cinematicket.R;
import edu.hanu.cinematicket.adapter.DateAdapter;
import edu.hanu.cinematicket.adapter.SeatAdapter;
import edu.hanu.cinematicket.model.Date;
import edu.hanu.cinematicket.model.Seat;

public class ChooseSeatActivity extends AppCompatActivity {

    private RecyclerView RVDate;
    private RecyclerView RVSeat;
    private TextView tvTime9, tvTime12, tvTime16;
    private boolean click9 = false;
    private boolean click12 = false;
    private boolean click16 = false;

    private FirebaseFirestore db;
    private Button orderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_seat);

        RVDate = findViewById(R.id.RVDate);
        RVSeat = findViewById(R.id.RVSeat);

        db = FirebaseFirestore.getInstance();
        orderBtn = findViewById(R.id.orderBtn);

        List<Date> lstDate = new ArrayList<>();

        lstDate.add(new Date("Sept 27", "Mon"));
        lstDate.add(new Date("Sept 28", "Tue"));
        lstDate.add(new Date("Sept 29", "Thu"));
        lstDate.add(new Date("Sept 30", "Wed"));
        lstDate.add(new Date("Oct 1", "Fri"));
        lstDate.add(new Date("Oct 2", "Sat"));
        lstDate.add(new Date("Oct 3", "Sun"));

        DateAdapter dateAdapter =new DateAdapter(this, lstDate);
        RVDate.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        RVDate.setAdapter(dateAdapter);


        tvTime9 = findViewById(R.id.time9);
        tvTime12 = findViewById(R.id.time12);
        tvTime16 = findViewById(R.id.time16);

        tvTime9.setOnClickListener(listener);
        tvTime12.setOnClickListener(listener);
        tvTime16.setOnClickListener(listener);

        // seat
        List<Seat> lstSeat = new ArrayList<>();
        for(int i = 1; i<7; i ++){
            lstSeat.add(new Seat("A" + i));
        }
        for(int i = 1; i<7; i ++){
            lstSeat.add(new Seat("B" + i));
        }
        for(int i = 1; i<7; i ++){
            lstSeat.add(new Seat("C" + i));
        }
        for(int i = 1; i<7; i ++){
            lstSeat.add(new Seat("D" + i));
        }

        SeatAdapter seatAdapter =new SeatAdapter(this, lstSeat);
        RVSeat.setLayoutManager(new GridLayoutManager(this, 6));
        RVSeat.setAdapter(seatAdapter);

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String seatNo = String.valueOf(seatAdapter.getRowByPos(seatAdapter.getLastClickedPos()));
                String currentDate = String.valueOf(dateAdapter.getDateForPosition(dateAdapter.getLastItemClicked()));
                String time = tvTime9.getText().toString();
                Map<String,Object> book = new HashMap<>();
                book.put("Seat Number",seatNo);
                book.put("Current Date",currentDate);
                book.put("The Timer",time);
                db.collection("book")
                        .add(book)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
//                                Log.d(TAG, "DocumentSnapshot successfully written!");
                                Toast.makeText(ChooseSeatActivity.this, "Successful added", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error writing document", e);
                        Toast.makeText(ChooseSeatActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        getSupportActionBar().setTitle("Choose seat & date");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary)));

    }
    View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.time9:
                    if (click9 == false && click12 == false && click16 == false) {
                        tvTime9.setBackground(getDrawable(R.drawable.time_bg_selected));
                        click9 = true;
                    }else if (click9 == false && click12 == true ) {
                        Toast.makeText(ChooseSeatActivity.this, R.string.choose_one_time, Toast.LENGTH_LONG).show();
                    }else if (click9 == false && click16 == true) {
                        Toast.makeText(ChooseSeatActivity.this, R.string.choose_one_time, Toast.LENGTH_LONG).show();
                    } else{
                        tvTime9.setBackground(getDrawable(R.drawable.time_bg));
                        click9 = false;
                    }
                    break;
                case R.id.time12:
                    if (click12 == false && click9 == false && click16 == false) {
                        tvTime12.setBackground(getDrawable(R.drawable.time_bg_selected));
                        click12 = true;
                    }else if (click12 == false && click9 == true) {
                        Toast.makeText(ChooseSeatActivity.this, R.string.choose_one_time, Toast.LENGTH_LONG).show();
                    }else if (click12 == false && click16 == true) {
                        Toast.makeText(ChooseSeatActivity.this, R.string.choose_one_time, Toast.LENGTH_LONG).show();
                    } else{
                        tvTime12.setBackground(getDrawable(R.drawable.time_bg));
                        click12 = false;
                    }
                    break;
                case R.id.time16:
                    if (click16 == false && click12 == false && click9 == false) {
                        tvTime16.setBackground(getDrawable(R.drawable.time_bg_selected));
                        click16 = true;
                    }else if (click16 == false && click12 == true) {
                        Toast.makeText(ChooseSeatActivity.this, R.string.choose_one_time, Toast.LENGTH_LONG).show();
                    }else if (click16 == false && click9 == true) {
                        Toast.makeText(ChooseSeatActivity.this, R.string.choose_one_time, Toast.LENGTH_LONG).show();
                    } else{
                        tvTime16.setBackground(getDrawable(R.drawable.time_bg));
                        click16 = false;
                    }
                    break;
            }
        }
    };


}