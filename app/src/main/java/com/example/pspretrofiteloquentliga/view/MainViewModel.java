package com.example.pspretrofiteloquentliga.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pspretrofiteloquentliga.model.Repository;
import com.example.pspretrofiteloquentliga.model.data.Equipo;
import com.example.pspretrofiteloquentliga.model.data.Jugador;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Repository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public LiveData<List<Equipo>> getLiveEquipoList(){
        return repository.getLiveEquipoList();
    }

    public LiveData<List<Jugador>> getLiveJugadorList(){
        return repository.getLiveJugadorList();
    }

    public void addEquipo(Equipo equipo){
        repository.add(equipo);
    }

    public void addJugador(Jugador jugador) {
        repository.add(jugador);
    }

    public void updateEquipo(Equipo equipo){
        repository.updateEquipo(equipo);
    }

    public void updateJugador(Jugador jugador){
        repository.updateJugador(jugador);
    }

    public void deleteEquipo(Equipo equipo){
        repository.delete(equipo);
    }

    public void deleteJugador(Jugador jugador){
        repository.delete(jugador);
    }

    public void setUrl(String url) {
        repository.setUrl(url);
    }

}
