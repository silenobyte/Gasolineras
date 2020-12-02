package com.example.gasolineras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Eliminar_gasolinera extends AppCompatActivity {
    Spinner spinner;
    ArrayList<Modelo_gasolinera> gasolineras= new ArrayList<>();
    ArrayList<String> nombres=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    int pos_eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_gasolinera);
        inicializar_spiner();
    }


    public void eliminar_gasolinera(View view){
        Modelo_gasolinera gasolinera= gasolineras.get(pos_eliminar);
        gasolinera.eliminar_firebase();
        Snackbar snackbar=Snackbar.make(findViewById(R.id.contenedor_agregar),"Eliminado correctamente", BaseTransientBottomBar.LENGTH_LONG);
        snackbar.show();
    }

    private void inicializar_spiner() {

        spinner= findViewById(R.id.spiner_modificar);
        arrayAdapter= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,nombres);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                pos_eliminar=i;
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

}