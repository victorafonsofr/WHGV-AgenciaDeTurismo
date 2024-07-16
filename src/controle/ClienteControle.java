/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.*;
import java.util.ArrayList;
import conexao.ConexaoMySQL;
import java.time.LocalDate;
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
    
   /* 
    Cliente novoCliente = new Cliente();
    novoCliente.setNome_cliente("Fulano bill");
    novoCliente.setSexo('M');
    novoCliente.setCadastro("fulanobill@gmail.com");
    novoCliente.setRg("12345678");
    novoCliente.setData_nasc(LocalDate.of(1990, 5, 15)); 
    novoCliente.setCpf("12345678900");
    
    String resultado = teste.inserirCliente(novoCliente);
    System.out.println(resultado);
    
*/}

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
                cli.setSexo(resultado.getString("sexo").charAt(0));
                cli.setCadastro(resultado.getString("cadastro"));
                cli.setRg(resultado.getString("rg"));
                cli.setCpf(resultado.getString("cpf"));
                
                LocalDate dataNasc = resultado.getDate("data_nasc").toLocalDate();
                cli.setData_nasc(dataNasc);
                
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível realizar a consulta dos clientes.");
        }
        return cli;
    }
     public ArrayList<Cliente> consultarCliente() {
    ConexaoMySQL conexao = new ConexaoMySQL();
    Connection conn = null;
    ArrayList<Cliente> geral = new ArrayList<>();
    try {
        conn = conexao.conectar(); 
        if (conn == null) {
            System.out.println("Falha ao estabelecer conexão com o banco de dados");
            return geral;
        }
        
        String consulta = "select * from cliente order by cod_cliente";
        try (Statement stm = conn.createStatement();
             ResultSet resultado = stm.executeQuery(consulta)) {
            
            while (resultado.next()) {
                Cliente cli = new Cliente();

                cli.setCod_cliente(resultado.getInt("cod_cliente"));
                cli.setNome_cliente(resultado.getString("nome_cliente"));
                cli.setSexo(resultado.getString("sexo").charAt(0));
                cli.setCadastro(resultado.getString("cadastro"));
                cli.setRg(resultado.getString("rg"));
                cli.setCpf(resultado.getString("cpf"));
                geral.add(cli);
                
                LocalDate dataNasc = resultado.getDate("data_nasc").toLocalDate();
                cli.setData_nasc(dataNasc);
                
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    return geral;
}

  public String inserirCliente(Cliente cliente) {
    ConexaoMySQL conexao = new ConexaoMySQL();
    Connection conn = conexao.conectar();
    String resultado = "";
    
    if (conn == null) {
        return "Erro na conexão com o banco de dados";
    }
    
    try {
        String consulta = "INSERT INTO cliente (nome_cliente, sexo, cadastro, rg, data_nasc, cpf) VALUES (?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stm = conn.prepareStatement(consulta);
        stm.setString(1, cliente.getNome_cliente());
        stm.setString(2, String.valueOf(cliente.getSexo())); 
        stm.setString(3, cliente.getCadastro());
        stm.setString(4, cliente.getRg());
        stm.setDate(5, java.sql.Date.valueOf(cliente.getData_nasc())); 
        stm.setString(6, cliente.getCpf());
        
        int linhasAfetadas = stm.executeUpdate();
        
        if (linhasAfetadas > 0) {
            resultado = "Dados inseridos com sucesso";
        } else {
            resultado = "Nenhum dado foi inserido";
        }
    } catch (SQLException ex) {
        System.out.println(ex.getSQLState());
        resultado = "Erro ao inserir dados: " + ex.getMessage();
    } finally {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    if (conn == null) {
        return "Erro na conexão com o banco de dados";
    }
    
    try {
        String consulta = "UPDATE cliente SET "
                + "nome_cliente = ?, sexo = ?, cadastro = ?, rg = ?, "
                + "data_nasc = ?, cpf = ? WHERE "
                + "cod_cliente = ?";
        
        PreparedStatement stm = conn.prepareStatement(consulta);
        stm.setString(1, cliente.getNome_cliente());
        stm.setString(2, String.valueOf(cliente.getSexo())); 
        stm.setString(3, cliente.getCadastro());
        stm.setString(4, cliente.getRg());

        stm.setDate(5, java.sql.Date.valueOf(cliente.getData_nasc())); 
        
        stm.setString(6, cliente.getCpf());
        
        stm.setInt(7, cliente.getCod_cliente());
        
        int linhasAfetadas = stm.executeUpdate();
        
        if (linhasAfetadas > 0) {
            resultado = "Dados atualizados com sucesso";
        } else {
            resultado = "Nenhum dado foi atualizado";
        }
    } catch (SQLException ex) {
        System.out.println(ex.getSQLState());
        resultado = "Erro ao atualizar dados: " + ex.getMessage();
    } finally {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return resultado;
}


    public String removerCliente(int cod_cliente) {
    ConexaoMySQL conexao = new ConexaoMySQL();
    Connection conn = conexao.conectar();
    String resultado = "";
    
    if (conn == null) {
        return "Erro na conexão com o banco de dados";
    }
    
    try {
        String consulta = "DELETE FROM cliente WHERE cod_cliente = ?";
        
        PreparedStatement stm = conn.prepareStatement(consulta);
        stm.setInt(1, cod_cliente);
        
        int linhasAfetadas = stm.executeUpdate();
        
        if (linhasAfetadas > 0) {
            resultado = "Cliente removido com sucesso";
        } else {
            resultado = "Nenhum cliente foi removido";
        }
    } catch (SQLException ex) {
        System.out.println(ex.getSQLState());
        resultado = "Erro ao remover cliente: " + ex.getMessage();
    } finally {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return resultado;
}


    public ClienteControle() {
    }

}

