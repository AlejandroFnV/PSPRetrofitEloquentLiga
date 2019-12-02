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

import com.bumptech.glide.Glide;
import com.example.pspretrofiteloquentliga.model.data.Jugador;
import com.example.pspretrofiteloquentliga.view.MainViewModel;

import java.io.IOException;

public class AddJugador extends AppCompatActivity {

    private MainViewModel viewModel;
    private EditText etNombreJ, etApellidos;
    private ImageView ivAddJugador;
    private Button btAddJugador;

    private String imageUri;
    private int idequipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_jugador);

        initComponents();
        initEvents();

    }

    private void initComponents() {
        idequipo = getIntent().getIntExtra("idequipo", 0);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        etNombreJ = findViewById(R.id.etNombreJ);
        etApellidos = findViewById(R.id.etApellidosJ);
        btAddJugador = findViewById(R.id.btAddJugador);
        ivAddJugador = findViewById(R.id.ivAddJugador);
        Glide.with(this)
                .load(R.drawable.jugador)
                .override(500, 500)
                .into(ivAddJugador);
    }

    private void initEvents() {
        btAddJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jugador jugador = new Jugador();
                jugador.setId(0);
                jugador.setNombre(etNombreJ.getText().toString());
                jugador.setApellidos(etApellidos.getText().toString());
                jugador.setIdequipo(idequipo);
                jugador.setImagenJugador("imagen_jugador2");
                viewModel.addJugador(jugador);
                startActivity(new Intent(AddJugador.this, JugadorActivity.class));
            }
        });

        ivAddJugador.setOnClickListener(new View.OnClickListener() {
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
                    .into(ivAddJugador);
            this.imageUri = data.getDataString();
        }
    }

}
