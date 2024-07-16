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
/**
 *
 * @author aluno
 */
public class CidadeControle {
    public static void main(String[] args) {
        CidadeControle teste = new CidadeControle();
        ArrayList<Cidade> cid = teste.consultarCidade();
        teste.imprimirCidade(cid);
    }

    public Cidade consultarCidadeCodigo(int cod_cidade) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        Cidade cid = new Cidade();
        try {
            String consulta = "select * from cidade where cod_cidade = " + cod_cidade;

            Statement stm = conn.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);

            while (resultado.next()) {
              
                cid.setCod_cidade(resultado.getInt("cod_cidade"));
                cid.setNome_cid(resultado.getString("nome_cid"));
                cid.setEstado(resultado.getString("estado"));
                cid.setPais(resultado.getString("pais"));
              
                
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível realizar a consulta das cidades.");
        }
        return cid;
    }
     public ArrayList<Cidade> consultarCidade() {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        ArrayList<Cidade> geral = new ArrayList<>();
        try {
            String consulta = "select * from cidade order by nome_cid";
            Statement stm = conn.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);

            while (resultado.next()) {
                Cidade cid = new Cidade();

                cid.setCod_cidade(resultado.getInt("cod_cidade"));
                cid.setNome_cid(resultado.getString("nome_cid"));
                cid.setEstado(resultado.getString("estado"));
                cid.setPais(resultado.getString("pais"));
              
                geral.add(cid);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return geral;
    }

    public String inserirCidade(Cidade cidade) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        String resultado = "";
        try {
            String consulta = "INSERT INTO cidade (cod_cidade, nome_cid, estado, pais) VALUES (?,?,?,?)";

            PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setString(1, cidade.getNome_cid());
            stm.setString(2, cidade.getEstado());
            stm.setString(3, cidade.getPais());
            

            stm.executeUpdate();
            resultado = "Dados inseridos com sucesso";
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            resultado = "erro ao inserir dados";
        }
        return resultado;
    }

    public void imprimirCidade(Cidade cidade) {
        System.out.println("Codigo: " + cidade.getCod_cidade() + "\n" +
                "Nome : " + cidade.getNome_cid() + "\n" +
                "Estado : " + cidade.getEstado() + "\n" +
                "País : " + cidade.getPais() + "\n");
    }

    public void imprimirCidade(ArrayList<Cidade> cidade) {
        for (Cidade cid : cidade) {
             System.out.println("Codigo: " + cid.getCod_cidade() + "\n" +
                "Nome : " + cid.getNome_cid() + "\n" +
                "Estado : " + cid.getEstado() + "\n" +
                "País : " + cid.getPais() + "\n");
        }
    }

    public String alterarCidade(Cidade cidade) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        String resultado = "";
        try {
            String consulta = "UPDATE cidade SET "
                    + "nome_cid = ?, estado = ?, pais = ? WHERE "
                    + "cod_cidade = ?";
            
            PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setString(1, cidade.getNome_cid());
            stm.setString(2, cidade.getEstado());
            stm.setString(3, cidade.getPais());
            
            stm.executeUpdate();
            resultado = "Dados atualizados com sucesso";
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            resultado = "erro ao atualizar a tabela 'cidade' ";
        }
        return resultado;
    }

    public String removerCidade(int cidade) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        String resultado = "";
        try {
            String consulta = "DELETE FROM cidade WHERE cod_cidade = ?";

            PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setInt(1, cidade);

            stm.executeUpdate();
            resultado = "Cidade removida com sucesso";
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            resultado = "erro";
        }
        return resultado;
    }

    public CidadeControle() {
    }

}
