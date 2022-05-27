package com.project.m4u;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.m4u.adapters.MovieAdapter;
import com.project.m4u.db.MyDbManager;
import com.project.m4u.domain.Actor;
import com.project.m4u.domain.Country;
import com.project.m4u.domain.Genre;
import com.project.m4u.domain.Movie;
import com.project.m4u.rest.MovieApi;
import com.project.m4u.rest.MovieApiImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnMovieListener {

    private RecyclerView movieRv;

    private MovieAdapter movieAdapter;

    private final List<Movie> movieList = new ArrayList<>();
    private final List<Country> countryList = new ArrayList<>();
    private final List<Actor> actorList = new ArrayList<>();
    public static List<Genre> genreList = new ArrayList<>();

    LinearLayoutManager llm = new LinearLayoutManager(this);

    private final MovieApi movieApi = new MovieApiImpl(this);

    private final MyDbManager myDbManager = new MyDbManager(MainActivity.this);


    ImageButton backBtn, refreshBtn;
    TextView tv_description, tv_country, tv_actors, tv_about_movie_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieRv = (RecyclerView) findViewById(R.id.rv_movies);
        refreshBtn = findViewById(R.id.btn_refresh_list);
        refreshBtn.setOnClickListener(btnL);

        fillLists();

        movieAdapter = new MovieAdapter(this, movieList, this);
        movieRv.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
        movieRv.setLayoutManager(llm);
    }

    View.OnClickListener btnL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fillLists();
            movieAdapter.notifyDataSetChanged();
            refreshBtn.setEnabled(false);
            Toast.makeText(MainActivity.this, "Updating...", Toast.LENGTH_SHORT).show();

            Timer buttonTimer = new Timer();
            buttonTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refreshBtn.setEnabled(true);
                        }
                    });
                }
            }, 3000);
        }
    };

    @Override
    public void onMovieClick(int position) throws IOException {
        onButtonShowPopupWindowClick();

        tv_description.setText(movieList.get(position).getDescription());
        tv_description.setMovementMethod(new ScrollingMovementMethod());

        tv_about_movie_name.setText(movieList.get(position).getName());
        tv_country.setText(countryList.get(movieList.get(position).getCountryId() - 1).getName());
        tv_actors.setText(actorList.get(movieList.get(position).getActorId() - 1).getName());
    }

    //Create popup window
    public void onButtonShowPopupWindowClick() {
        //Inflate the layout of the popup window
        View popupView;
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = inflater.inflate(R.layout.popup_window, null);

        //Init views
        tv_description = popupView.findViewById(R.id.tv_description);
        tv_country = popupView.findViewById(R.id.tv_country);
        tv_actors = popupView.findViewById(R.id.tv_actors);
        tv_about_movie_name = popupView.findViewById(R.id.tv_about_movie_name);
        backBtn = popupView.findViewById(R.id.backBtn);

        //Create the popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = 2000;
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        //Show the popup window
        //Which view you pass in doesn't matter, it is only used for the window token
        popupWindow.setAnimationStyle(R.style.popupWindowAnimation);
        popupWindow.showAtLocation(popupView, Gravity.CENTER_VERTICAL, 0, 0);

        //CloseAlertPopupBtn listener
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    private void fillLists() {
        //Clear to avoid duplicates
        movieList.clear();
        countryList.clear();
        actorList.clear();
        genreList.clear();

        //Make data up to date
        movieApi.updateCountry();
        movieApi.updateActor();
        movieApi.updateGenre();
        movieApi.updateMovie();

        //Put data to db
        movieApi.fillCountry();
        movieApi.fillGenre();
        movieApi.fillActor();
        movieApi.fillMovie();
        //Put it into arrays
        movieList.addAll(myDbManager.getMoviesFromDb());
        countryList.addAll(myDbManager.getCountriesFromDb());
        genreList.addAll(myDbManager.getGenresFromDb());
        actorList.addAll(myDbManager.getActorsFromDb());
    }
}