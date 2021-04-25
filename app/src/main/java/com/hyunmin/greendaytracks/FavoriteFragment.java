package com.hyunmin.greendaytracks;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

/* Shows User's Favorite contents
 * Favorites are stored in local Database
 * */

public class FavoriteFragment extends Fragment {

    private FavoriteViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private FavoriteAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.favorite_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerview_favorite);
        mAdapter = new FavoriteAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //use livedata to update UI
        mViewModel = new ViewModelProvider(requireActivity()).get(FavoriteViewModel.class);
        mViewModel.getFavorites().observe(getViewLifecycleOwner(), new Observer<List<TrackData>>() {
            @Override
            public void onChanged(List<TrackData> trackData) {
                mAdapter.setFavorites(trackData);
            }
        });

        return view;
    }


}
