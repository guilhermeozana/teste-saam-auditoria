/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cadastro.funcionarios.dao;

import br.com.cadastro.funcionarios.util.Conexao;
import br.com.cadastro.funcionarios.util.EmailValidator;
import br.com.cadastro.funcionarios.util.Util;
import br.com.cadastro.funcionarios.vo.FuncionarioVO;
import br.com.cadastro.funcionarios.vo.UsuarioVO;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.server.QueryUtil;

/**
 *
 * @author Guilherme
 */
public class AutenticacaoDAO {
    Connection conn = Conexao.getConexao();
    
    public boolean fazerLogin(String email, String senha) {
        try {
            
            String sql = "SELECT senha FROM usuarios WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String senhaHashArmazenada = rs.getString("senha");
                
                String senhaHashFornecida = hashSHA256(senha);
                
                return senhaHashArmazenada.equals(senhaHashFornecida);
            } else {
                Util.getAlert().alertErro("Usuário não encontrado!");
                return false;
            }
        } catch (SQLException | NoSuchAlgorithmException ex) {
            Util.getAlert().alertErro("Erro ao fazer login: " + ex.getMessage());
            return false;
        }
    }
    
    public Response fazerCadastro(UsuarioVO usuarioVO) {
        try {
            
            if(!EmailValidator.isValidEmail(usuarioVO.getEmail())) {
                Util.getAlert().alertErro("Formato de e-mail inválido!");
                
                return new ErrorResponse("Erro ao fazer cadastro");
            }
            
            if(verificaEmailExiste(usuarioVO.getEmail())){
                Util.getAlert().alertErro("E-mail já cadastrado na base de dados!");
                
                return new ErrorResponse("Erro ao fazer cadastro");
            }
                
            String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuarioVO.getNome());
            stmt.setString(2, usuarioVO.getEmail());
            stmt.setString(3, hashSHA256(usuarioVO.getSenha()));

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                Util.getAlert().alertSucesso("Usuário cadastrado com sucesso!");

                return new VOResponse(usuarioVO);
            } else {
                return new ErrorResponse("Erro ao fazer cadastro");
            }
        
            
        } catch (SQLException | NoSuchAlgorithmException ex) {
            return new ErrorResponse("Erro ao fazer cadastro: " + ex.getMessage());
        }
    }
    
    public String hashSHA256(String senha) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(senha.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private boolean verificaEmailExiste(String email) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM usuarios WHERE email = ?;";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        
        return rs.next() && rs.getInt("count") > 0;
    }
    
    
    
}
