package com.example.protectress.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.protectress.Adapters.API;
import com.example.protectress.Adapters.ChatAdapter;
import com.example.protectress.Modals.MessageModal;
import com.example.protectress.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatBotActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText message;
    List<MessageModal> list;
    ChatAdapter chatAdapter;
    ImageButton send;
    //set the types of json
    public static final MediaType JSON = MediaType.get("application/json");
    // initilize okhttp instance here
    OkHttpClient client= new OkHttpClient();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);

        recyclerView=findViewById(R.id.recyclerView);
        message= findViewById(R.id.msgInput);
        send=findViewById(R.id.msgButton);

        list= new ArrayList<>();
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        //leave space for top and add to bottom
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        chatAdapter= new ChatAdapter(list);
        recyclerView.setAdapter(chatAdapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question= message.getText().toString();
                if(question.isEmpty())
                {
                    Toast.makeText(ChatBotActivity.this, "Write something", Toast.LENGTH_SHORT).show();
                }
                else{
                    addToChat(question, MessageModal.SENT_BY_ME);
                    message.setText("");
                    callAPI(question);
                }
            }
        });
    }
    public void addToChat(String question,String sentby)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                list.add(new MessageModal(question,sentby));
                chatAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(chatAdapter.getItemCount());
            }
        });
    }
    void addResponse(String response){
        list.remove(list.size()-1);
        addToChat(response, MessageModal.SENT_BY_BOT);
    } // addResponse End Here =======

    void callAPI(String question) {
        // okhttp
        list.add(new MessageModal("Typing...", MessageModal.SENT_BY_BOT));

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model", "gpt-3.5-turbo-instruct");
            jsonBody.put("prompt", question);
            jsonBody.put("max_tokens", 3000);
            jsonBody.put("temperature", 0);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        RequestBody requestBody = RequestBody.create(jsonBody.toString(), JSON);
        Request request = new Request.Builder()
                .url(API.API_URL)
                .header("Authorization", "Bearer " + API.API)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                addResponse("Failed to load response due to" + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0).getString("text");
                        addResponse(result.trim());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    addResponse("Failed to load response due to" + response.body().toString());
                }

            }
        });
    }

    }