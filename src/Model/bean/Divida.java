/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.bean;

import Model.dao.ClienteDAO;
import Model.dao.DividaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author vitor
 */
public class Divida {

    private int idDivida;
    private Pessoa credor;
    private Date dataAtualizacao;
    private double valorDivida;
    private Pessoa devedor;

    public Divida(int idDivida, Pessoa credor, Date dataAtualizacao, double valorDivida, Pessoa devedor) {
        this.idDivida = idDivida;
        this.credor = credor;
        this.dataAtualizacao = dataAtualizacao;
        this.valorDivida = valorDivida;
        this.devedor = devedor;
    }

    public Divida(double valor_divida, Date dataAtualizacao) {
        this.valorDivida = valor_divida;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Pessoa getCredor() {
        return credor;
    }

    public Divida() {
    }

    public int getIdDivida() {
        return idDivida;
    }

    public void setIdDivida(int idDivida) {
        this.idDivida = idDivida;
    }

    public void setCredor(Pessoa credor) {
        this.credor = credor;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public double getValorDivida() {
        return valorDivida;
    }

    public void setValorDivida(double valorDivida) {
        this.valorDivida = valorDivida;
    }

    public Pessoa getDevedor() {
        return devedor;
    }

    public void setDevedor(Pessoa devedor) {
        this.devedor = devedor;
    }
    public ArrayList<Cliente> obterClientes(){
        ClienteDAO cl = new ClienteDAO();   
        try {      
            return cl.obterTodos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;   
    }
    public void inserir() {
        DividaDAO dividaDAO = new DividaDAO();
        if (this.getIdDivida() == 0) {
            if (dividaDAO.Salvar(this)) {
                JOptionPane.showMessageDialog(null, "Dívida cadastrada com  sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar dívida!", "Erro", JOptionPane.WARNING_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
     public void alterar() throws SQLException {
        DividaDAO dividaDAO = new DividaDAO();
        if (this.getIdDivida() > 0) {
            if (dividaDAO.update(this)) {
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
    public ArrayList<Divida> consultar(String documento){
         DividaDAO divida = new DividaDAO();   
        documento= documento.replace("-","");
        documento = documento.replace(".","");
        try {      
            return divida.obter("",documento);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;   
    }
    public ArrayList<Divida> consultar(String documento, int id){
         DividaDAO divida = new DividaDAO();   
        documento= documento.replace("-","");
        documento = documento.replace(".","");
        try {      
            return divida.obter("",documento,id);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;   
    }
    public void excluir(int codigo) {
        DividaDAO dividaDao = new DividaDAO();
        try{
            if (dividaDao.delete(codigo)) {
                JOptionPane.showMessageDialog(null, "Dívida excluida com suecesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Não é possível excluir essa dívida!", "Erro", JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ArrayList<Divida> listarDivida(){
        DividaDAO dividaDao = new DividaDAO();
        try {
            return dividaDao.obterTodos("Todos");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;  
    }
    public ArrayList<Divida> buscarDevedorCPF(String documento, String filtro){
        DividaDAO dividaDao = new DividaDAO();
        try {      
            if(documento.equals("           ")){
                return dividaDao.obterTodos(filtro);
            }else{
                return dividaDao.obter(filtro, documento);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;  
    }
}
