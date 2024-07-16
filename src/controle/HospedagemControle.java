/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.*;
import java.util.ArrayList;
import conexao.ConexaoMySQL;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cidade;
import modelo.Hospedagem;

/**
 *
 * @author aluno
 */
public class HospedagemControle {
    
     private Cidade cid = new Cidade(); 
     private CidadeControle cidcontrole = new CidadeControle(); 
             
     public static void main(String[] args) {
        HospedagemControle teste = new HospedagemControle();
        ArrayList<Hospedagem> hosp = teste.consultarHospedagem();
        teste.imprimirHospedagem(hosp);
    }

    public Hospedagem consultarHospedagemCodigo(int cod_hospedagem) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        Hospedagem hosp = new Hospedagem();
        try {
            String consulta = "select * from hospedagem where cod_hospedagem = " + cod_hospedagem;

            Statement stm = conn.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);
           
            while (resultado.next()) {
                hosp.setCod_hospedagem(resultado.getInt("cod_hospedagem"));
                hosp.setNome_hotel(resultado.getString("nome_hotel"));
                hosp.setCEP(resultado.getString("CEP"));
                hosp.setPreco_hosp(resultado.getInt("preco_hosp"));
                hosp.setQuarto(resultado.getInt("quarto"));
                hosp.setAvaliacao(resultado.getInt("avaliacao"));
                hosp.setTipo_hospedagem(resultado.getString("tipo_hospedagem"));
                hosp.setCod_cidade(cidcontrole.consultarCidadeCodigo(resultado.getInt("cod_cidade")));  
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível realizar a consulta das hospedagens.");
        }
        return hosp;
    }
     public ArrayList<Hospedagem> consultarHospedagem() {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        ArrayList<Hospedagem> geral = new ArrayList<>();
        try {
            String consulta = "select * from hospedagem order by nome_hotel";
            Statement stm = conn.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);

            while (resultado.next()) {
                Hospedagem hosp = new Hospedagem();

                hosp.setCod_hospedagem(resultado.getInt("cod_hospedagem"));
                hosp.setNome_hotel(resultado.getString("nome_hotel"));
                hosp.setCEP(resultado.getString("CEP"));
                hosp.setPreco_hosp(resultado.getInt("preco_hosp"));
                hosp.setQuarto(resultado.getInt("quarto"));
                hosp.setAvaliacao(resultado.getInt("avaliacao"));
                hosp.setTipo_hospedagem(resultado.getString("tipo_hospedagem"));
                hosp.setCod_cidade(cidcontrole.consultarCidadeCodigo(resultado.getInt("cod_cidade")));  
                geral.add(hosp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HospedagemControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return geral;
    }

    public String inserirHospedagem (Hospedagem hospedagem) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        String resultado = "";
        try {
            String consulta = "INSERT INTO hospedagem (cod_hospedagem, nome_hotel, CEP, preco_hosp, quarto, avaliacao, tipo_hospedagem, cod_cidade) VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setString(1, hospedagem.getNome_hotel());
            stm.setString(2, hospedagem.getCEP());
            stm.setInt(3, hospedagem.getPreco_hosp());
            stm.setInt(4, hospedagem.getQuarto());
            stm.setInt(5, hospedagem.getAvaliacao());
            stm.setString(6, hospedagem.getTipo_hospedagem());
            stm.setInt(7, hospedagem.getCod_cidade().getCod_cidade());

            stm.executeUpdate();
            resultado = "Dados inseridos com sucesso";
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            resultado = "erro ao inserir dados";
        }
        return resultado;
    }

    public void imprimirHospedagem(Hospedagem hospedagem) {
        System.out.println("Codigo: " + hospedagem.getCod_hospedagem() + "\n" +
                "Nome hotel: " + hospedagem.getNome_hotel() + "\n" +
                "CEP: " + hospedagem.getCEP() + "\n" +
                "Preço da hospedagem: " + hospedagem.getPreco_hosp() + "\n" +
                "Quarto: "+ hospedagem.getQuarto()+ "\n" +
                "Avaliação: " + hospedagem.getAvaliacao() + "\n" +
                "Tipo de hospedagem: " + hospedagem.getTipo_hospedagem() + "\n" +
                "Codigo da cidade: " + hospedagem.getCod_cidade().getCod_cidade()+ "\n");
    }

    public void imprimirHospedagem(ArrayList<Hospedagem> hospedagem) {
        for (Hospedagem hosp : hospedagem) {
            System.out.println("Codigo: " + hosp.getCod_hospedagem() + "\n" +
                "Nome hotel: " + hosp.getNome_hotel() + "\n" +
                "CEP: " + hosp.getCEP() + "\n" +
                "Preço da hospedagem: " + hosp.getPreco_hosp() + "\n" +
                "Quarto: "+ hosp.getQuarto()+ "\n" +
                "Avaliação: " + hosp.getAvaliacao() + "\n" +
                "Tipo de hospedagem: " + hosp.getTipo_hospedagem() + "\n" +
                "Codigo da cidade: " + hosp.getCod_cidade().getCod_cidade()+ "\n");
        }
    }

    public String alterarHospedagem(Hospedagem hospedagem) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        String resultado = "";
        try {
            String consulta = "UPDATE hospedagem SET "
                    + "nome_hotel = ?, CEP = ?,preco_hosp  = ?, quarto = ?, "
                    + "avaliacao = ?, tipo_hospedagem = ?, cod_cidade = ? WHERE "
                    + "cod_hospedagem = ?";
            
            PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setString(1, hospedagem.getNome_hotel());
            stm.setString(2, hospedagem.getCEP());
            stm.setInt(3, hospedagem.getPreco_hosp());
            stm.setInt(4, hospedagem.getQuarto());
            stm.setInt(5, hospedagem.getAvaliacao());
            stm.setString(6, hospedagem.getTipo_hospedagem());
            stm.setInt(7, hospedagem.getCod_cidade().getCod_cidade());

            stm.executeUpdate();
            resultado = "Dados atualizados com sucesso";
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            resultado = "erro ao atualizar a tabela 'hospedagem' ";
        }
        return resultado;
    }

    public String removerHospedagem(int hospedagem) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        String resultado = "";
        try {
            String consulta = "DELETE FROM hospedagem WHERE cod_hospedagem = ?";

            PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setInt(1, hospedagem);

            stm.executeUpdate();
            resultado = "Hospedagem removida com sucesso";
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            resultado = "erro";
        }
        return resultado;
    }

    public HospedagemControle() {
    }

    
}
