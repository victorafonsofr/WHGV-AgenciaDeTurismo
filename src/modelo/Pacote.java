/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author aluno
 */
public class Pacote {
    private Integer cod_pacote;
    private String destino;
    private String data_partida;
    private String origem;
    private String data_volta;
    private Integer preco_pac;
    private String compania_aerea;
   
    public Pacote(){}
    
    public Integer getCod_pacote() {
        return cod_pacote;
    }

    public void setCod_pacote(Integer cod_pacote) {
        this.cod_pacote = cod_pacote;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getData_partida() {
        return data_partida;
    }

    public void setData_partida(String data_partida) {
        this.data_partida = data_partida;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getData_volta() {
        return data_volta;
    }

    public void setData_volta(String data_volta) {
        this.data_volta = data_volta;
    }

    public Integer getPreco_pac() {
        return preco_pac;
    }

    public void setPreco_pac(Integer preco_pac) {
        this.preco_pac = preco_pac;
    }

    public String getCompania_aerea() {
        return compania_aerea;
    }

    public void setCompania_aerea(String compania_aerea) {
        this.compania_aerea = compania_aerea;
    }

    public Pacote(Integer cod_pacote, String destino, String data_partida, String origem, String data_volta, Integer preco_pac, String compania_aerea) {
        this.cod_pacote = cod_pacote;
        this.destino = destino;
        this.data_partida = data_partida;
        this.origem = origem;
        this.data_volta = data_volta;
        this.preco_pac = preco_pac;
        this.compania_aerea = compania_aerea;
    }

}
