package com.example.wilmer.foodzappbusiness.Firebase;

public class BusinessUser{
    String mNombreEmpresa;
    String mClave;
    String mNumero;
    String mEmail;
    String mRNC;
    String mDescripcion;

    public BusinessUser(){

    }

    public BusinessUser(String nombreEmpresa,String clave,String numero,String email,String RNC,String descripcion){
        this.mNombreEmpresa = nombreEmpresa;
        this.mClave = clave;
        this.mNumero = numero;
        this.mEmail = email;
        this.mRNC = RNC;
        this.mDescripcion=descripcion;
    }


}
