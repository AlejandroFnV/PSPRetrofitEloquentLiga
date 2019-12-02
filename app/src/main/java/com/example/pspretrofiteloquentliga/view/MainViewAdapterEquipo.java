package com.example.pspretrofiteloquentliga.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pspretrofiteloquentliga.JugadorActivity;
import com.example.pspretrofiteloquentliga.MainActivity;
import com.example.pspretrofiteloquentliga.R;
import com.example.pspretrofiteloquentliga.UpdateEquipo;
import com.example.pspretrofiteloquentliga.model.data.Equipo;
import com.example.pspretrofiteloquentliga.model.data.Jugador;

import java.util.List;

public class MainViewAdapterEquipo extends RecyclerView.Adapter<MainViewAdapterEquipo.ItemHolder> {

    private MainViewModel viewModel;
    private Context context;
    private OnItemClickListener listener;
    private LayoutInflater inflater;
    private List<Equipo> equipoList;
    private Activity activity;

    public interface OnItemClickListener{
        void onItemClick(Equipo equipo);
    }

    public MainViewAdapterEquipo(Context context /*OnItemClickListener listener*/) {
        this.context = context;
        this.listener = listener;
        this.inflater = LayoutInflater.from(context);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public MainViewAdapterEquipo.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_equipo, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewAdapterEquipo.ItemHolder holder, int position) {
        final Equipo current = equipoList.get(position);
        Glide.with(context)
                    .load(R.drawable.escudo)
                    .override(300, 300)
                    .into(holder.ivEquipo);

        holder.tvNombreEquipo.setText(current.getNombre());
        holder.tvCiudad.setText(current.getCiudad());
        holder.tvEstadio.setText(current.getEstadio());
        holder.tvAforo.setText(current.getAforo() + "");
        holder.cdEquipo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(v.getContext(), UpdateEquipo.class);
                intent.putExtra("id", current.getId());
                intent.putExtra("nombre", current.getNombre());
                intent.putExtra("ciudad", current.getCiudad());
                intent.putExtra("estadio", current.getEstadio());
                intent.putExtra("aforo", current.getAforo());
                intent.putExtra("imagen_equipo", current.getImagenEquipo());
                v.getContext().startActivity(intent);
                return true;
            }

        });
        holder.cdEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), JugadorActivity.class);
                intent.putExtra("idequipo", current.getId());
                v.getContext().startActivity(intent);
            }
        });
        holder.ivTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("¿Desea eliminar este equipo?");
                alertDialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel = ViewModelProviders.of((FragmentActivity) context).get(MainViewModel.class);
                        viewModel.deleteEquipo(current);
                        Toast.makeText(context, "Equipo eliminado con éxito", Toast.LENGTH_SHORT).show();
                        v.getContext().startActivity(new Intent(context, MainActivity.class));
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

            }
        });
    }

    @Override
    public int getItemCount() {
        int element = 0;
        if(equipoList != null) {
            element = equipoList.size();
        }
        return element;
    }

    public void setData(List<Equipo> equipoList){
        this.equipoList = equipoList;
        notifyDataSetChanged();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        private ImageView ivEquipo, ivTrash;
        private TextView tvNombreEquipo, tvCiudad, tvEstadio, tvAforo;
        private CardView cdEquipo;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            cdEquipo = itemView.findViewById(R.id.cdEquipo);
            ivEquipo = itemView.findViewById(R.id.ivEquipo);
            ivTrash = itemView.findViewById(R.id.ivTrash);
            tvNombreEquipo = itemView.findViewById(R.id.tvNombreEquipo);
            tvCiudad = itemView.findViewById(R.id.tvCiudad);
            tvEstadio = itemView.findViewById(R.id.tvEstadio);
            tvAforo = itemView.findViewById(R.id.tvAforo);
        }

    }
}
