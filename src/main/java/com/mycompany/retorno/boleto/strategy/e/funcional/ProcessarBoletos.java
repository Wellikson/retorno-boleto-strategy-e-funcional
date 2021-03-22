package com.mycompany.retorno.boleto.strategy.e.funcional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author wellikson
 */
public class ProcessarBoletos {

    public static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter FORMATO_DATA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private Function<String, List<Boleto>> leituraRetorno;

    public ProcessarBoletos(Function<String, List<Boleto>> leituraRetorno) {
        this.leituraRetorno = leituraRetorno;
    }

    public static List<Boleto> lerBancoBrasil(String nomeArquivo) {
        try {
            BufferedReader reader
                    = Files.newBufferedReader(Paths.get(nomeArquivo));
            String line;
            List<Boleto> boletos = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] vetor = line.split(";");
                Boleto boleto = new Boleto();
                boleto.setId(Integer.parseInt(vetor[0]));
                boleto.setCodBanco(vetor[1]);

                boleto.setDataVenc(LocalDate.parse(vetor[2], FORMATO_DATA));
                boleto.setDataPag(LocalDate.parse(vetor[3], FORMATO_DATA).atTime(0, 0, 0));

                boleto.setCpfCliente(vetor[4]);
                boleto.setValor(Double.parseDouble(vetor[5]));
                boleto.setMulta(Double.parseDouble(vetor[6]));
                boleto.setJuros(Double.parseDouble(vetor[7]));

                boletos.add(boleto);
            }

            return boletos;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static List<Boleto> lerBradesco(String nomeArquivo) {
        try {
            BufferedReader reader
                    = Files.newBufferedReader(Paths.get(nomeArquivo));
            String line;
            List<Boleto> boletos = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] vetor = line.split(";");
                Boleto boleto = new Boleto();
                boleto.setId(Integer.parseInt(vetor[0]));
                boleto.setCodBanco(vetor[1]);
                boleto.setAgencia(vetor[2]);
                boleto.setContaBancaria(vetor[3]);

                boleto.setDataVenc(LocalDate.parse(vetor[4], FORMATO_DATA));
                boleto.setDataPag(LocalDateTime.parse(vetor[5], FORMATO_DATA_HORA));

                boleto.setCpfCliente(vetor[6]);
                boleto.setValor(Double.parseDouble(vetor[7]));
                boleto.setMulta(Double.parseDouble(vetor[8]));
                boleto.setJuros(Double.parseDouble(vetor[9]));

                boletos.add(boleto);
            }

            return boletos;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public final void processar(String nomeArquivo) {
        System.out.println("Boletos");
        System.out.println("----------------------------------------------------------------------------------");
        List<Boleto> boletos = leituraRetorno.apply(nomeArquivo);
        for (Boleto boleto : boletos) {
            System.out.println(boleto);
        }
    }
}
