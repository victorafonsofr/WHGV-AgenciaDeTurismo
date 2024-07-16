/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author aluno
 */
public class Oferta {
    private Integer cod_oferta;
    private LocalDate tempo_inicial;
    private LocalDate tempo_final;
    private Integer percentual_desconto;
    private Pacote cod_pacote;

    public Oferta(){}
    
    public Oferta(Integer cod_oferta, LocalDate tempo_inicial, LocalDate tempo_final, Integer percentual_desconto, Pacote cod_pacote) {
        this.cod_oferta = cod_oferta;
        this.tempo_inicial = tempo_inicial;
        this.tempo_final = tempo_final;
        this.percentual_desconto = percentual_desconto;
        this.cod_pacote = cod_pacote;
    }

    public Integer getCod_oferta() {
        return cod_oferta;
    }

    public void setCod_oferta(Integer cod_oferta) {
        this.cod_oferta = cod_oferta;
    }

    public LocalDate getTempo_inicial() {
        return tempo_inicial;
    }

    public void setTempo_inicial(LocalDate tempo_inicial) {
        this.tempo_inicial = tempo_inicial;
    }

    public LocalDate getTempo_final() {
        return tempo_final;
    }

    public void setTempo_final(LocalDate tempo_final) {
        this.tempo_final = tempo_final;
    }

    public Integer getPercentual_desconto() {
        return percentual_desconto;
    }

    public void setPercentual_desconto(Integer percentual_desconto) {
        this.percentual_desconto = percentual_desconto;
    }

    public Pacote getCod_pacote() {
        return cod_pacote;
    }

    public void setCod_pacote(Pacote cod_pacote) {
        this.cod_pacote = cod_pacote;
    }
    
}
