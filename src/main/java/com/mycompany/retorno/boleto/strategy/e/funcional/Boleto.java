/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.retorno.boleto.strategy.e.funcional;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author wellikson
 */
public class Boleto {

    private int id;
    private String codBanco;
    private LocalDate dataVenc;
    private LocalDateTime dataPag;
    private String cpfCliente;
    private double valor;
    private double multa;
    private double juros;
    private String agencia;
    private String contaBancaria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodBanco() {
        return codBanco;
    }

    public void setCodBanco(String codBanco) {
        this.codBanco = codBanco;
    }

    public LocalDate getDataVenc() {
        return dataVenc;
    }

    public void setDataVenc(LocalDate dataVenc) {
        this.dataVenc = dataVenc;
    }

    public LocalDateTime getDataPag() {
        return dataPag;
    }

    public void setDataPag(LocalDateTime dataPag) {
        this.dataPag = dataPag;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(String contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    @Override
    public String toString() {
        String str = String.format("Id: %5d; Banco: %3s;", id, codBanco);
        String ag = "";
        if (agencia != null && !agencia.isEmpty() && contaBancaria != null && !contaBancaria.isEmpty()) {
            ag = String.format(" Ag: %6s; CC: %10s;", agencia, contaBancaria);
        }

        str += ag + String.format(
                "\nVenc: %s; Pag: %s; CPF:%s; Valor: %7.2f;",
                ProcessarBoletos.FORMATO_DATA.format(dataVenc),
                formatDataPag(), formatCpf(), valor);
        if (multa > 0) {
            str += String.format("\n Multa: %10.2f;", multa);
        }

        if (juros > 0) {
            str += String.format("\n Juros: %10.2f;", juros);
        }

        return str += "\n-------------------------------"
                + "-------------------------------------------------\n";
    }

    private String formatCpf() {
        String cpf = "";
        if (codBanco.equals("002")) {
            cpf += cpfCliente.substring(0, 3) + "." + cpfCliente.substring(3, 6)
                    + "." + cpfCliente.substring(6, 9) + "-" + cpfCliente.substring(9, 11);
        } else {
            cpf = cpfCliente;
        }
        return cpf;
    }

    private String formatDataPag() {
        String dtPag;
        if (codBanco.equals("002")) {
            dtPag = ProcessarBoletos.FORMATO_DATA_HORA.format(dataPag);
        } else {
            dtPag = ProcessarBoletos.FORMATO_DATA.format(dataPag);
        }
        return dtPag;
    }
}
