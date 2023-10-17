/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    Statement stmt;
    ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) throws SQLException {

        conn = new conectaDAO().connectDB();

        String SQL_INSERT = "INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)";

        try {
            prep = conn.prepareStatement(SQL_INSERT);
            prep.setString(1, produto.getNome());
            prep.setBigDecimal(2, new BigDecimal(produto.getValor()));
            prep.setString(3, produto.getStatus());

            prep.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() throws SQLException {

        String SQL_SELECT = "SELECT id, nome, valor, status FROM produtos";

        try {
            conn = new conectaDAO().connectDB();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL_SELECT);

            while (rs.next()) {
                ProdutosDTO produtosDTO = new ProdutosDTO();
                produtosDTO.setId(rs.getInt("id"));
                produtosDTO.setNome(rs.getString("nome"));
                produtosDTO.setValor(rs.getFloat("valor"));
                produtosDTO.setStatus(rs.getString("status"));
                listagem.add(produtosDTO);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            rs.close();
            stmt.close();
            conn.close();
        }
        return listagem;
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() throws SQLException {
        String SQL_SELECT = "SELECT id, nome, valor, status FROM produtos WHERE status = 'Vendido'";

        try {
            conn = new conectaDAO().connectDB();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL_SELECT);

            while (rs.next()) {
                ProdutosDTO produtosDTO = new ProdutosDTO();
                produtosDTO.setId(rs.getInt("id"));
                produtosDTO.setNome(rs.getString("nome"));
                produtosDTO.setValor(rs.getFloat("valor"));
                produtosDTO.setStatus(rs.getString("status"));
                listagem.add(produtosDTO);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            rs.close();
            stmt.close();
            conn.close();
        }
        return listagem;
    }

    public void venderProduto(int idDaVenda) throws SQLException {
        String SQL_UPDATE = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(SQL_UPDATE);

            prep.setInt(1, idDaVenda);

            prep.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            prep.close();
            conn.close();
        }
    }
}
