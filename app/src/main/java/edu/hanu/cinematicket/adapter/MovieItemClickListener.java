package edu.hanu.cinematicket.adapter;

import android.widget.ImageView;

import edu.hanu.cinematicket.model.Movie;

public interface MovieItemClickListener {

    void onMovieClick(Movie movie, ImageView movieImageView);

}