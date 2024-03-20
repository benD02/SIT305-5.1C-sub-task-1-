package com.example.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsDetail extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);

        ImageView newsImage = view.findViewById(R.id.newsImage);
        TextView newsTitle = view.findViewById(R.id.newsTitle);
        TextView newsDescription = view.findViewById(R.id.newsDescription);

        Bundle args = getArguments();
        if (args != null) {
            String title = args.getString("newsTitle", "News Title");
            String description = args.getString("newsDescription", "News Description");
            String imageUrl = args.getString("newsImageUrl", "");

            newsTitle.setText(title);
            newsDescription.setText(description);
            Picasso.get().load(imageUrl).into(newsImage);

        }


        // Fetch related news items
        List<NewsItem> relatedNewsItems = getRelatedNews();

        // Set up RecyclerView for displaying related news
        RecyclerView relatedNewsRecyclerView = view.findViewById(R.id.relatedNewsRecyclerView);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        relatedNewsRecyclerView.setLayoutManager(horizontalLayoutManager);

        // Assuming NewsAdapter is designed to handle lists of NewsItem objects
        NewsAdapter relatedNewsAdapter = new NewsAdapter(relatedNewsItems, new NewsAdapter.NewsItemClickListener() {
            @Override
            public void onNewsItemClick(NewsItem newsItem) {
                Bundle args = new Bundle();
                args.putString("newsTitle", newsItem.getTitle());
                args.putString("newsDescription", newsItem.getDescription());
                args.putString("newsImageUrl", newsItem.getImageUrl());

                NewsDetail fragment = new NewsDetail();
                fragment.setArguments(args);

                requireFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        relatedNewsRecyclerView.setAdapter(relatedNewsAdapter);




        return view;
    }

    public List<NewsItem> getRelatedNews() {
        List<NewsItem> relatedNewsItems = new ArrayList<>();
        relatedNewsItems.add(new NewsItem("Title 1", "Description 1", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));
        relatedNewsItems.add(new NewsItem("Title 4", "Description 1", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));
        relatedNewsItems.add(new NewsItem("Title 31", "Description 1", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));
        return relatedNewsItems;
    }

}
