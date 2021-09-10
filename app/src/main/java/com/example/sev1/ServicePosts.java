//package com.example.sev1;
//
//import android.content.Context;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//
//import java.util.List;
//
//public class ServicePosts {
//
//    public interface VolleyResponseListener{
//        void onError(String message);
//        void onResponse(String sucess);
//    }
//
//    public List<> getAllPosts(Context context){
//        RequestQueue queue = Volley.newRequestQueue(context);
//        String url ="http://www.google.com";
//
//        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                try {
//                    //JSONArray x = response.getJSONArray("");
//                } catch (){
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//    }
//}
