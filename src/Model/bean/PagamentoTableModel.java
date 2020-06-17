/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.bean;


import View.table.PagamentoTable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vitor
 */
public class PagamentoTableModel {
    private final PagamentoTable view;

    public PagamentoTableModel(PagamentoTable view) {
        this.view = view;
    }
    public void popularTabela(ArrayList<Pagamento> dadosPagamento){
        DefaultTableModel tableModel = (DefaultTableModel) this.view.getTabelaPagamento().getModel();
         tableModel.setNumRows(0);
          DecimalFormatSymbols x = new DecimalFormatSymbols();
        x.setDecimalSeparator('.');
        Locale BRAZIL = new Locale("pt","BR");
        DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
        DecimalFormat decimalForm = new DecimalFormat("¤ ###,###,##0.00",REAL);
        SimpleDateFormat  formatador  =  new SimpleDateFormat("dd/MM/yyyy");
      
         for(Pagamento   pagamentos :dadosPagamento){
             
             tableModel.addRow(new Object[]{           
                pagamentos.getIdpag(),
                pagamentos.getDivida().getIdDivida(),                
                pagamentos.getDivida().getDevedor().getNomePessoa(),
                pagamentos.getDivida().getDevedor().getDocumento(),
                decimalForm.format(pagamentos.getDivida().getValorDivida()),
                pagamentos.getDivida().getCredor().getNomePessoa(),
                decimalForm.format(pagamentos.getValorpago()),              
                formatador.format(pagamentos.getData_pagamento()),
                formatador.format(pagamentos.getDivida().getDataAtualizacao())
           });  
         }
    }
    
}
