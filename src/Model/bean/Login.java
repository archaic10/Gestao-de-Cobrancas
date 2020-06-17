/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.bean;

import Model.dao.UsuarioDAO;
import View.LoginForm;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author vitor
 */
public class Login {
    private String nome_usuario;
    private String senha;
    private final LoginForm view;
    public boolean  autenticacao;
    public Login(LoginForm view) {
        this.view = view;
    }
 
    
    
    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public boolean logar(){
        UsuarioDAO usuario = new UsuarioDAO();
        this.autenticacao = usuario.autenticar(this);
        if(usuario.autenticar(this)){
            JOptionPane.showMessageDialog(null, "Logado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            System.out.println("Sua senha é: "+this.getSenha());
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!", "Erro", JOptionPane.WARNING_MESSAGE);
           return false;
        }
    }
    
}
