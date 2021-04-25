package com.hyunmin.greendaytracks;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;

/* Fragment to show Api searched Tracks
 * Use Volley for Api communications
 * Use Fresco for Url image processing
 * */

public class SearchFragment extends Fragment{

    private SearchViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private SearchAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RequestQueue queue;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerview_search);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);

        //initialize volley queue
        queue = Volley.newRequestQueue(getActivity());

        //get tracks from iTunes api
        getTracks();

        return view;
    }


    public void  getTracks(){
        //Api request url
        String url ="https://itunes.apple.com/search?term=greenday&entity=song";

        //Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {

                    try {
                        //get Json data from url and change it into JsonObject
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray arrayResults = jsonObject.getJSONArray("results");

                        //List to put Track information
                        List<TrackData> dataList = new ArrayList<>();

                        for(int i=0, j = arrayResults.length(); i<j; i++){
                            JSONObject trackInfo = arrayResults.getJSONObject(i);

                            //put new track into dataList
                            TrackData data = new TrackData();
                            data.setArtistName(trackInfo.getString("artistName"));
                            data.setArtWorkUrl(trackInfo.getString("artworkUrl60"));
                            data.setCollectionName(trackInfo.getString("collectionName"));
                            data.setTrackId(trackInfo.getInt("trackId"));
                            data.setTrackName(trackInfo.getString("trackName"));

                            //set isFavorite value
                            if(mViewModel.isFavorite(data.getTrackId())){
                                //if data is in DB, set to true
                                data.setFavorite(true);
                            }
                            else {
                                //if not set to false
                                data.setFavorite(false);
                            }
                            dataList.add(data);
                        }

                        //Attach Adapter with API information
                        mAdapter = new SearchAdapter(dataList, getContext());
                        mRecyclerView.setAdapter(mAdapter);

                        //Favorite toggle button triggered function
                        mAdapter.setOnFavoriteClickListener(position -> {
                            TrackData currentData = dataList.get(position);
                            if(mViewModel.isFavorite(currentData.getTrackId())){
                                //Track not in db = not a favorite
                                mViewModel.delete(currentData);
                            }
                            else{//favorite track
                                mViewModel.insert(currentData);
                            }
                        });


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //extract wanted data from response

                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("VolleyError", "onErrorResponse: " + error);
                }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

}