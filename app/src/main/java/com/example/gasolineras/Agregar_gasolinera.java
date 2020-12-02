package com.example.gasolineras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class Agregar_gasolinera extends AppCompatActivity {
        EditText nombre,empresa,departamento,municipio,ubicacion,latitud,longitud;
        String sid,snombre,sempresa,sdepartamento,smuniciopio,subicacion;
        double slatitud,slogitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_gasolinera);
        nombre=findViewById(R.id.nombre_gasolinera);
        empresa=findViewById(R.id.empresa_gasolinera);
        departamento=findViewById(R.id.departamento_gasolinera);
        municipio=findViewById(R.id.municipio_gasolinera);
        ubicacion=findViewById(R.id.ubicacion_gasolinera);
        latitud=findViewById(R.id.latitud);
        longitud=findViewById(R.id.longitud);




    }

    public  void montar_gasolinera(View view){

      snombre= nombre.getText().toString();
    sempresa=empresa.getText().toString();
    sdepartamento=departamento.getText().toString();
    smuniciopio=municipio.getText().toString();
    subicacion=ubicacion.getText().toString();
    slatitud=Double.parseDouble(latitud.getText().toString());
    slogitud=Double.parseDouble(longitud.getText().toString());


    Modelo_gasolinera gasolinera= new Modelo_gasolinera(snombre,sempresa,sdepartamento,smuniciopio,subicacion,slatitud,slogitud);
    gasolinera.montar_a_firebase();
        Snackbar snackbar=Snackbar.make(findViewById(R.id.contenedor_agregar),"Guardado correctamente", BaseTransientBottomBar.LENGTH_LONG);
        snackbar.show();
        vaciar_campos();


    }
    public void vaciar_campos(){
        nombre.setText("");
        empresa.setText("");
        departamento.setText("");
        municipio.setText("");
        ubicacion.setText("");
        longitud.setText("");
        latitud.setText("");

    }

}