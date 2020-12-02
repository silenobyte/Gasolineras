package com.example.gasolineras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Ver_gasolineras extends AppCompatActivity {
    Spinner spinner;
    ArrayList<Modelo_gasolinera> gasolineras= new ArrayList<>();
    ArrayList<String> nombres=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    int pos_mostrar;
    Mapa mapa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_mapa);
        mapa= new Mapa();
        add_fragment(mapa);
        mapa.setUbicaciones(gasolineras);
        inicializar_spiner();
    }
    private void inicializar_spiner() {

        spinner= findViewById(R.id.spiner_modificar);
        arrayAdapter= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,nombres);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(mapa.Map!=null){
                mapa.Map.clear();
                mapa.añadir_marcador_camara(gasolineras.get(i));
                mapa.enviar_zoom(12);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



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


                arrayAdapter.notifyDataSetChanged();


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