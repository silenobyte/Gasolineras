package com.example.gasolineras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Ver_todas_gasolineras extends AppCompatActivity {
    ArrayList<Modelo_gasolinera> gasolineras= new ArrayList<>();
    ArrayList<String> nombres=new ArrayList<>();
    Mapa mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_gasolineras);
         mapa= new Mapa();
        add_fragment(mapa);
        buscar_gasolineras();



    }

    private void agregar_marcadores() {
        mapa.setUbicaciones(gasolineras);
    }

    private void buscar_gasolineras() {

        Gasolineras g= new Gasolineras();
        g.get_referencia_gasolinera().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                gasolineras.clear();
                nombres.clear();
                for(DataSnapshot snapshot_gasolinera: snapshot.getChildren()){
                    Modelo_gasolinera gasolinera= snapshot_gasolinera.getValue(Modelo_gasolinera.class);
                    gasolineras.add(gasolinera);
                    nombres.add(gasolinera.nombre_estacion);
                }
                agregar_marcadores();









            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void add_fragment(Fragment fragment){
        // este metodo permite remplazar un fragment por otro () siempre hay que hacer un comit
        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,fragment).

                // el setTrasition envia una animacion
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();

    }
    @Override
    public void onBackPressed() {
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}