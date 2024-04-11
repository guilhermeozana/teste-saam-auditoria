/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cadastro.funcionarios.controller;

import br.com.cadastro.funcionarios.dao.AutenticacaoDAO;
import br.com.cadastro.funcionarios.dao.FuncionarioDAO;
import br.com.cadastro.funcionarios.view.FuncionarioView;
import br.com.cadastro.funcionarios.view.LoginView;
import java.awt.Dimension;

/**
 *
 * @author Guilherme
 */
public class LoginController {
    
    AutenticacaoDAO autenticacaoDAO = new AutenticacaoDAO();
    
    public LoginController() {
    }
    
    
    public boolean fazerLogin(String email, String senha) {
        return autenticacaoDAO.fazerLogin(email, senha);
    }
    
    
}
