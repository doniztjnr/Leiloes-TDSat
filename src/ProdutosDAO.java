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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
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

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

}
