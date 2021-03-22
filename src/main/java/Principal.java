
import com.mycompany.retorno.boleto.strategy.e.funcional.ProcessarBoletos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wellikson
 */
public class Principal {
    public static void main(String[] args) {
        ProcessarBoletos processador = new ProcessarBoletos(ProcessarBoletos::lerBradesco);
        String nomeArquivo = "bradesco-1.csv";
        processador.processar(nomeArquivo);
    }
}
