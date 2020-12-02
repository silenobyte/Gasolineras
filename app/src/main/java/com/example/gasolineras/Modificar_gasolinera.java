package com.example.gasolineras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Modificar_gasolinera extends AppCompatActivity {
    Spinner spinner;
    ArrayList<Modelo_gasolinera> gasolineras= new ArrayList<>();
    ArrayList<String> nombres=new ArrayList<>();
    EditText nombre,empresa,departamento,municipio,ubicacion,latitud,longitud;
    String sid,snombre,sempresa,sdepartamento,smuniciopio,subicacion;
    double slatitud,slogitud;
    int pos_modificar=0;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_gasolinera);
        inicializar_spiner();
        nombre=findViewById(R.id.nombre_gasolinera);
        empresa=findViewById(R.id.empresa_gasolinera);
        departamento=findViewById(R.id.departamento_gasolinera);
        municipio=findViewById(R.id.municipio_gasolinera);
        ubicacion=findViewById(R.id.ubicacion_gasolinera);
        latitud=findViewById(R.id.latitud);
        longitud=findViewById(R.id.longitud);
    }

    private void inicializar_spiner() {

        spinner= findViewById(R.id.spiner_modificar);
        arrayAdapter= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,nombres);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                llenar_campos(gasolineras.get(i));
                pos_modificar=i;
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
                spinner.setSelection(pos_modificar);
                llenar_campos(gasolineras.get(pos_modificar));

                arrayAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
    public void llenar_campos(Modelo_gasolinera gasolinera){
        nombre.setText(gasolinera.getNombre_estacion());
        empresa.setText(gasolinera.getEmpresa());
        departamento.setText(gasolinera.getDepartamento());
        municipio.setText(gasolinera.getMunicipio());
        ubicacion.setText(gasolinera.ubicacion);
        longitud.setText(String.valueOf(gasolinera.getLongitud()));
        latitud.setText(String.valueOf(gasolinera.getLatitud()));

    }

    public void modificar_gasolinera(View view){
        Modelo_gasolinera gasolinera=null;


            Toast.makeText(this,"modificado",Toast.LENGTH_LONG).show();
            gasolinera= gasolineras.get(pos_modificar);
            snombre= nombre.getText().toString();
            sempresa=empresa.getText().toString();
            sdepartamento=departamento.getText().toString();
            smuniciopio=municipio.getText().toString();
            subicacion=ubicacion.getText().toString();
            slatitud=Double.parseDouble(latitud.getText().toString());
            slogitud=Double.parseDouble(longitud.getText().toString());

            gasolinera.setNombre_estacion(snombre);
            gasolinera.setEmpresa(sempresa);
            gasolinera.setDepartamento(sdepartamento);
            gasolinera.setMunicipio(smuniciopio);
            gasolinera.setUbicacion(subicacion);
            gasolinera.setLatitud(slatitud);
            gasolinera.setLongitud(slogitud);
            gasolinera.modificar_en_firebase();

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