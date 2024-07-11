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
import modelo.Pacote;

/**
 *
 * @author aluno
 */
public class PacoteControle {
    public static void main(String[] args) {
        PacoteControle teste = new PacoteControle();
        ArrayList<Pacote> pac = teste.consultarPacote();
        teste.imprimirPacote(pac);
    }

    public Pacote consultarPacoteCodigo(int cod_pacote) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        Pacote pac = new Pacote();
        try {
            String consulta = "select * from pacote where cod_pac = " + cod_pacote;

            Statement stm = conn.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);

            while (resultado.next()) {
                pac.setCod_pacote(resultado.getInt("cod_pacote"));
                pac.setDestino(resultado.getString("destino"));
                pac.setData_partida(resultado.getString("data_partida"));
                pac.setOrigem(resultado.getString("origem"));
                pac.setData_volta(resultado.getString("data_volta"));
                pac.setPreco_pac(resultado.getInt("preco_pac"));
                pac.setCompania_aerea(resultado.getString("compania_aerea"));
                
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível realizar a consulta dos pacotes.");
        }
        return pac;
    }
     public ArrayList<Pacote> consultarPacote() {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        ArrayList<Pacote> geral = new ArrayList<>();
        try {
            String consulta = "select * from pacote order by destino";
            Statement stm = conn.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);

            while (resultado.next()) {
                Pacote pac = new Pacote();

                pac.setCod_pacote(resultado.getInt("cod_pacote"));
                pac.setDestino(resultado.getString("destino"));
                pac.setData_partida(resultado.getString("data_partida"));
                pac.setOrigem(resultado.getString("origem"));
                pac.setData_volta(resultado.getString("data_volta"));
                pac.setPreco_pac(resultado.getInt("preco_pac"));
                pac.setCompania_aerea(resultado.getString("compania_aerea"));
                
                geral.add(pac);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacoteControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return geral;
    }

    public String inserirPacote (Pacote pacote) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        String resultado = "";
        try {
            String consulta = "INSERT INTO pacote (cod_pacote, destino, data_partida, origem, data_volta, preco_pac, compania_aerea) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setString(1, pacote.getDestino());
            stm.setString(2, pacote.getData_partida());
            stm.setString(3, pacote.getOrigem());
            stm.setString(4, pacote.getData_volta());
            stm.setInt(5, pacote.getPreco_pac());
            stm.setString(6, pacote.getCompania_aerea());
            

            stm.executeUpdate();
            resultado = "Dados inseridos com sucesso";
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            resultado = "erro ao inserir dados";
        }
        return resultado;
    }

    public void imprimirPacote(Pacote pacote) {
        System.out.println("Codigo: " + pacote.getCod_pacote() + "\n" +
                "Destino: " + pacote.getDestino() + "\n" +
                "Data de partida: " + pacote.getData_partida() + "\n" +
                "Origem: " + pacote.getOrigem()+ "\n" +
                "Data de volta: " + pacote.getData_volta()+ "\n" +
                "Preço do pacote: " + pacote.getPreco_pac() + "\n" +
                "Compania aerea" + pacote.getCpf() + "\n");
    }

    public void imprimirCliente(ArrayList<Cliente> cliente) {
        for (Cliente cli : cliente) {
            System.out.println("Codigo: " + cli.getCod_cliente() + "\n" +
                "Nome: " + cli.getNome_cliente() + "\n" +
                "Sexo: " + cli.getSexo() + "\n" +
                "Cadastro: " + cli.getCadastro() + "\n" +
                "RG: " + cli.getRg() + "\n" +
                "Data de nascimento: " + cli.getData_nasc() + "\n" +
                "CPF: " + cli.getCpf() + "\n");
        }
    }

    public String alterarCliente(Cliente cliente) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        String resultado = "";
        try {
            String consulta = "UPDATE cliente SET "
                    + "nome_cliente = ?, sexo = ?, cadastro = ?, rg = ?, "
                    + "data_nasc = ?, cpf = ? WHERE "
                    + "cod_cliente = ?";
            PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setString(1, pacote.getDestino());
            stm.setString(2, pacote.getData_partida());
            stm.setString(3, pacote.getOrigem());
            stm.setString(4, pacote.getData_volta());
            stm.setInt(5, pacote.getPreco_pac());
            stm.setString(6, pacote.getCompania_aerea());
            

            stm.executeUpdate();
            resultado = "Dados atualizados com sucesso";
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            resultado = "erro ao atualizar a tabela 'cliente' ";
        }
        return resultado;
    }

    public String removerCliente(int cliente) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        String resultado = "";
        try {
            String consulta = "DELETE FROM clientes WHERE cod_cliente = ?";

            PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setInt(1, cliente);

            stm.executeUpdate();
            resultado = "Cliente removido com sucesso";
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            resultado = "erro";
        }
        return resultado;
    }

    public ClienteControle() {
    }
}
