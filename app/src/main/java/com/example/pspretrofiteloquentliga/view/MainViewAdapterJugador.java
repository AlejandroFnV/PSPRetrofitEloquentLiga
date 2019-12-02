package com.example.pspretrofiteloquentliga.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pspretrofiteloquentliga.JugadorActivity;
import com.example.pspretrofiteloquentliga.R;
import com.example.pspretrofiteloquentliga.model.data.Jugador;

import java.util.List;

public class MainViewAdapterJugador extends RecyclerView.Adapter<MainViewAdapterJugador.ItemHolder> {

    private MainViewModel viewModel;
    private Context context;
    private LayoutInflater inflater;
    private List<Jugador> jugadorList;

    public MainViewAdapterJugador(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MainViewAdapterJugador.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_jugador, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewAdapterJugador.ItemHolder holder, int position) {
        final Jugador current = jugadorList.get(position);
        Glide.with(context)
                .load(R.drawable.jugador)
                .override(300, 300)
                .into(holder.ivJugador);
        holder.tvNombreJugador.setText(current.getNombre());
        holder.tvApellidosJugador.setText(current.getApellidos());
        holder.cvJugador.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("¿Desea eliminar este jugador?");
                alertDialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel = ViewModelProviders.of((FragmentActivity) context).get(MainViewModel.class);
                        viewModel.deleteJugador(current);
                        Toast.makeText(context, "Jugador eliminado con éxito", Toast.LENGTH_SHORT).show();
                        v.getContext().startActivity(new Intent(context, JugadorActivity.class));
                    }
                });
                alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        int element = 0;
        if(jugadorList != null) {
            element = jugadorList.size();
        }
        return element;
    }

    public void setData(List<Jugador> jugadorList){
        this.jugadorList = jugadorList;
        notifyDataSetChanged();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        private ImageView ivJugador;
        private TextView tvNombreJugador, tvApellidosJugador;
        private CardView cvJugador;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            ivJugador = itemView.findViewById(R.id.ivJugador);
            tvNombreJugador = itemView.findViewById(R.id.tvNombreJugador);
            tvApellidosJugador = itemView.findViewById(R.id.tvApellidosJugador);
            cvJugador = itemView.findViewById(R.id.cvJugador);
        }
    }
}
