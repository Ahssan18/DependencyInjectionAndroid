package com.zebra.dependencyinjectionandroid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zebra.dependencyinjectionandroid.R;
import com.zebra.dependencyinjectionandroid.injector.ApiInterface;
import com.zebra.dependencyinjectionandroid.injector.DaggerApiComponents;
import com.zebra.dependencyinjectionandroid.injector.models.Post;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    @Inject
    Retrofit retrofit;
    private Button button;
    private TextView tvEndpoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvEndpoint=findViewById(R.id.tv_endpoint);
        button=findViewById(R.id.btn_connect);
        DaggerApiComponents.create().injectMain(this);
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface.getPosts().enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if(response.isSuccessful())
                        {
                            List<Post> posts=response.body();
                            tvEndpoint.append(posts.toString());
                            Toast.makeText(MainActivity.this, ""+posts.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {

                    }
                });
            }
        });

    }
}