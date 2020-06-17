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
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     public boolean realizarPagamento(Pagamento pagamento) throws ParseException, SQLException {
        if (this.getValorpago() < this.getDivida().getValorDivida()) {
            JOptionPane.showMessageDialog(null, "O pagamento não pode ser inferior a dívida!", "Erro", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            PagamentoDAO pagamentoDao = new PagamentoDAO();
            if (pagamentoDao.insert(this)) {                    
                JOptionPane.showMessageDialog(null, "Pagamento realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);   
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao realizar pagamento!", "Erro", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
    }
    public boolean alterarPagamento(Pagamento pagamento){
        PagamentoDAO pagamentoDao = new PagamentoDAO();
        if (this.getValorpago() < this.getDivida().getValorDivida()) {
            JOptionPane.showMessageDialog(null, "O pagamento não pode ser inferior a dívida!", "Erro", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            if (pagamentoDao.update(this)) {
                JOptionPane.showMessageDialog(null, "Pagamento alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);   
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao alterar pagamento!", "Erro", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
    }
    public boolean excluir(int codigo) {
        PagamentoDAO pagamentoDao = new PagamentoDAO();
        try{
            if (pagamentoDao.delete(codigo)) {
                JOptionPane.showMessageDialog(null, "Pagamento excluido com suecesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Não é possível excluir esse pagamento!", "Erro", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
             return false;
        }
    }
    public ArrayList<Pagamento> listarPagamento(){
        PagamentoDAO pagamentoDAO = new PagamentoDAO();
        try{
            return pagamentoDAO.obterTodos();
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;    
    }
    public ArrayList<Pagamento> buscarPorDocumento(String documento){
        PagamentoDAO pagamentoDAO = new PagamentoDAO();
        try{
            if(documento.equals("           ")){
                return pagamentoDAO.obterTodos();
            }else{
                return pagamentoDAO.obter(documento);
            }
            
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;    
    }
    public ArrayList<Divida> obterDividas(String filtro,String documento){
        DividaDAO divida = new DividaDAO();
        filtro = filtro == "Pagar"? "Não pagas" : "Pagas";
        try {
            return divida.obter(filtro, documento);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null; 
        
    }
    public ArrayList<Divida> obterDividaDetalhada(String filtro,String documento, String documentoCredor){
        DividaDAO divida = new DividaDAO();
        filtro = filtro == "Pagar"? "Não pagas" : "Pagas";
        try {
            return divida.obterCredorDevedor(filtro, documento, documentoCredor);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null; 
        
    }
    
    public String consultarFaturamento(String dataIni, String dataF) throws ParseException{
        PagamentoDAO pagamentoDao = new PagamentoDAO();
         String formato = "dd/MM/yyyy";
         String dataInicio =dataIni;
         String dataFim =dataF;
        Date dataInicial = new SimpleDateFormat(formato).parse(dataInicio);
        Date dataFinal = new SimpleDateFormat(formato).parse(dataFim);
        String resultado = "";
        DecimalFormatSymbols x = new DecimalFormatSymbols();         
        x.setDecimalSeparator('.');
        Locale BRAZIL = new Locale("pt","BR");
        DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
        DecimalFormat decimalForm = new DecimalFormat("¤ ###,###,##0.00",REAL);
        try {
            for(Pagamento pagamentos : pagamentoDao.obterFaturamento(dataInicial,dataFinal)){
                resultado = "O faturamento entre "+dataInicio+" e "+dataFim+" é : "+decimalForm.format(pagamentos.getValorpago());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
         return resultado;
    } 
}
