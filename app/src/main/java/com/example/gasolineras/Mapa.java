package com.example.gasolineras;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Mapa extends Fragment {
    public GoogleMap Map;
    public ArrayList<Modelo_gasolinera> gasolineras= new ArrayList<>();
    public LatLng ubicacion_colombia= new LatLng(2.8894434,-73.783892);

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            Map=googleMap;
            // pasar a agregar marcador inico
            crear_marcadores();
            mover_camara(ubicacion_colombia);
            enviar_zoom(5);


        }
    };

    private void crear_marcadores() {
        for(Modelo_gasolinera gasolinera:gasolineras){
            añadir_marcador(gasolinera);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mapa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    public void añadir_marcador(Modelo_gasolinera gasolinera){

        LatLng ubicacion = new LatLng(gasolinera.getLatitud(), gasolinera.longitud);
        Map.addMarker(new MarkerOptions().position(ubicacion).title(gasolinera.getNombre_estacion()));
        Map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }
    public void añadir_marcador_camara(Modelo_gasolinera gasolinera){

        LatLng ubicacion = new LatLng(gasolinera.getLatitud(), gasolinera.longitud);
        Map.addMarker(new MarkerOptions().position(ubicacion).title(gasolinera.getNombre_estacion()));
        Map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mover_camara(ubicacion);

    }


    public void mover_camara(LatLng ubicacion){
        Map.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
    }

    public void enviar_zoom(float zoom){
        Map.moveCamera(CameraUpdateFactory.zoomTo(zoom));

    }

    public ArrayList<Modelo_gasolinera> getUbicaciones() {
        return gasolineras;
    }

    public void setUbicaciones(ArrayList<Modelo_gasolinera> ubicaciones) {
        this.gasolineras = ubicaciones;
    }
    public void eliminar_marcadores(){
        Map.clear();

    }
}