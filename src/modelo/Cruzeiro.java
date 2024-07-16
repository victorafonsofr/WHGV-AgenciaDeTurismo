/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * 
 */
public class Cruzeiro {

    public Integer getCodCruz() {
        return cod_cruz;
    }

    public void setCodCruz(Integer cod_cruz) {
        this.cod_cruz = cod_cruz;
    }

    public String getNavio() {
        return navio;
    }

    public void setNavio(String navio) {
        this.navio = navio;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getPercurso() {
        return percurso;
    }

    public void setPercurso(String percurso) {
        this.percurso = percurso;
    }

    public Integer getClasseNavio() {
        return classe_navio;
    }

    public void setClasseNavio(Integer classe_navio) {
        this.classe_navio = classe_navio;
    }

    public String getDataPartida() {
        return data_partida;
    }

    public void setDataPartida(String data_partida) {
        this.data_partida = data_partida;
    }

    public String getDataFim() {
        return data_fim;
    }

    public void setDataFim(String data_fim) {
        this.data_fim = data_fim;
    }

    private Integer cod_cruz;

    private String navio;

    private Integer capacidade;

    private String percurso;

    private Integer classe_navio;

    private String data_partida;

    private String data_fim;

    public Cruzeiro(Integer cod_cruz, String navio, Integer capacidade, String percurso, Integer classe_navio,
            String data_partida, String data_fim, Integer preco_cruz) {
        this.cod_cruz = cod_cruz;
        this.navio = navio;
        this.capacidade = capacidade;
        this.percurso = percurso;
        this.classe_navio = classe_navio;
        this.data_partida = data_partida;
        this.data_fim = data_fim;
        this.preco_cruz = preco_cruz;
    }
    public Cruzeiro(){}

    private Integer preco_cruz;

    public Integer getPreco() {
        return preco_cruz;
    }

    public void setPreco(Integer preco_cruz) {
        this.preco_cruz = preco_cruz;
    }
}
