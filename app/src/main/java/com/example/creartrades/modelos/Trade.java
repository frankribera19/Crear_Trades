package com.example.creartrades.modelos;

import java.io.Serializable;

public class Trade implements Serializable{
    private String estado;//Pongo si estoy en simulado o real
    private String entrada;//Pongo si es Largo o Corto
    private int anyo;
    private int dia;
    private String mes;
    private String mercado;
    private int contratos;
    private float valorPunto;//Poner el precio de cada punto
    private float puntos;
    private float total;
    private String emocion;//Poner la emocion sentida

    public Trade(String estado, String entrada, int anyo, int dia, String mes, String mercado, int contratos, float valorPunto, float puntos, float total, String emocion) {
        this.estado = estado;
        this.entrada = entrada;
        this.anyo = anyo;
        this.dia = dia;
        this.mes = mes;
        this.mercado = mercado;
        this.contratos = contratos;
        this.valorPunto = valorPunto;
        this.puntos = puntos;
        this.total = total;
        this.emocion = emocion;
    }

    public Trade(String estado, String entrada, int anyo, int dia, String mes, String mercado, int contratos, float valorPunto, float puntos, String emocion) {
        this.estado = estado;
        this.entrada = entrada;
        this.anyo = anyo;
        this.dia = dia;
        this.mes = mes;
        this.mercado = mercado;
        this.contratos = contratos;
        this.valorPunto = valorPunto;
        this.puntos = puntos;
        this.emocion = emocion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getMercado() {
        return mercado;
    }

    public void setMercado(String mercado) {
        this.mercado = mercado;
    }

    public int getContratos() {
        return contratos;
    }

    public void setContratos(int contratos) {
        this.contratos = contratos;
    }

    public float getValorPunto() {
        return valorPunto;
    }

    public void setValorPunto(float valorPunto) {
        this.valorPunto = valorPunto;
    }

    public float getPuntos() {
        return puntos;
    }

    public void setPuntos(float puntos) {
        this.puntos = puntos;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getEmocion() {
        return emocion;
    }

    public void setEmocion(String emocion) {
        this.emocion = emocion;
    }

    public float calcularTotal(){
        return total = (contratos * valorPunto) * puntos;
    }
}
