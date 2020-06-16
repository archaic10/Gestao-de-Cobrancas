/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.bean;

import Model.dao.ClienteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author vitor
 */
public class Cliente {

    private int idCliente;
    private String nomeCliente;
    private String endereco;
    private String uf;
    private String telefone;
    private String documento;
    private String email;

    public Cliente(int idCliente, String nomeCliente, String endereco, String uf, String telefone, String documento, String email) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.endereco = endereco;
        this.uf = uf;
        this.telefone = telefone;
        this.documento = documento;
        this.email = email;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente() {
    }

    public Cliente(String nome, String email, String documento) {
        this.nomeCliente = nome;
        this.email = email;
        this.documento = documento;

    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void inserir() {
        ClienteDAO clienteDao = new ClienteDAO();
        if (this.getIdCliente() == 0) {
            if (clienteDao.save(this)) {
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com  sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    JOptionPane.showMessageDialog(null, "Erro ao Cliente cadastrar!", "Erro", JOptionPane.WARNING_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    public ArrayList<Cliente>consultar(Cliente cliente) {
        ClienteDAO cl = new ClienteDAO();   
        String cpfFormatado = this.getDocumento().replace("-","");
        this.setDocumento(cpfFormatado.replace(".",""));
        try {      
            return cl.consultarPorCpf(cpfFormatado);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;        
    }
    
    public void alterar() throws SQLException {
        ClienteDAO clienteDao = new ClienteDAO();
        if (this.getIdCliente() > 0) {
            if (clienteDao.update(this)) {
                JOptionPane.showMessageDialog(null, "Cliente alterado com  suecesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    JOptionPane.showMessageDialog(null, "Erro ao Cliente alteraar!", "Erro", JOptionPane.WARNING_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void excluir(Cliente cliente) throws Exception {
        System.out.println("ok");
        ClienteDAO cl = new ClienteDAO();
        if (cl.delete(cliente.idCliente)) {
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!", "Erro", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                JOptionPane.showMessageDialog(null, "Erro ao excluir cliente!", "Erro", JOptionPane.WARNING_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public ArrayList<Cliente> carregarDadosCliente (Cliente cliente) throws Exception{     
        ClienteDAO cl = new ClienteDAO();
        try {
            return cl.obterDados(cliente.documento);   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }   
    public ArrayList<Cliente> listarCliente(){
        ClienteDAO cl = new ClienteDAO();
        try {
            return cl.obterTodos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    public ArrayList<Cliente>  buscarClienteDocumento(String documento){
        ClienteDAO cl = new ClienteDAO(); 
        
        try {
            
            if(documento.equals("           ")){
                return cl.obterTodos();
            }else{
                return cl.consultarPorCpf(documento);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
}
