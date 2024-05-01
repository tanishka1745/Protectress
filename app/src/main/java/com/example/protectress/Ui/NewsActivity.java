package com.example.protectress.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.protectress.R;
import com.example.protectress.Retrofit.ApiUtilities;
import com.example.protectress.Retrofit.ModalClass;
import com.example.protectress.Retrofit.NewsAdapter;
import com.example.protectress.Retrofit.NewsModal;
import com.example.protectress.Retrofit.Retrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    String API="455a09ecdbc245bb9bbd0ea3d1d07975";
    ArrayList<NewsModal> list;
    NewsAdapter adapterClass;
    String country="in";
    String category="crime";
    RecyclerView recyclerViewoHome;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerViewoHome=findViewById(R.id.recyclerview);
        list= new ArrayList<>();
        recyclerViewoHome.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        adapterClass= new NewsAdapter(list,getApplicationContext());
        recyclerViewoHome.setAdapter(adapterClass);
        findNews();



    }
    private void findNews() {

        Retrofit.getApiInterface().getCategoryNews(country,category,100,API).enqueue(new Callback<ModalClass>() {
            @Override
            public void onResponse(Call<ModalClass> call, Response<ModalClass> response) {
                list.addAll(response.body().getArticles());
                adapterClass.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ModalClass> call, Throwable t) {

            }
        });



    }
}