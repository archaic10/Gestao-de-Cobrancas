/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.bean;


import View.table.DividaTable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vitor
 */
public class DividaTableModel {
    private final DividaTable view;

    public DividaTableModel(DividaTable view) {
        this.view = view;
    }
    public void popularTabela(ArrayList<Divida> dadosDivida){
    DefaultTableModel tableModel = (DefaultTableModel) this.view.getTabelaDivida().getModel();
    tableModel.setNumRows(0);
    DecimalFormatSymbols x = new DecimalFormatSymbols();
    SimpleDateFormat  formatador  =  new SimpleDateFormat("dd/MM/yyyy");
    x.setDecimalSeparator('.');
        Locale BRAZIL = new Locale("pt","BR");
        DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
        DecimalFormat decimalForm = new DecimalFormat("¤ ###,###,##0.00",REAL);
        for(Divida dividas : dadosDivida){
            tableModel.addRow(new Object[]{
                dividas.getIdDivida(),
                dividas.getDevedor().getNomePessoa(),
                dividas.getDevedor().getDocumento(),
                decimalForm.format(dividas.getValorDivida()),
                dividas.getCredor().getNomePessoa(),
                formatador.format(dividas.getDataAtualizacao())
                
            });
        }
    }
}
