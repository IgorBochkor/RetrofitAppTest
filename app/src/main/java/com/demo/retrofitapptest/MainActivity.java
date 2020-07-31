package com.demo.retrofitapptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PostsAdapter adapter;
    private TextView textViewText;
    private TextView textViewIni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewText = findViewById(R.id.textViewText);
        textViewIni = findViewById(R.id.textViewBash);
        recyclerView = findViewById(R.id.recycleView);
        final ArrayList<PostModel> postModels = new ArrayList<>();
        adapter = new PostsAdapter(postModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Api api = Api.getApi();
        ServiceApi serviceApi = api.getServiceApi();
        serviceApi.getData("bash",5).enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                postModels.addAll(response.body());
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });


//        serviceApi.getData("bash",5)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<PostModel>>() {
//                    @Override
//                    public void accept(List<PostModel> postModels) throws Exception {
//                        adapter.setPost(postModels);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                    }
//                });

//        PostModel postModel1 = new PostModel();
//        PostModel postModel2 = new PostModel();
//        postModel1.setName("hhh");
//        postModel2.setName("hhheee");
//        postModel1.setDesc("aaaa");
//        postModel2.setDesc("aaaa3333");
//        postModels.add(postModel1);
//        postModels.add(postModel2);


    }
}