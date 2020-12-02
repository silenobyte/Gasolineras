package com.example.gasolineras;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Modelo_gasolinera {
 String id ;
 String nombre_estacion;
 String empresa;
 String departamento;
 String municipio;
 String ubicacion;
 double latitud;
 double longitud;
 FirebaseDatabase database = FirebaseDatabase.getInstance();
 DatabaseReference myRef = database.getReference();


    public Modelo_gasolinera() {
    }

    public Modelo_gasolinera(String nombre_estacion, String empresa, String departamento, String municipio, String ubicacion, double latitud, double longitud) {

        this.nombre_estacion = nombre_estacion;
        this.empresa = empresa;
        this.departamento = departamento;
        this.municipio = municipio;
        this.ubicacion = ubicacion;
        this.latitud = latitud;
        this.longitud = longitud;
    }
    public void montar_a_firebase(){
        this.id=myRef.push().getKey();

        myRef.child("gasolineras").child(this.id).setValue(this);

    }
    public void modificar_en_firebase(){
        Map<String,Object> modificacion = new HashMap<>();
        modificacion.put("empresa",this.empresa);
        modificacion.put("latitud",this.latitud);
        modificacion.put("longitud",this.longitud);
        modificacion.put("municipio",this.municipio);
        modificacion.put("nombre_estacion",this.nombre_estacion);
        modificacion.put("ubicacion",this.ubicacion);
        myRef.child("gasolineras").child(this.id).updateChildren(modificacion);
    }
    public void eliminar_firebase(){
        myRef.child("gasolineras").child(this.id).removeValue();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre_estacion() {
        return nombre_estacion;
    }

    public void setNombre_estacion(String nombre_estacion) {
        this.nombre_estacion = nombre_estacion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
