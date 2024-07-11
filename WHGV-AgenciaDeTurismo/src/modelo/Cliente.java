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
public class Cliente {
    
    private Integer cod_cliente;
    private String nome_cliente;
    private String sexo;
    private String cadastro;
    private String rg;
    private String data_nasc;
    private String cpf;
    

    public Cliente(Integer cod_cliente, String nome_cliente, String sexo, String cadastro, String rg, String data_nasc, String cpf) {
        this.cod_cliente = cod_cliente;
        this.nome_cliente = nome_cliente;
        this.sexo = sexo;
        this.cadastro = cadastro;
        this.rg = rg;
        this.data_nasc = data_nasc;
        this.cpf = cpf;
    }
    
    public Cliente(){}

    public Integer getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(Integer cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCadastro() {
        return cadastro;
    }

    public void setCadastro(String cadastro) {
        this.cadastro = cadastro;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
