package com.example.pspretrofiteloquentliga.model;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pspretrofiteloquentliga.model.data.Equipo;
import com.example.pspretrofiteloquentliga.model.data.Jugador;
import com.example.pspretrofiteloquentliga.model.rest.Client;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private Client.EquipoClient equipoClient;
    private Client.JugadorClient jugadorClient;

    private String url = "54.88.146.76";
    private List<Jugador> jugadorList = new ArrayList<>();
    private List<Equipo> equipoList = new ArrayList<>();
    private MutableLiveData<List<Jugador>> liveJugadorList = new MutableLiveData<>();
    private MutableLiveData<List<Equipo>> liveEquipoList = new MutableLiveData<>();

    public Repository() {
        retrieveApiClient(url);

        fetchEquipoList();
        fetchJugadorList();
    }

    private void retrieveApiClient(String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + url + "/web/psp/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        equipoClient = retrofit.create(Client.EquipoClient.class);
        jugadorClient = retrofit.create(Client.JugadorClient.class);
    }

    public void fetchEquipoList(){
        Call<List<Equipo>> call = equipoClient.getEquipos();
        call.enqueue(new Callback<List<Equipo>>() {
            @Override
            public void onResponse(Call<List<Equipo>> call, Response<List<Equipo>> response) {
                Log.v("orFetchEquipo", response.body().toString());
                equipoList = response.body();
                liveEquipoList.setValue(equipoList); //(response.body())
            }
            @Override
            public void onFailure(Call<List<Equipo>> call, Throwable t) {
                Log.v("ofFetchEquipo", t.getLocalizedMessage());
                equipoList = new ArrayList<>();
            }
        });
    }

    public void fetchJugadorList() {
        Call<List<Jugador>> call = jugadorClient.getJugadores();
        call.enqueue(new Callback<List<Jugador>>() {
            @Override
            public void onResponse(Call<List<Jugador>> call, Response<List<Jugador>> response) {
                Log.v("orFetchJugador", response.body().toString());
                jugadorList = response.body();
                liveJugadorList.setValue(jugadorList); //(response.body())
            }
            @Override
            public void onFailure(Call<List<Jugador>> call, Throwable t) {
                Log.v("ofFetchJugador", t.getLocalizedMessage());
                jugadorList = new ArrayList<>();
            }
        });
    }

    public LiveData<List<Equipo>> getLiveEquipoList(){
        return liveEquipoList;
    }

    public LiveData<List<Jugador>> getLiveJugadorList(){
        return liveJugadorList;
    }

    public void add(Equipo equipo){
        Call<Void> call = equipoClient.postEquipo(equipo);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.v("adde", "El equipo se ha insertado correctamente - " + response.body());
                fetchEquipoList();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.v("adde", "FAILURE: " + t.getMessage());

            }
        });
    }

    public void add(Jugador jugador) {
        Call<Void> call = jugadorClient.postJugador(jugador);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.v("addj", "El jugador se ha insertado correctamente" + response.body());
                fetchJugadorList();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.v("addj", t.getLocalizedMessage());

            }
        });
    }

    public void updateEquipo(Equipo equipo){
        Call<Integer> call = equipoClient.putEquipo(equipo.getId(), equipo);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.v("updE", "El equipo se ha modificado correctamente- " + response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("updE", "FAILURE: " + t.getMessage());
            }
        });
    }

    public void updateJugador(final Jugador jugador){
        Call<Integer> call = jugadorClient.putJugador(jugador.getId(), jugador);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.v("updJ", "El jugador se ha modificado correctamente");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("updJ", "FAILURE: " + t.getMessage());
            }
        });
    }

    public void delete(Equipo equipo){
        Call<Integer> call = equipoClient.deleteEquipo(equipo.getId());
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.v("deleteE", "El equipo se ha eliminado correctamente");
                fetchEquipoList();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("deleteE", "FAILURE: " + t.getMessage());
            }
        });
    }

    public void delete(Jugador jugador){
        Call<Integer> call = jugadorClient.deleteJugador(jugador.getId());
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.v("deleteJ", "El jugador se ha eliminado correctamente");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("deleteJ", "FAILURE: " + t.getMessage());
            }
        });
    }

    public void setUrl(String url) {
        this.url = url;
        retrieveApiClient(url);
    }

}
