package com.example.newsapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class NewsList extends Fragment {
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));


        RecyclerView topRecyclerView = view.findViewById(R.id.top_recycler_view);
        // Set up the top stories items
        LinearLayoutManager topLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        topRecyclerView.setLayoutManager(topLayoutManager);
        List<NewsItem> topStoriesList = new ArrayList<>();
        topStoriesList.add(new NewsItem("Title 1", "Description 1", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));
        topStoriesList.add(new NewsItem("Title 2", "Description 2", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));
        topStoriesList.add(new NewsItem("Title 3", "Description 3", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));
        NewsAdapter.NewsItemClickListener topStoriesClickListener = null;
        topRecyclerView.setAdapter(new NewsAdapter(topStoriesList, topStoriesClickListener));




        // Sample news items
        List<NewsItem> sampleNewsItems = new ArrayList<>();
        sampleNewsItems.add(new NewsItem("Title 1", "Description 1", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));
        sampleNewsItems.add(new NewsItem("Title 2", "Description 2", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));
        sampleNewsItems.add(new NewsItem("Title 3", "Description 3", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));
        sampleNewsItems.add(new NewsItem("Title 1", "Description 1", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));
        sampleNewsItems.add(new NewsItem("Title 2", "Description 2", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));
        sampleNewsItems.add(new NewsItem("Title 3", "Description 3", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));
        sampleNewsItems.add(new NewsItem("Title 3", "Description 3", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));

        NewsAdapter newsAdapter = new NewsAdapter(sampleNewsItems, new NewsAdapter.NewsItemClickListener() {
            @Override
            public void onNewsItemClick(NewsItem newsItem) {
                // Bundle setup and fragment transaction
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

        recyclerView.setAdapter(newsAdapter);

        topStoriesClickListener = new NewsAdapter.NewsItemClickListener() {
            @Override
            public void onNewsItemClick(NewsItem newsItem) {
                // Handle the click event replacing the fragment with a detailed view
                Bundle args = new Bundle();
                args.putString("newsTitle", newsItem.getTitle());
                args.putString("newsDescription", newsItem.getDescription());
                args.putString("newsImageUrl", newsItem.getImageUrl());

                NewsDetail fragment = new NewsDetail();
                fragment.setArguments(args);

                getChildFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        };


        return view;


    }



}


