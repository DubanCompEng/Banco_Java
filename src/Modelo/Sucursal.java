package Modelo;

import Modelo.interfaces.Listable;
import Modelo.interfaces.Selecionable;
import java.io.Serializable;

public class Sucursal implements Serializable, Listable, Selecionable{
    private String nombreSucursal;
    private String direccion;

    public Sucursal(String nombreSucursal, String direccion) {
        this.nombreSucursal = nombreSucursal;
        this.direccion = direccion;
    }

    public String getNombreSucursal(){return this.nombreSucursal;}
    public void setNombreSucursal(String nombreSucursal) {this.nombreSucursal = nombreSucursal;}

    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}


    @Override
    public String mostrarInfo() {
          return nombreSucursal + " | " + direccion + " |";
    }
}