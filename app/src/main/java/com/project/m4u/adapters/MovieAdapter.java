package com.project.m4u.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.m4u.R;
import com.project.m4u.domain.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private LayoutInflater inflater;
    private Context mContext;

    private List<Movie> movieList = new ArrayList<>();

    private OnMovieListener mOnMovieListener;

    public MovieAdapter(Context mContext, List<Movie> movieList, OnMovieListener mOnMovieListener) {
        this.inflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.movieList = movieList;
        this.mOnMovieListener = mOnMovieListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_movie, parent, false);
        return new MyViewHolder(v, mOnMovieListener);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,
                                 @SuppressLint("RecyclerView")int position) {

        Movie movie = movieList.get(position);

        Glide.with(mContext).
                load(movieList.get(position).getPicUrl())
                .into(((MyViewHolder) holder).iv_poster);

        ((MyViewHolder) holder).tv_name.setText(movie.getName());
        ((MyViewHolder) holder).tv_genre.setText(movie.getGenre());
        ((MyViewHolder) holder).tv_year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_name;
        TextView tv_genre;
        TextView tv_year;
        ImageView iv_poster;
        OnMovieListener onMovieListener;

        public MyViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
            super(itemView);
            this.onMovieListener = onMovieListener;

            tv_name = (TextView) itemView.findViewById(R.id.tv_movie_name);
            tv_genre = (TextView) itemView.findViewById(R.id.tv_movie_genre);
            tv_year = (TextView) itemView.findViewById(R.id.tv_movie_year);
            iv_poster = (ImageView) itemView.findViewById(R.id.movie_poster);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            try {
                onMovieListener.onMovieClick(getAdapterPosition());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public interface OnMovieListener {
        void onMovieClick(int position) throws IOException;
    }
}
