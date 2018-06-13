package com.example.bochkarevfy.bcontact;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {


    MyRecyclerViewAdapter adapter;
    MyTask mt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mt = new MyTask();
        mt.execute();

        List<User> usersNames = null;

        try {
            usersNames = mt.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, usersNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    class MyTask extends AsyncTask<Void, Void, List<User>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<User> doInBackground(Void... params) {
            String url = "http://nbics.net/VSM.Web.Plugins.Contacts/ContactsHome/GetContacts?email=tonitonytoni11@gmail.com&PasswordHash=5fa285e1bebe0a6623e33afc04a1fbd";
            List<User> usersNames = new ArrayList<>();


            URL obj = null;
            StringBuilder response = null;
            try {
                obj = new URL(url);

                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                // optional default is GET
                con.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            Gson gson = new Gson();
            User[] user = null;
            try {
                user = gson.fromJson(response.toString(), User[].class);
                User[] users = user;
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }

            for (User anUser : user) {
                usersNames.add(new User(anUser.getFirstName(), anUser.getSurName(), anUser.getImage()));
            }
            return usersNames;
        }

        @Override
        protected void onPostExecute(List<User> result) {
            super.onPostExecute(result);
        }
    }
}