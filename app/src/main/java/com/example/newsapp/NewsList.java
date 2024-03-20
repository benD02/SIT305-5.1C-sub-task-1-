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
        sampleNewsItems.add(new NewsItem("Green Energy Revolution: New Battery Technology Triples Capacity", "Researchers at the Global Tech Innovation Lab have unveiled a groundbreaking battery technology that promises to triple the energy storage capacity of existing solutions. This advancement could significantly boost the adoption of renewable energy sources worldwide, marking a major milestone in the fight against climate change.", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));
        sampleNewsItems.add(new NewsItem("City Skies to Get Cleaner with the Launch of Electric Air Taxis", " Urban mobility is set to take a giant leap forward with the introduction of electric air taxis. Skyway, a leading aerospace startup, announced plans to deploy a fleet of noise-reducing, zero-emission vehicles that could redefine city commuting and drastically reduce urban air pollution.", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));
        sampleNewsItems.add(new NewsItem("Revolutionizing Agriculture: Drones to Automate Crop Monitoring", "AgriTech Corporation has launched an innovative drone-based system designed to automate crop monitoring and management. This cutting-edge technology uses AI to analyze aerial imagery, enabling farmers to optimize crop yields, reduce waste, and save water.", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));
        sampleNewsItems.add(new NewsItem("Ocean Cleanup Initiative", "The Ocean Guardian project has successfully removed over 10,000 tonnes of plastic waste from the Pacific Ocean. This significant achievement highlights the potential of community-led initiatives and advanced waste collection technologies in tackling marine pollution.", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));
        sampleNewsItems.add(new NewsItem("Virtual Reality Classrooms: The Future of Education", "NextGen Education unveils a new virtual reality platform that transforms traditional learning environments. This immersive technology provides students with interactive, 3D learning experiences, making education more engaging and accessible than ever before.", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));
        sampleNewsItems.add(new NewsItem("Breakthrough in Water Purification: Portable Device Turns Seawater Drinkable in Minutes", "A team of scientists from AquaTech Innovations has developed a groundbreaking portable device capable of purifying seawater into drinkable water in just minutes. This invention has the potential to provide a sustainable source of clean water to regions affected by drought and water scarcity, revolutionizing access to potable water across the globe.", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));
        sampleNewsItems.add(new NewsItem("Artificial Intelligence to Aid in Early Detection of Forest Fires", "FireWatch AI, a leading tech firm, has launched a new artificial intelligence system designed to detect early signs of forest fires. Utilizing satellite imagery and machine learning algorithms, the system can alert authorities to potential outbreaks before they escalate, offering a powerful tool in the prevention of widespread forest fire damage.", "https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg"));

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


