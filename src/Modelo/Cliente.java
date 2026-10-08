package Modelo; // [REQUISITO: Package]

import Modelo.Cuentas.Cuenta;
import Modelo.interfaces.Ranking;
import Modelo.interfaces.Registrar;

import java.io.*;
import java.util.ArrayList;

public class Cliente implements Registrar, Ranking, Serializable{
    public static int contador = 0;

    private String rut;
    private String nombre;
    private String clave;
    private ArrayList<Cuenta> cuentas;
    private double saldoTotal;

    public Cliente(String rut, String nombre, String clave){
        contador++;
        this.rut = rut;
        this.nombre = nombre;
        this.clave = clave;
        this.cuentas = new ArrayList<>();
    }

    public String getClave() {
        return clave;
    }

    public String getRut() {
        return rut;
    }


    public void agregarCuenta(Cuenta cuenta){
        cuentas.add(cuenta);
    }

    @Override
    public double getValorRanking() {
        return saldoTotal;
    }

    @Override
    public String toString() {
        return nombre + ";" + rut + ";";
    }
}
