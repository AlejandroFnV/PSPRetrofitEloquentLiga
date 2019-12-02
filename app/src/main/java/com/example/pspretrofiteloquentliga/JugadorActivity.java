package com.example.pspretrofiteloquentliga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pspretrofiteloquentliga.model.data.Jugador;
import com.example.pspretrofiteloquentliga.view.MainViewAdapterJugador;
import com.example.pspretrofiteloquentliga.view.MainViewModel;

import java.util.List;

public class JugadorActivity extends AppCompatActivity {

    private Button btAñadirJugador;
    private RecyclerView rvJugador;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugador);

        btAñadirJugador = findViewById(R.id.btAñadirJugador);
        btAñadirJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idequipo = getIntent().getIntExtra("idequipo", 0);
                Intent intent = new Intent(JugadorActivity.this, AddJugador.class);
                intent.putExtra("idequipo", idequipo);
                startActivity(intent);
            }
        });

        rvJugador = findViewById(R.id.rvJugador);
        rvJugador.setLayoutManager(new GridLayoutManager(this, 2));
        final MainViewAdapterJugador adapter = new MainViewAdapterJugador(this);
        rvJugador.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getLiveJugadorList().observe(this, new Observer<List<Jugador>>() {
            @Override
            public void onChanged(List<Jugador> jugadores) {
                adapter.setData(jugadores);
            }
        });

    }
}
