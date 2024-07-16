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
public class Cidade {
    
    private Integer cod_cidade;
    private String nome_cid;
    private String estado;
    private String pais;
    
    

    public Cidade(Integer cod_cidade, String nome_cid, String estado, String pais) {
        this.cod_cidade = cod_cidade;
        this.nome_cid = nome_cid;
        this.estado = estado;
        this.pais = pais;
    }
    
    public Cidade(){}

    public Integer getCod_cidade() {
        return cod_cidade;
    }

    public void setCod_cidade(Integer cod_cidade) {
        this.cod_cidade = cod_cidade;
    }

    public String getNome_cid() {
        return nome_cid;
    }

    public void setNome_cid(String nome_cid) {
        this.nome_cid = nome_cid;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
}
