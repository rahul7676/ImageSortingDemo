package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    ImageView img1,img2,img3,img4,img5,img6,img7,img8;
    String String_img,String_order,String_title;
    ConstraintLayout contraint;
    LinearLayout lin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       img1=(ImageView)findViewById(R.id.imageView1);
        img2=(ImageView)findViewById(R.id.imageView2);
        img3=(ImageView)findViewById(R.id.imageView3);
        img4=(ImageView)findViewById(R.id.imageView4);
        img5=(ImageView)findViewById(R.id.imageView5);
        img6=(ImageView)findViewById(R.id.imageView6);

        img7=(ImageView)findViewById(R.id.imageView7);
        img8=(ImageView)findViewById(R.id.imageView8);

        GetResponse();
    }


    private void GetResponse(){

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading please wait...");
        progressDialog.show();

        final String Url ="https://plobalapps.s3.ap-southeast-1.amazonaws.com/assets/test-sample.json";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {

            public void onResponse(String response) {
                Log.i("activationresponse", "" + response);
                try {

                      progressDialog.dismiss();

                    JSONObject jsonobj = new JSONObject(response);


                        JSONArray datajsonarray = jsonobj.getJSONArray("images");

                    HashMap<Integer, String> hm = new HashMap<Integer, String>();
                        for (int i = 0; i < datajsonarray.length(); i++) {

                            JSONObject childjsonobject = datajsonarray.getJSONObject(i);


                            String_img= childjsonobject.getString("img");

                            String_order= childjsonobject.getString("order");

                            String_title= childjsonobject.getString("title");


                            hm.put(Integer.parseInt(String_order), String_img);



                        }

                    Map<Integer, String> map = new TreeMap<Integer, String>(hm);
                    System.out.println("After Sorting:");



                    Set set2 = map.entrySet();
                    Iterator iterator2 = set2.iterator();
                    int i=0;
                    while(iterator2.hasNext()) {
                        Map.Entry me2 = (Map.Entry)iterator2.next();
                        System.out.print(me2.getKey() + ": ");
                        System.out.println(me2.getValue());



                        i++;
                        if( i==1)
                        {
                            Glide.with(MainActivity.this)
                                    .load(me2.getValue())
                                    .override(100, 200)
                                    .into(img1);
                        }
                        else if(i==2)
                        {
                            Glide.with(MainActivity.this)
                                    .load(me2.getValue())
                                    .override(100, 200)
                                    .into(img2);
                        }
                        else if(i==3)
                        {
                            Glide.with(MainActivity.this)
                                    .load(me2.getValue())
                                    .override(100, 200)
                                    .into(img3);
                        }
                        else if(i==4)
                        {
                            Glide.with(MainActivity.this)
                                    .load(me2.getValue())
                                    .override(100, 200)
                                    .into(img4);
                        }
                        else if(i==5)
                        {
                            Glide.with(MainActivity.this)
                                    .load(me2.getValue())
                                    .override(100, 200)
                                    .into(img5);
                        }
                        else if(i==6)
                        {
                            Glide.with(MainActivity.this)
                                    .load(me2.getValue())
                                    .override(100, 200)
                                    .into(img6);
                        }
                        else if(i==7)
                        {
                            Glide.with(MainActivity.this)
                                    .load(me2.getValue())
                                    .override(100, 200)
                                    .into(img7);
                        }
                        else if(i==8)
                        {
                            Glide.with(MainActivity.this)
                                    .load(me2.getValue())
                                    .override(100, 200)
                                    .into(img8);
                        }


                        Log.i("response", "" + me2.getKey() +"\n"+me2.getValue());
                    }


                    } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }




            }
        },

                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError Error) {


                        progressDialog.dismiss();


                        Log.i("error", "" + Error.toString());

                    }

                }) {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();

                return headers;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        stringRequest.setShouldCache(false);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }
}