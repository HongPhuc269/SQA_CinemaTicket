package edu.hanu.cinematicket.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import edu.hanu.cinematicket.R;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView MovieImage, MovieCoverImg;
    private TextView tv_title, tv_description, MovieDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        iniView();
    }

    void iniView(){
        // get movie information from home activity
        String movieTitle = getIntent().getExtras().getString("title");
        int movieImage = getIntent().getExtras().getInt("imgURL");
        int movieCover = getIntent().getExtras().getInt("imgCover");
        String movieDescription = getIntent().getExtras().getString("imgDes");

        MovieImage = findViewById(R.id.detail_movie_img);
        Glide.with(this).load(movieImage).into(MovieImage);
        MovieImage.setImageResource(movieImage);

        MovieCoverImg = findViewById(R.id.detail_movie_cover);
        MovieCoverImg.setImageResource(movieCover);

        MovieDescription = findViewById(R.id.detail_movie_desc);
        MovieDescription.setText(movieDescription);

        getSupportActionBar().setTitle(movieTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary)));
        tv_description = findViewById(R.id.detail_movie_desc);

        Button btnChoose = findViewById(R.id.btnChoose);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDetailActivity.this, ChooseSeatActivity.class);
                startActivity(intent);
            }
        });


    }

}