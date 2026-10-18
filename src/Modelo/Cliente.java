package Modelo; // [REQUISITO: Package]

import Modelo.Cuentas.Cuenta;
import Modelo.Cuentas.CuentaCorriente;
import Modelo.interfaces.Listable;
import Modelo.interfaces.Ranking;
import Modelo.interfaces.Registrar;

import java.io.*;
import java.util.ArrayList;

public class Cliente implements Listable, Registrar, Ranking, Serializable{
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

    public String getRut() {
        return rut;
    }
    public String getNombre() {
        return nombre;
    }
    public String getClave() {
        return clave;
    }
    public ArrayList<Cuenta> getCuentas(){
        return cuentas;
    }
    
    
    //--Metodos
    public void agregarCuenta(Cuenta cuenta){
        cuentas.add(cuenta);
    }
    public void removerCuenta(Cuenta cu){
        cuentas.remove(cu);
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
    public String mostrarInfo() {
        return nombre + "| " + rut + ".";
    }

    
    //---Gets y Sets
    public static int getContador() {return contador;}
    public static void setContador(int contador) {Cliente.contador = contador;}

    public double getSaldoTotal() {return saldoTotal;}
    public void setSaldoTotal(double saldoTotal) {this.saldoTotal = saldoTotal;}
}
