package Modelo;

import Modelo.Cliente;
import Modelo.interfaces.Listable;
import java.io.Serializable;
import java.util.HashMap;

public class Sucursal implements Serializable, Listable{
    private String nombreSucursal;
    private String direccion;
    private HashMap< String, Cliente> clientes;

    public Sucursal(String nombreSucursal, String direccion) {
        this.nombreSucursal = nombreSucursal;
        this.direccion = direccion;
        this.clientes = new HashMap<>();
    }

    public String getNombreSucursal(){return this.nombreSucursal;}
    public void setNombreSucursal(String nombreSucursal) {this.nombreSucursal = nombreSucursal;}

    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}

    public HashMap<String, Cliente> getClientes() {return clientes;}
    public void setClientes(HashMap<String, Cliente> clientes) {this.clientes = clientes;}

    @Override
    public String mostrarInfo() {
          return nombreSucursal + " | " + direccion + " | Clientes: " + clientes.size();
    }
}