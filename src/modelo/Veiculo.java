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
public class Veiculo {
   
    private Integer cod_veic;
    private String tipo_veiculo;
    private Integer preco_loc;
    private Cidade cod_cidade;

    public Veiculo(){}
    
    public Veiculo(Integer cod_veic, String tipo_veiculo, Integer preco_loc, Cidade cod_cidade) {
        this.cod_veic = cod_veic;
        this.tipo_veiculo = tipo_veiculo;
        this.preco_loc = preco_loc;
        this.cod_cidade = cod_cidade;
    }

    public Integer getCod_veic() {
        return cod_veic;
    }

    public void setCod_veic(Integer cod_veic) {
        this.cod_veic = cod_veic;
    }

    public String getTipo_veiculo() {
        return tipo_veiculo;
    }

    public void setTipo_veiculo(String tipo_veiculo) {
        this.tipo_veiculo = tipo_veiculo;
    }

    public Integer getPreco_loc() {
        return preco_loc;
    }

    public void setPreco_loc(Integer preco_loc) {
        this.preco_loc = preco_loc;
    }

    public Cidade getCod_cidade() {
        return cod_cidade;
    }

    public void setCod_cidade(Cidade cod_cidade) {
        this.cod_cidade = cod_cidade;
    }

}
