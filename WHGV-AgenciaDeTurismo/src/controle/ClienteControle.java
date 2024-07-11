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
import modelo.Cliente;

/**
 *
 * @author aluno
 */
public class ClienteControle {
    
     public static void main(String[] args) {
        ClienteControle teste = new ClienteControle();
        ArrayList<Cliente> cli = teste.consultarCliente();
        teste.imprimirCliente(cli);
    }

    public Cliente consultarClienteCodigo(int cod_cliente) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        Cliente cli = new Cliente();
        try {
            String consulta = "select * from cliente where cod_cliente = " + cod_cliente;

            Statement stm = conn.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);

            while (resultado.next()) {
                cli.setCod_cliente(resultado.getInt("cod_cliente"));
                cli.setNome_cliente(resultado.getString("nome_cliente"));
                cli.setSexo(resultado.getString("sexo"));
                cli.setCadastro(resultado.getString("cadastro"));
                cli.setRg(resultado.getString("rg"));
                cli.setData_nasc(resultado.getString("data_nasc"));
                cli.setCpf(resultado.getString("cpf"));
                
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível realizar a consulta dos clientes.");
        }
        return cli;
    }
     public ArrayList<Cliente> consultarCliente() {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        ArrayList<Cliente> geral = new ArrayList<>();
        try {
            String consulta = "select * from cliente order by nome_cliente";
            Statement stm = conn.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);

            while (resultado.next()) {
                Cliente cli = new Cliente();

                cli.setCod_cliente(resultado.getInt("cod_cliente"));
                cli.setNome_cliente(resultado.getString("nome_cliente"));
                cli.setSexo(resultado.getString("sexo"));
                cli.setCadastro(resultado.getString("cadastro"));
                cli.setRg(resultado.getString("rg"));
                cli.setData_nasc(resultado.getString("data_nasc"));
                cli.setCpf(resultado.getString("cpf"));
                geral.add(cli);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return geral;
    }

    public String inserirCliente (Cliente cliente) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        String resultado = "";
        try {
            String consulta = "INSERT INTO cliente (cod_cliente, nome_cliente, sexo, cadastro, rg, data_nasc, cpf) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setString(1, cliente.getNome_cliente());
            stm.setString(2, cliente.getSexo());
            stm.setString(3, cliente.getCadastro());
            stm.setString(4, cliente.getRg());
            stm.setString(5, cliente.getData_nasc());
            stm.setString(6, cliente.getCpf());
            

            stm.executeUpdate();
            resultado = "Dados inseridos com sucesso";
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            resultado = "erro ao inserir dados";
        }
        return resultado;
    }

    public void imprimirCliente(Cliente cliente) {
        System.out.println("Codigo: " + cliente.getCod_cliente() + "\n" +
                "Nome: " + cliente.getNome_cliente() + "\n" +
                "Sexo: " + cliente.getSexo() + "\n" +
                "Cadastro: " + cliente.getCadastro() + "\n" +
                "RG: " + cliente.getRg() + "\n" +
                "Data de nascimento: " + cliente.getData_nasc() + "\n" +
                "CPF: " + cliente.getCpf() + "\n");
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
            stm.setString(1, cliente.getNome_cliente());
            stm.setString(2, cliente.getSexo());
            stm.setString(3, cliente.getCadastro());
            stm.setString(4, cliente.getRg());
            stm.setString(5, cliente.getData_nasc());
            stm.setString(6, cliente.getCpf());
            

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

