package edu.hanu.cinematicket.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edu.hanu.cinematicket.R;
import edu.hanu.cinematicket.adapter.MovieAdapter;
import edu.hanu.cinematicket.adapter.MovieItemClickListener;
import edu.hanu.cinematicket.adapter.SlidePagerAdapter;
import edu.hanu.cinematicket.model.Movie;
import edu.hanu.cinematicket.model.Slide;

public class HomeActivity extends AppCompatActivity implements MovieItemClickListener {

    private List<Slide> lstSlide;
    private ViewPager sliderpaper;
    private TabLayout indicator;
    private RecyclerView MovieRV;

    BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;
    //    private FirebaseDatabase firebaseDatabase;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Navigation Drawer
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_logout:
                        //dialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                        builder.setTitle("Log out ");
                        builder.setMessage("Do you want to logout ?");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        AlertDialog dialog = builder.show();
                        break;
                    case R.id.nav_person:
                        Intent intentProfile = new Intent(HomeActivity.this, ProfileActivity.class);
                        startActivity(intentProfile);
                        break;

                    case R.id.nav_history:
                        Intent intentTicket = new Intent(HomeActivity.this, TicketActivity.class);
                        startActivity(intentTicket);
                        break;
                }

                return true;
            }

        });
        navigationView.bringToFront();

        //
        sliderpaper = findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);
        MovieRV = findViewById(R.id.RvMovie);

        lstSlide = new ArrayList<>();
        lstSlide.add(new Slide(R.drawable.slide1, "Update new movies continuously"));
        lstSlide.add(new Slide(R.drawable.slide2, "Relaxing space"));
        lstSlide.add(new Slide(R.drawable.slide3, "Open every day of the week"));
        lstSlide.add(new Slide(R.drawable.slide4, "Favorite place of the month"));

        SlidePagerAdapter adapter = new SlidePagerAdapter(this, lstSlide);
        sliderpaper.setAdapter(adapter);

        //set time
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new HomeActivity.SliderTimer(), 3000,4000);
        indicator.setupWithViewPager(sliderpaper, true);

        //RecyclerView set up
        //ini data
        final List<Movie> lstMovie = new ArrayList<>();
//        FirebaseDatabase.getInstance().getReference().child("film").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot data:snapshot.getChildren()){
//                    lstMovie.add(new Movie(data.child("name").toString(), Integer.parseInt(data.child("url").getValue().toString())));
////                    MovieAdapter movieAdapter = new MovieAdapter(this, lstMovie, this);
////                    MovieRV.setAdapter(movieAdapter);
////                    MovieRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//

        lstMovie.add(new Movie("Doctor Strange", R.drawable.doctor_strange, R.drawable.doctor_strange,"Dr. Stephen Strange casts a forbidden spell that opens the doorway to the multiverse, including alternate versions of himself, whose threat to humanity is too great for the combined forces of Strange, Wong, and Wanda Maximoff."));
        lstMovie.add(new Movie("Sangchi", R.drawable.sangchi,  R.drawable.sangchi, "Shang-Chi, the master of weaponry-based Kung Fu, is forced to confront his past after being drawn into the Ten Rings organization."));
        lstMovie.add(new Movie("Black Widow", R.drawable.blackwidow,R.drawable.blackwidow, "Natasha Romanoff confronts the darker parts of her ledger when a dangerous conspiracy with ties to her past arises."));
        lstMovie.add(new Movie("Thor", R.drawable.thor, R.drawable.thor, "Thor enlists the help of Valkyrie, Korg and ex-girlfriend Jane Foster to fight Gorr the God Butcher, who intends to make the gods extinct."));
        lstMovie.add(new Movie("Infinity War", R.drawable.infinity, R.drawable.infinity, "The Avengers and their allies must be willing to sacrifice all in an attempt to defeat the powerful Thanos before his blitz of devastation and ruin puts an end to the universe."));
        lstMovie.add(new Movie("Captain America", R.drawable.captain, R.drawable.captain, "Political involvement in the Avengers' affairs causes a rift between Captain America and Iron Man."));
        //
        MovieAdapter movieAdapter = new MovieAdapter(this, lstMovie, this);
        MovieRV.setAdapter(movieAdapter);
        MovieRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {

        // send movie information to detail activity

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgURL", movie.getThumbnail());
        intent.putExtra("imgCover", movie.getCoverPhoto());
        intent.putExtra("imgDes", movie.getDescription());
        startActivity(intent);

        // set animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HomeActivity.this, movieImageView, "sharedName");
        startActivity(intent, options.toBundle());

    }

    //auto change slide
    class SliderTimer extends TimerTask {

        @Override
        public void run() {
            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderpaper.getCurrentItem()<lstSlide.size()-1){
                        sliderpaper.setCurrentItem(sliderpaper.getCurrentItem()+1);
                    }
                    else{
                        sliderpaper.setCurrentItem(0);
                    }
                }
            });
        }
    }

    }
