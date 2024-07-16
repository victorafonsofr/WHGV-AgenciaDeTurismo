package controle;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import conexao.ConexaoMySQL;
import java.time.LocalDate;
import modelo.Oferta;
import controle.PacoteControle;

public class OfertaControle {
    public static void main(String[] args) {
        
    }
    PacoteControle pacControle = new PacoteControle();
    
    public Oferta consultarOfertaCodigo(int cod_oferta) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        Oferta oferta = new Oferta();
        try {
            String consulta = "SELECT * FROM Ofertas WHERE cod_oferta = " + cod_oferta;

            Statement stm = conn.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);

            while (resultado.next()) {
                oferta.setCod_oferta(resultado.getInt("cod_oferta"));
                oferta.setPercentual_desconto(resultado.getInt("percentual_desconto"));
                LocalDate tempoInicial = resultado.getDate("tempo_inicial").toLocalDate();
                oferta.setTempo_inicial(tempoInicial);
                
                LocalDate tempoFinal = resultado.getDate("tempo_final").toLocalDate();
                oferta.setTempo_inicial(tempoFinal);
               
                oferta.setCod_pacote(pacControle.consultarPacoteCodigo(resultado.getInt("cod_pacote")));
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível realizar a consulta das ofertas.");
            ex.printStackTrace();
        }
        return oferta;
    }

    public ArrayList<Oferta> consultarOfertas() {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        ArrayList<Oferta> listaOfertas = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM Ofertas ORDER BY cod_oferta";
            Statement stm = conn.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);

            while (resultado.next()) {
                Oferta oferta = new Oferta();

                oferta.setCod_oferta(resultado.getInt("cod_oferta"));
                oferta.setPercentual_desconto(resultado.getInt("percentual_desconto"));
                LocalDate tempoInicial = resultado.getDate("tempo_inicial").toLocalDate();
                oferta.setTempo_inicial(tempoInicial);
                
                LocalDate tempoFinal = resultado.getDate("tempo_final").toLocalDate();
                oferta.setTempo_inicial(tempoFinal);
               
                oferta.setCod_pacote(pacControle.consultarPacoteCodigo(resultado.getInt("cod_pacote")));

                listaOfertas.add(oferta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OfertaControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaOfertas;
    }

    public String inserirOferta(Oferta oferta) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        String resultado = "";
        try {
            String consulta = "INSERT INTO Ofertas (cod_oferta, tempo_inicial, tempo_final, percentual_desconto, cod_pacote) VALUES (?,?,?,?,?)";

            PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setInt(1, oferta.getCod_oferta());
            stm.setObject(2, oferta.getTempo_inicial());
            stm.setObject(3, oferta.getTempo_final());
            stm.setInt(4, oferta.getPercentual_desconto());
            stm.setInt(5, oferta.getCod_pacote().getCod_pacote());

            stm.executeUpdate();
            resultado = "Dados inseridos com sucesso";
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            resultado = "Erro ao inserir dados";
        }
        return resultado;
    }

    public void imprimirOferta(Oferta oferta) {
        System.out.println("Código da Oferta: " + oferta.getCod_oferta());
        System.out.println("Tempo Inicial: " + oferta.getTempo_inicial());
        System.out.println("Tempo Final: " + oferta.getTempo_final());
        System.out.println("Percentual de Desconto: " + oferta.getPercentual_desconto() + "%");
        System.out.println("Código do Pacote Associado: " + oferta.getCod_pacote());
    }

    public void imprimirOfertas(ArrayList<Oferta> ofertas) {
        for (Oferta oferta : ofertas) {
            imprimirOferta(oferta);
            System.out.println(); 
        }
    }

    public String alterarOferta(Oferta oferta) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        String resultado = "";
        try {
            String consulta = "UPDATE Ofertas SET "
                    + "tempo_inicial = ?, tempo_final = ?, percentual_desconto = ?, cod_pacote = ? "
                    + "WHERE cod_oferta = ?";

            
            PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setInt(1, oferta.getCod_oferta());
            stm.setObject(2, oferta.getTempo_inicial());
            stm.setObject(3, oferta.getTempo_final());
            stm.setInt(4, oferta.getPercentual_desconto());
            stm.setInt(5, oferta.getCod_pacote().getCod_pacote());


            stm.executeUpdate();
            resultado = "Dados atualizados com sucesso";
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            resultado = "Erro ao atualizar a oferta";
        }
        return resultado;
    }

    public String removerOferta(int cod_oferta) {
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection conn = conexao.conectar();
        String resultado = "";
        try {
            String consulta = "DELETE FROM Ofertas WHERE cod_oferta = ?";

            PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setInt(1, cod_oferta);

            stm.executeUpdate();
            resultado = "Oferta removida com sucesso";
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            resultado = "Erro ao remover a oferta";
        }
        return resultado;
    }

    public OfertaControle() {
       
    }
}
