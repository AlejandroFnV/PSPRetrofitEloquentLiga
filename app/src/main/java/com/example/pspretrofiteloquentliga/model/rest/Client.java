package com.example.pspretrofiteloquentliga.model.rest;

import com.example.pspretrofiteloquentliga.model.data.Equipo;
import com.example.pspretrofiteloquentliga.model.data.Jugador;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class Client {


    public interface EquipoClient{

        @DELETE("equipo/{id}")
        Call<Integer> deleteEquipo(@Path("id") int id);

        @GET("equipo/{id}")
        Call<Equipo> getEquipo(@Path("id") int id);

        @GET("equipo")
        Call<List<Equipo>> getEquipos();

        @POST("equipo")
        Call<Void> postEquipo(@Body Equipo equipo);

        @PUT("equipo/{id}")
        Call<Integer> putEquipo(@Path("id") int id, @Body Equipo equipo);
    }

    public interface JugadorClient{

        @DELETE("jugador/{id}")
        Call<Integer> deleteJugador(@Path("id") int id);

        @GET("jugador/{id}")
        Call<Jugador> getJugador(@Path("id") int id);

        @GET("jugador")
        Call<List<Jugador>> getJugadores();

        @GET("jugador/{idequipo}")
        Call<List<Jugador>> getJugadores2(int id);

        @POST("jugador")
        Call<Void> postJugador(@Body Jugador jugador);

        @PUT("jugador/{id}")
        Call<Integer> putJugador(@Path("id") int id, @Body Jugador jugador);
    }


}
