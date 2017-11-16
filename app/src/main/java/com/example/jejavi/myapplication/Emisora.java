package com.example.jejavi.myapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;

/**
 * Created by jejavi on 13/11/17.
 */

public class Emisora {
    String nombre,ciudad,url;
    Drawable logo;

    public Emisora() {
    }

    public Emisora(String nombre, String ciudad, String url, Drawable logo) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.url = url;
        this.logo = logo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Drawable getLogo() {
        return logo;
    }

    public void setLogo(Drawable logo) {
        this.logo = logo;
    }


}
