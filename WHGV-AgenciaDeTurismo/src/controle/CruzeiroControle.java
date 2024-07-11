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
import modelo.Cruzeiro;

public class CruzeiroControle {

    public static void main(String[] args) {
        CruzeiroControle teste = new CruzeiroControle();
        ArrayList<Cruzeiro> cruz = teste.consultarCruzeiro();
        teste.imprimirCruzeiro(cruz);
    }

    public Cruzeiro consultarCruzeiroCodigo(int cod_cruz) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        Cruzeiro cruz = new Cruzeiro();
        try {
            String consulta = "select * from cruzeiro where cod_cruz = " + cod_cruz;

            Statement stm = conn.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);

            while (resultado.next()) {
                cruz.setCodCruz(resultado.getInt("cod_cruz"));
                cruz.setNavio(resultado.getString("navio"));
                cruz.setCapacidade(resultado.getInt("capacidade"));
                cruz.setPercurso(resultado.getString("percurso"));
                cruz.setClasseNavio(resultado.getInt("classe_navio"));
                cruz.setDataPartida(resultado.getString("data_partida"));
                cruz.setDataFim(resultado.getString("data_fim"));
                cruz.setPreco(resultado.getInt("preco_cruz"));
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível realizar a consulta dos cruzeiros.");
        }
        return cruz;
    }

    public ArrayList<Cruzeiro> consultarCruzeiro() {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        ArrayList<Cruzeiro> geral = new ArrayList<>();
        try {
            String consulta = "select * from cruzeiro order by navio";
            Statement stm = conn.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);

            while (resultado.next()) {
                Cruzeiro cruz = new Cruzeiro();

                cruz.setCodCruz(resultado.getInt("cod_cruz"));
                cruz.setNavio(resultado.getString("navio"));
                cruz.setCapacidade(resultado.getInt("capacidade"));
                cruz.setPercurso(resultado.getString("percurso"));
                cruz.setClasseNavio(resultado.getInt("classe_navio"));
                cruz.setDataPartida(resultado.getString("data_partida"));
                cruz.setDataFim(resultado.getString("data_fim"));
                cruz.setPreco(resultado.getInt("preco_cruz"));
                geral.add(cruz);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CruzeiroControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return geral;
    }

    public String inserirCruzeiro(Cruzeiro cruzeiro) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        String resultado = "";
        try {
            String consulta = "INSERT INTO cruzeiro (cod_cruz, navio, capacidade, percurso, classe_navio, data_partida, data_fim, preco_cruz) VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setString(1, cruzeiro.getNavio());
            stm.setInt(2, cruzeiro.getCapacidade());
            stm.setString(3, cruzeiro.getPercurso());
            stm.setInt(4, cruzeiro.getClasseNavio());
            stm.setString(5, cruzeiro.getDataPartida());
            stm.setString(6, cruzeiro.getDataFim());
            stm.setInt(7, cruzeiro.getPreco());

            stm.executeUpdate();
            resultado = "Dados inseridos com sucesso";
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            resultado = "erro ao inserir dados";
        }
        return resultado;
    }

    public void imprimirCruzeiro(Cruzeiro cruzeiro) {
        System.out.println("Codigo: " + cruzeiro.getCodCruz() + "\n" +
                "Navio: " + cruzeiro.getNavio() + "\n" +
                "Capacidade: " + cruzeiro.getCapacidade() + " pessoas" + "\n" +
                "Percurso: " + cruzeiro.getPercurso() + "\n" +
                "Classe do navio: " + cruzeiro.getClasseNavio() + "a" + "\n" +
                "Data de partida do navio: " + cruzeiro.getDataPartida() + "\n" +
                "Data de fim de viagem: " + cruzeiro.getDataFim() + "\n" +
                "Preco do cruzeiro: " + cruzeiro.getPreco() + "\n");
    }

    public void imprimirCruzeiro(ArrayList<Cruzeiro> cruzeiro) {
        for (Cruzeiro cruz : cruzeiro) {
            System.out.println("Codigo: " + cruz.getCodCruz() + "\n" +
                    "Navio: " + cruz.getNavio() + "\n" +
                    "Capacidade: " + cruz.getCapacidade() + "\n" +
                    "Percurso: " + cruz.getPercurso() + "\n" +
                    "Classe do navio: " + cruz.getClasseNavio() + "\n" +
                    "Data de partida do navio: " + cruz.getDataPartida() + "\n" +
                    "Data de fim de viagem: " + cruz.getDataFim() + "\n" +
                    "Preco do cruzeiro" + cruz.getPreco() + "\n");
        }
    }

    public String alterarCruzeiro(Cruzeiro cruzeiro) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        String resultado = "";
        try {
            String consulta = "UPDATE cruzeiro SET "
                    + "navio = ?, capacidade = ?, percurso = ?, classe_navio = ?, "
                    + "data_partida = ?, data_fim = ?, preco_cruz = ? WHERE "
                    + "cod_cruz = ?";
            PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setString(1, cruzeiro.getNavio());
            stm.setInt(2, cruzeiro.getCapacidade());
            stm.setString(3, cruzeiro.getPercurso());
            stm.setInt(4, cruzeiro.getClasseNavio());
            stm.setString(5, cruzeiro.getDataPartida());
            stm.setString(6, cruzeiro.getDataFim());
            stm.setInt(7, cruzeiro.getPreco());

            stm.executeUpdate();
            resultado = "Dados atualizados com sucesso";
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            resultado = "erro ao atualizar a tabela 'cruzeiro' ";
        }
        return resultado;
    }

    public String removerCruzeiro(int cruzeiro) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        String resultado = "";
        try {
            String consulta = "DELETE FROM clientes WHERE codCruz = ?";

            PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setInt(1, cruzeiro);

            stm.executeUpdate();
            resultado = "Cruzeiro removido com sucesso";
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            resultado = "erro";
        }
        return resultado;
    }

    public CruzeiroControle() {
    }

}
