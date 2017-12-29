package com.example.user.myvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final RequestQueue rq = Volley.newRequestQueue(MainActivity.this);
                StringRequest srq = new StringRequest(
                        Request.Method.POST,
                        "http://172.16.0.183:8080/jsonapp.json",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // assuming no prob and the res is found
                                textView.setText(response);
                                rq.stop();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // assuming got error reading res
                        textView.setText("Something went wrong");
                        error.printStackTrace();
                        rq.stop();
                    }
                });

                rq.add(srq);
            }
        });
    }
}
