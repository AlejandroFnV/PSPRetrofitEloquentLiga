package com.example.pspretrofiteloquentliga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.pspretrofiteloquentliga.model.data.Equipo;
import com.example.pspretrofiteloquentliga.view.MainViewModel;

public class UpdateEquipo extends AppCompatActivity {

    private EditText etNombreUpd, etCiudadUpd, etEstadioUpd, etAforoUpd;
    private ImageView ivAddEquipoUpd;
    private Button btAddEquipoUpd;
    private MainViewModel viewModel;

    private String nombre, ciudad, estadio, imagen_equipo;
    private int aforo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_equipo);

        initData();
        initComponents();
        initEvents();

    }

    private void initData() {
        nombre = getIntent().getStringExtra("nombre");
        ciudad = getIntent().getStringExtra("ciudad");
        estadio = getIntent().getStringExtra("estadio");
        aforo = getIntent().getIntExtra("aforo", 0);
        imagen_equipo = getIntent().getStringExtra("imagen_equipo");

    }

    private void initComponents() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        etNombreUpd = findViewById(R.id.etNombreUpd);
        etNombreUpd.setText(nombre);

        etCiudadUpd = findViewById(R.id.etCiudadUpd);
        etCiudadUpd.setText(ciudad);

        etEstadioUpd = findViewById(R.id.etEstadioUpd);
        etEstadioUpd.setText(estadio);

        etAforoUpd = findViewById(R.id.etAforoUpd);
        etAforoUpd.setText(aforo + "");

        ivAddEquipoUpd = findViewById(R.id.ivAddEquipoUpd);
        //ivAddEquipoUpd.setImageResource(Integer.parseInt(imagen_equipo));

        btAddEquipoUpd = findViewById(R.id.btAddEquipoUpd);

        Glide.with(this)
                .load(R.drawable.escudo)
                .override(300, 300)
                .into(ivAddEquipoUpd);
    }

    private void initEvents() {
        btAddEquipoUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNombreUpd.setText(etNombreUpd.getText().toString());
                etCiudadUpd.setText(etCiudadUpd.getText().toString());
                etEstadioUpd.setText(etEstadioUpd.getText().toString());
                etAforoUpd.setText(etAforoUpd.getText().toString());
                viewModel.updateEquipo(new Equipo());
            }
        });
    }
}
