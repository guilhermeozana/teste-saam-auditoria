/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cadastro.funcionarios.controller;

import br.com.cadastro.funcionarios.dao.AutenticacaoDAO;
import br.com.cadastro.funcionarios.vo.UsuarioVO;
import org.openswing.swing.message.receive.java.Response;

/**
 *
 * @author Guilherme
 */
public class CadastroController {
    AutenticacaoDAO autenticacaoDAO = new AutenticacaoDAO();
    
    public Response fazerCadastro(UsuarioVO usuarioVO) {
        return autenticacaoDAO.fazerCadastro(usuarioVO);
    }
    
}
