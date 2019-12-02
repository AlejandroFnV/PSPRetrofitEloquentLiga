package com.example.pspretrofiteloquentliga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.pspretrofiteloquentliga.model.data.Equipo;
import com.example.pspretrofiteloquentliga.view.MainViewAdapterEquipo;
import com.example.pspretrofiteloquentliga.view.MainViewModel;
import com.example.pspretrofiteloquentliga.view.settings.SettingsActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btAñadirEquipo;
    private RecyclerView rvEquipo;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btAñadirEquipo = findViewById(R.id.btAñadirEquipo);
        btAñadirEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddEquipo.class));
            }
        });

        rvEquipo = findViewById(R.id.rvEquipo);
        rvEquipo.setLayoutManager(new LinearLayoutManager(this));
        final MainViewAdapterEquipo adapter = new MainViewAdapterEquipo(this);

        rvEquipo.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getLiveEquipoList().observe(this, new Observer<List<Equipo>>() {
            @Override
            public void onChanged(List<Equipo> equipos) {
                adapter.setData(equipos);
            }
        });

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Client.EquipoClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Client.EquipoClient equipoClient = retrofit.create(Client.EquipoClient.class);
        Call<List<Equipo>> call = equipoClient.getEquipos();
        call.enqueue(new Callback<List<Equipo>>() {
            @Override
            public void onResponse(Call<List<Equipo>> call, Response<List<Equipo>> response) {

                Log.v("onresponse", response.body().toString());

            }

            @Override
            public void onFailure(Call<List<Equipo>> call, Throwable t) {
                Log.v("onfailure", t.getLocalizedMessage());
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this /* Activity context */);
        String url = sharedPreferences.getString("url", "");
        viewModel.setUrl(url);
    }
}
