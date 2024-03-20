package com.example.newsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private static List<NewsItem> newsItems;
    private static NewsItemClickListener listener;

    public NewsAdapter(List<NewsItem> newsItems, NewsItemClickListener listener) {
        this.newsItems = newsItems;
        this.listener = listener;
    }



    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsItem newsItem = newsItems.get(position);
        holder.titleTextView.setText(newsItem.getTitle());
        holder.descriptionTextView.setText(newsItem.getDescription());
        Picasso.get().load(newsItem.getImageUrl()).into(holder.imageView);
    }
    public interface NewsItemClickListener {
        void onNewsItemClick(NewsItem newsItem);
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        ImageView imageView;
        Button readMoreButton;

        NewsViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.newsTitle);
            descriptionTextView = itemView.findViewById(R.id.newsDescription);
            imageView = itemView.findViewById(R.id.newsImage);
            readMoreButton = itemView.findViewById(R.id.readMoreButton);

            readMoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onNewsItemClick(newsItems.get(position));
                    }
                }
            });
        }
    }

}
