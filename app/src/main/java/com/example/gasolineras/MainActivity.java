package com.example.gasolineras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
Mapa mapa;

    public  Gasolineras  gasolineras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gasolineras= new Gasolineras();





    }


    public void agregar_gasolinera(View view){
      Intent intent= new Intent(this,Agregar_gasolinera.class);
      startActivity(intent);

    }
    public  void  modificar_gasolinera(View view){
        Intent intent= new Intent(this,Modificar_gasolinera.class);
        startActivity(intent);
    }

    public void eliminar_gasolinera(View view){
        Intent intent= new Intent(this,Eliminar_gasolinera.class);
        startActivity(intent);

    }
    public  void  ver_mapa(View view){
        Intent intent= new Intent(this, Ver_todas_gasolineras.class);
        startActivity(intent);
    }

    public void ver_gasolineras(View view){
        Intent intent = new Intent(this,Ver_gasolineras.class);
        startActivity(intent) ;
    }

    // para poner el fragmento del mapa

    private void add_fragment(Fragment fragment){
        // este metodo permite remplazar un fragment por otro () siempre hay que hacer un comit
        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,fragment).

                // el setTrasition envia una animacion
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();

    }


}