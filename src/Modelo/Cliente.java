package Modelo; // [REQUISITO: Package]

import Modelo.Cuentas.Cuenta;
import Modelo.interfaces.Ranking;
import Modelo.interfaces.Registrar;

import java.io.*;
import java.util.ArrayList;

public class Cliente implements Registrar, Ranking, Serializable{
    public static int contador = 0;

    private final String rut;
    private final String nombre;
    private final String clave;
    private final ArrayList<Cuenta> cuentas;
    private double saldoTotal;

    //---Constructor
    public Cliente(String rut, String nombre, String clave){
        contador++;
        this.rut = rut;
        this.nombre = nombre;
        this.clave = clave;
        this.cuentas = new ArrayList<>();
    }
    
    //--Metodos
    public void agregarCuenta(Cuenta cuenta){
        cuentas.add(cuenta);
    }

    @Override
    public double getValorRanking() {
        double total = 0;
        for (Cuenta c : cuentas) {
            total += c.getSaldo();
        }
        return total;
    }

    @Override
    public String toString() {
        return nombre + ";" + rut + ";";
    }

    @Override
    public String toCSV() {
        
    }
    
    //---Gets y Sets
    public static int getContador() {return contador;}
    public static void setContador(int contador) {Cliente.contador = contador;}

    public double getSaldoTotal() {return saldoTotal;}
    public void setSaldoTotal(double saldoTotal) {this.saldoTotal = saldoTotal;}
}
