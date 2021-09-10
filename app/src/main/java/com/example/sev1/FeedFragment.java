package com.example.sev1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.FeedExperience;
import view.adapters.FeedExperienceAdapter;

public class FeedFragment extends Fragment {

    public static final String TAG = "MYTAG";
    public TextView textView;
    RequestQueue QUEUE;
    String URLHTTP = "http://yeswecodeshareexperienceapi-test.sa-east-1.elasticbeanstalk.com/experiences/get-all";
    String APIKEY = "IzFncnVwb3BhcmFpem95ZXN3ZWNvZWNvZGU6c2hhcmVleHBhcGlrZXkxlw==";

    private List<Object> mRecyclerViewItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    RecyclerView rv;

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final  View rootView = inflater.inflate(R.layout.fragment_feed,container,false);
        rv = (RecyclerView)rootView.findViewById(R.id.myrecyview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter    = new FeedExperienceAdapter(getContext(),mRecyclerViewItems,this);
        QUEUE = Volley.newRequestQueue(getContext());
        httpGET(URLHTTP);

        return rootView;
    }

    public void httpGET(String url)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response != null){
//                            Toast.makeText(getContext(),
//                                    response,
//                                    Toast.LENGTH_LONG).show();
                            parsingData(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data,"utf-8");
                    Log.d(TAG,"ERROR "+responseBody);
                }catch (UnsupportedEncodingException errorr){
                    Log.d(TAG,errorr.toString());
                }
            }

        }
        ) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<>();
                header.put("apiKey", APIKEY);
                return header;
            }
        };

        QUEUE.add(stringRequest);
    }


    public void parsingData(String jsonData)
    {
        try {
            JSONObject obj = new JSONObject(jsonData);
            JSONArray m_jArry = obj.getJSONArray("data");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String id = jo_inside.getString("id");
                String name = jo_inside.getString("name");
                String userUid = jo_inside.getString("userUid");
                String description = jo_inside.getString("description");
                String created_at = jo_inside.getString("created_at");
                String updated_at = jo_inside.getString("updated_at");
                Boolean isActive = jo_inside.getBoolean("isActive");

                FeedExperience feedItem = new FeedExperience(id, name, userUid, description,
                        created_at,updated_at,isActive);
                mRecyclerViewItems.add(feedItem);
            }

            rv.setAdapter(mAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}