package com.project.m4u;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.m4u.adapters.MovieAdapter;
import com.project.m4u.db.MyDbManager;
import com.project.m4u.domain.Movie;
import com.project.m4u.rest.MovieApi;
import com.project.m4u.rest.MovieApiImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnMovieListener {

    int positionRecyclerView;

    private RecyclerView movieRv;

    private MovieAdapter movieAdapter;

    private final List<Movie> movieList = new ArrayList<>();

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

        movieList.addAll(myDbManager.getMoviesFromDb());
        movieAdapter = new MovieAdapter(this, movieList, this);
        movieRv.setAdapter(movieAdapter);
        movieRv.setLayoutManager(llm);
    }

    View.OnClickListener btnL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            movieList.clear();
            fillLists();
            movieList.addAll(myDbManager.getMoviesFromDb());
            movieAdapter.notifyDataSetChanged();
            refreshBtn.setEnabled(false);


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
            }, 5000);
        }
    };

    @Override
    public void onMovieClick(int position) throws IOException {
        onButtonShowPopupWindowClick();
        positionRecyclerView = position;
        myDbManager.openDb();
        tv_description.setText(myDbManager.getMoviesFromDb().get(position).getDescription());
        tv_country.setText(myDbManager.getCountriesFromDb().get(position).getName());
        tv_actors.setText(myDbManager.getActorsFromDb().get(position).getName());
        tv_about_movie_name.setText(myDbManager.getMoviesFromDb().get(position).getName());

    }

    //Popup windows method, when user wants to add new playlist or delete existed
    public void onButtonShowPopupWindowClick() {
        //Inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView;

        //Check which popup needed
        popupView = inflater.inflate(R.layout.popup_window, null);
        backBtn = popupView.findViewById(R.id.backBtn);


        tv_description = popupView.findViewById(R.id.tv_description);
        tv_country = popupView.findViewById(R.id.tv_country);
        tv_actors = popupView.findViewById(R.id.tv_actors);
        tv_about_movie_name = popupView.findViewById(R.id.tv_about_movie_name);


        //Create the popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = 2000;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

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
        movieApi.fillCountry();
        movieApi.fillGenre();
        movieApi.fillActor();
        movieApi.fillMovie();
    }

}