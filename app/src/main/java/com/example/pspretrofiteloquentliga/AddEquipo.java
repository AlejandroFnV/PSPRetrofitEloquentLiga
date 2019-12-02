package com.example.pspretrofiteloquentliga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pspretrofiteloquentliga.model.data.Equipo;
import com.example.pspretrofiteloquentliga.view.MainViewModel;

import java.io.IOException;

public class AddEquipo extends AppCompatActivity {

    private EditText etNombre, etCiudad, etEstadio, etAforo;
    private ImageView ivAddEquipo;
    private Button btAddEquipo;
    private MainViewModel viewModel;

    private String imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_equipo);

        initComponents();
        initEvents();

    }

    private void initComponents() {
        etNombre = findViewById(R.id.etNombre);
        etCiudad = findViewById(R.id.etCiudad);
        etEstadio = findViewById(R.id.etEstadio);
        etAforo = findViewById(R.id.etAforo);
        btAddEquipo = findViewById(R.id.btAddEquipo);
        ivAddEquipo = findViewById(R.id.ivAddEquipo);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        if(imageUri != null){
            Glide.with(this)
                    .load(imageUri)
                    .override(300, 300)
                    .into(ivAddEquipo);
        }else{
            Glide.with(this)
                    .load(R.drawable.escudo)
                    .override(300, 300)
                    .into(ivAddEquipo);
        }


    }

    private void initEvents() {
        btAddEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Equipo equipo = new Equipo();
                //equipo.setId(0);
                equipo.setNombre(etNombre.getText().toString());
                equipo.setCiudad(etCiudad.getText().toString());
                equipo.setEstadio(etEstadio.getText().toString());
                equipo.setAforo(Integer.parseInt(etAforo.getText().toString()));
                equipo.setImagenEquipo(ivAddEquipo.getDrawable().toString());
                viewModel.addEquipo(equipo);
                Toast.makeText(AddEquipo.this, "Equipo insertado correctamente", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddEquipo.this, MainActivity.class));
            }
        });

        ivAddEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK &&
                data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            Log.v("xyz", imageUri.toString());
            Bitmap bitmapBig = null;
            try {
                bitmapBig =
                        MediaStore.Images.Media.getBitmap(this.getContentResolver(),
                                imageUri);
            } catch (IOException e) {
                Log.d("esc exception", "no crea bitmap");
                e.printStackTrace();
            }
            Glide.with(this)
                    .load(imageUri)
                    .override(500, 500)// prueba de escalado
                    .into(ivAddEquipo);
            this.imageUri = data.getDataString();
        }
    }

}
