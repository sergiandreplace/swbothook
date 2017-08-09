package com.blindbugs.swbot.application.datasource.swapi.sw;

import com.blindbugs.swbot.application.datasource.swapi.models.ApiPeople;
import com.blindbugs.swbot.application.datasource.swapi.models.ApiPlanet;
import com.blindbugs.swbot.application.datasource.swapi.models.ApiSWModelList;
import com.blindbugs.swbot.domain.planet.PlanetCollection;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Oleur on 21/12/2014.
 * Star Wars API interface for making calls.
 */
public interface StarWarsApi {

//    @GET("/People/")
//    public void getAllPeople(@Query("page") int page,
//                             Callback<SWModelList<People>> callback);

    @GET("/people/{id}/")
    public ApiPeople getPeople(@Path("id") int peopleId);

    @GET("/people/")
    public ApiSWModelList<ApiPeople> findPeople(@Query("search") String name);

//    @GET("/films/")
//    public void getAllFilms(@Query("page") int page,
//                            Callback<SWModelList<Film>> callback);
//
//    @GET("/films/{id}/")
//    public void getFilm(@Path("id") int filmId,
//                        Callback<Film> callback);
//
//    @GET("/starships")
//    public void getAllStarships(@Query("page") int page,
//                                Callback<SWModelList<Starship>> callback);
//
//    @GET("/starships/{id}/")
//    public void getStarship(@Path("id") int starshipId,
//                            Callback<Starship> callback);
//
//    @GET("/vehicles/")
//    public void getAllVehicles(@Query("page") int page,
//                               Callback<SWModelList<Vehicle>> callback);
//
//    @GET("/vehicles/{id}/")
//    public void getVehicle(@Path("id") int vehicleId,
//                           Callback<Vehicle> callback);
//
//    @GET("/species/")
//    public void getAllSpecies(@Query("page") int page,
//                              Callback<SWModelList<Species>> callback);
//
//    @GET("/species/{id}/")
//    public void getSpecies(@Path("id") int speciesId,
//                           Callback<Species> callback);
//
//    @GET("/planets/")
//    public void getAllPlanets(@Query("page") int page,
//                              Callback<SWModelList<PlanetCollection>> callback);
//
    @GET("/planets/{id}/")
    ApiPlanet getPlanet(@Path("id") int planetId);
//
}
