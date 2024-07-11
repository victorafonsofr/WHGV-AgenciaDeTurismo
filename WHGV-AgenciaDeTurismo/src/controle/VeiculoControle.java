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
import modelo.Veiculo;

/**
 *
 * @author aluno
 */
public class VeiculoControle {
     private Veiculo veic = new Veiculo(); 
     private CidadeControle cidcontrole = new CidadeControle(); 
             
     public static void main(String[] args) {
        VeiculoControle teste = new VeiculoControle();
        ArrayList<Veiculo> veic = teste.consultarVeiculo();
        teste.imprimirVeiculo(veic);
    }

    public Veiculo consultarVeiculoCodigo(int cod_veic) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        Veiculo veic = new Veiculo();
        try {
            String consulta = "select * from veiculo where cod_veic = " + cod_veic;

            Statement stm = conn.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);
           
            while (resultado.next()) {
                veic.setCod_veic(resultado.getInt("cod_veic"));
                veic.setTipo_veiculo(resultado.getString("tipo_veiculo"));
                veic.setPreco_loc(resultado.getInt("preco_loc"));
                veic.setCod_cidade(cidcontrole.consultarCidadeCodigo(resultado.getInt("cod_cidade")));  
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível realizar a consulta dos veiculos.");
        }
        return veic;
    }
     public ArrayList<Veiculo> consultarVeiculo() {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        ArrayList<Veiculo> geral = new ArrayList<>();
        try {
            String consulta = "select * from veiculo order by tipo_veiculo";
            Statement stm = conn.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);

            while (resultado.next()) {
                Veiculo veic = new Veiculo();

                veic.setCod_veic(resultado.getInt("cod_veic"));
                veic.setTipo_veiculo(resultado.getString("tipo_veiculo"));
                veic.setPreco_loc(resultado.getInt("preco_loc"));
                veic.setCod_cidade(cidcontrole.consultarCidadeCodigo(resultado.getInt("cod_cidade"))); 
                geral.add(veic);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return geral;
    }

    public String inserirVeiculo (Veiculo veiculo) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        String resultado = "";
        try {
            String consulta = "INSERT INTO veiculo (cod_veic,tipo_veiculo, preoo_loc,cod_cidade) VALUES (?,?,?,?)";

            PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setString(1, veiculo.getTipo_veiculo());
            stm.setInt(2, veiculo.getPreco_loc());
            stm.setInt(3, veiculo.getCod_cidade().getCod_cidade());

            stm.executeUpdate();
            resultado = "Dados inseridos com sucesso";
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            resultado = "erro ao inserir dados";
        }
        return resultado;
    }

    public void imprimirVeiculo(Veiculo veiculo) {
        System.out.println("Codigo: " + veiculo.getCod_veic() + "\n" +
                "Tipo do veiculo: " + veiculo.getTipo_veiculo() + "\n" +
                "Preço da locação: " + veiculo.getPreco_loc() + "\n" +
                "Codigo da cidade: " + veiculo.getCod_cidade().getCod_cidade()+ "\n");
    }

    public void imprimirVeiculo(ArrayList<Veiculo> veiculo) {
        for (Veiculo veic : veiculo) {
           System.out.println("Codigo: " + veic.getCod_veic() + "\n" +
                "Tipo do veiculo: " + veic.getTipo_veiculo() + "\n" +
                "Preço da locação: " + veic.getPreco_loc() + "\n" +
                "Codigo da cidade: " + veic.getCod_cidade().getCod_cidade()+ "\n");
        }
    }

    public String alterarVeiculo(Veiculo veiculo) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        String resultado = "";
        try {
            String consulta = "UPDATE veiculo SET "
                    + "tipo_veiculo = ?,preco_loc =, cod_cidade ? WHERE "
                    + "cod_veic = ?";
            
             PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setString(1, veiculo.getTipo_veiculo());
            stm.setInt(2, veiculo.getPreco_loc());
            stm.setInt(3, veiculo.getCod_cidade().getCod_cidade());

            stm.executeUpdate();
            resultado = "Dados atualizados com sucesso";
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            resultado = "erro ao atualizar a tabela 'hospedagem' ";
        }
        return resultado;
    }

    public String removerVeiculo(int veiculo) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        String resultado = "";
        try {
            String consulta = "DELETE FROM veiculo WHERE cod_veic = ?";

            PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setInt(1, veiculo);

            stm.executeUpdate();
            resultado = "Veiculo removido com sucesso";
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            resultado = "erro";
        }
        return resultado;
    }

    public VeiculoControle() {
    }

    
}
