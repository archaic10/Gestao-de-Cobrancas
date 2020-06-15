/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.bean;
import Model.dao.DividaDAO;
import Model.dao.PagamentoDAO;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author vitor
 */
public class Pagamento {

    private int idpag;
    private Divida divida;
    private Date data_pagamento;
    private double valorpago;

    public Pagamento() {
    }

    public Pagamento(int cod_pagamento, Divida divida, Date data_pagamento, double valorpago) throws ParseException {
        this.idpag = cod_pagamento;
        this.divida = divida;
        this.data_pagamento = data_pagamento;
        this.valorpago = valorpago;
    }
        public Pagamento(double valorpago) {
        this.valorpago = valorpago;
    }

    public Divida getDivida() {
        return divida;
    }

    public void setDivida(Divida divida) {
        this.divida = divida;
    }

    public int getIdpag() {
        return idpag;
    }

    public void setIdpag(int idpag) {
        this.idpag = idpag;
    }

    public Date getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(Date data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public double getValorpago() {
        return valorpago;
    }

    public void setValorpago(double valorpago) {
        this.valorpago = valorpago;
    }
     public void realizarPagamento(Pagamento pagamento) throws ParseException, SQLException {
        if (this.getValorpago() < this.getDivida().getValorDivida()) {
            JOptionPane.showMessageDialog(null, "O pagamento não pode ser inferior a dívida!", "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            PagamentoDAO pagamentoDao = new PagamentoDAO();
            if (pagamentoDao.insert(this)) {                    
                JOptionPane.showMessageDialog(null, "Pagamento realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);   
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao realizar pagamento!", "Erro", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    public void alterarPagamento(Pagamento pagamento){
        PagamentoDAO pagamentoDao = new PagamentoDAO();
        if (this.getValorpago() < this.getDivida().getValorDivida()) {
            JOptionPane.showMessageDialog(null, "O pagamento não pode ser inferior a dívida!", "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            if (pagamentoDao.update(this)) {
                 JOptionPane.showMessageDialog(null, "Pagamento alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);   
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao alterar pagamento!", "Erro", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    public void excluir(int codigo) {
        PagamentoDAO pagamentoDao = new PagamentoDAO();
        try{
            if (pagamentoDao.delete(codigo)) {
                JOptionPane.showMessageDialog(null, "Pagamento excluido com suecesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Não é possível excluir esse pagamento!", "Erro", JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
