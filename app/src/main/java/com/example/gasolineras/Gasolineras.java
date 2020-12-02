package com.example.gasolineras;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Gasolineras {
    ArrayList<Modelo_gasolinera> gasolineras= new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference myRef;
    Activity activity;

    public Gasolineras() {
    database=FirebaseDatabase.getInstance();
    myRef=database.getReference();


    }


    public ArrayList<Modelo_gasolinera> getGasolineras() {
        return gasolineras;
    }



    public DatabaseReference get_referencia_gasolinera (){
        return myRef.child("gasolineras");
    }

    public String[] nombres (){

        Log.e("nombres",gasolineras.get(1).getNombre_estacion());
        String[] nombres= new String[gasolineras.size()];
        for (int i=0 ; i<gasolineras.size();i++){
            nombres[i]=gasolineras.get(i).getNombre_estacion();
            Log.e("nombres",gasolineras.get(i).getNombre_estacion());
        }

        return nombres;
    }
}

