package com.example.sev1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class AddPostFragment extends Fragment {

    private TextView btnPostar;
    private EditText post;
    private View view;
    private ImageView img;

    public AddPostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add_post, container, false);

        btnPostar = (Button) view.findViewById(R.id.btn_compartilhar);
        post = (EditText) view.findViewById(R.id.description_add);
        img = (ImageView) view.findViewById(R.id.img_add);


        Picasso.get().load("https://media-exp1.licdn.com/dms/image/C4D03AQHu_P-GHDZX-Q/profile-displayphoto-shrink_200_200/0/1626997550637?e=1632960000&v=beta&t=0lRQaGJTSPR5JRwkEAYh42Co7bU4NKLPn_-nKLSG2NY").into(img);

        btnPostar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sendPost();

                }
                catch (Exception e){

                }
            }
        });
        return view;
    }

    private void sendPost(){
        String postagem = post.getText().toString();
        //String nome = btnPostar.getText().toString();
        String APIKEY = "IzFncnVwb3BhcmFpem95ZXN3ZWNvZWNvZGU6c2hhcmVleHBhcGlrZXkxlw==";

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            String URL = "http://yeswecodeshareexperienceapi-test.sa-east-1.elasticbeanstalk.com/experiences/new-experience";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("name", "Teste Karen");
            jsonBody.put("userUid", "testekaren");
            jsonBody.put("isActive", true);
            jsonBody.put("description", postagem);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> header = new HashMap<>();
                    header.put("apiKey", APIKEY);
                    return header;
                }

                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}