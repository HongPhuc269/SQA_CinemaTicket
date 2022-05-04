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
                        Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                        startActivity(intent);
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
        lstSlide.add(new Slide(R.drawable.avenger, "add title here"));
        lstSlide.add(new Slide(R.drawable.spiderman, "add title here"));
        lstSlide.add(new Slide(R.drawable.venom, "add title here"));
        lstSlide.add(new Slide(R.drawable.spiderman, "add title here"));

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

        lstMovie.add(new Movie("Spiderman", R.drawable.spiderman, R.drawable.spiderman_background, "In the film, Parker asks Dr. Stephen Strange (Cumberbatch) to use magic to make his identity as Spider-Man a secret again following its public revelation at the end of Far From Home. When the spell goes wrong, the multiverse is broken open which allows visitors from alternate realities to enter Parker's universe."));
        lstMovie.add(new Movie("Venom", R.drawable.venom, R.drawable.venom_background, "After a faulty interview with the Life Foundation ruins his career, former reporter Eddie Brock's life is in pieces. Six months later, he comes across the Life Foundation again, and he comes into contact with an alien symbiote and becomes Venom, a parasitic antihero."));
        lstMovie.add(new Movie("Avenger", R.drawable.avenger, R.drawable.avenger_background, "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos's actions and undo the chaos to the universe, no matter what consequences may be in store, and no matter who they face..."));
        lstMovie.add(new Movie("Spiderman", R.drawable.spiderman));
        lstMovie.add(new Movie("Venom", R.drawable.venom));
        lstMovie.add(new Movie("Avenger", R.drawable.avenger));
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
