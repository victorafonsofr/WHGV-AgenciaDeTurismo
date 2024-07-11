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
public class Hospedagem {
 
    private Integer cod_hospedagem;
    private String nome_hotel;
    private String CEP;
    private Integer preco_hosp;
    private Integer quarto;
    private Integer avaliacao;
    private String tipo_hospedagem;
    private Cidade cod_cidade;

    public Hospedagem(Integer cod_hospedagem, String nome_hotel, String CEP, Integer preco_hosp, Integer quarto, Integer avaliacao, String tipo_hospedagem, Cidade cod_cidade) {
        this.cod_hospedagem = cod_hospedagem;
        this.nome_hotel = nome_hotel;
        this.CEP = CEP;
        this.preco_hosp = preco_hosp;
        this.quarto = quarto;
        this.avaliacao = avaliacao;
        this.tipo_hospedagem = tipo_hospedagem;
        this.cod_cidade = cod_cidade;
    }
    public Hospedagem(){}

    public Integer getCod_hospedagem() {
        return cod_hospedagem;
    }

    public void setCod_hospedagem(Integer cod_hospedagem) {
        this.cod_hospedagem = cod_hospedagem;
    }

    public String getNome_hotel() {
        return nome_hotel;
    }

    public void setNome_hotel(String nome_hotel) {
        this.nome_hotel = nome_hotel;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public Integer getPreco_hosp() {
        return preco_hosp;
    }

    public void setPreco_hosp(Integer preco_hosp) {
        this.preco_hosp = preco_hosp;
    }

    public Integer getQuarto() {
        return quarto;
    }

    public void setQuarto(Integer quarto) {
        this.quarto = quarto;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getTipo_hospedagem() {
        return tipo_hospedagem;
    }

    public void setTipo_hospedagem(String tipo_hospedagem) {
        this.tipo_hospedagem = tipo_hospedagem;
    }

    public Cidade getCod_cidade() {
        return cod_cidade;
    }

    public void setCod_cidade(Cidade cod_cidade) {
        this.cod_cidade = cod_cidade;
    }
    
}
