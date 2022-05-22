package com.project.m4u;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.m4u.adapters.MovieAdapter;
import com.project.m4u.db.MyDbManager;
import com.project.m4u.rest.MovieApi;
import com.project.m4u.rest.MovieApiImpl;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnMovieListener {

    private MovieAdapter movieAdapter;

    private RecyclerView movieRv;

    private FragmentTransaction transaction;

    private final MovieApi movieApi = new MovieApiImpl(this);

    private MyDbManager myDbManager = new MyDbManager(MainActivity.this);

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDbManager.openDb();
        movieApi.fillCountry();
        movieApi.fillGenre();
        movieApi.fillMovie();

        movieRv = (RecyclerView) findViewById(R.id.rv_movies);
        movieAdapter = new MovieAdapter(this, myDbManager.getMoviesFromDb(), this);
        movieRv.setAdapter(movieAdapter);
        movieRv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        myDbManager.closeDb();
    }

    public void update() {
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMovieClick(int position) throws IOException {

    }

}