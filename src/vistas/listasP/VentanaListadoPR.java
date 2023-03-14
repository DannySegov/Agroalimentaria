/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vistas.listasP;

import controlador.ControladorProductoRefrigerado;
import modelos.ProductoRefrigerado;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carol
 */
public class VentanaListadoPR extends javax.swing.JInternalFrame {

    /** Creates new form VentanaListadoPR */
    public VentanaListadoPR() {
        initComponents();
        generarTable();
    }
    
    private void generarTable(){
        
        ControladorProductoRefrigerado controlador = new ControladorProductoRefrigerado();
        
        List<ProductoRefrigerado> listaProductos = controlador.mostrarTodoProductosF();
        
        DefaultTableModel modeloTabla = new DefaultTableModel(new String[]{"id", "Clave", "Descripción", "Cantidad", "No. Lote", "Fecha Cad.",
        "Temperatura Recom.", "Tipo de Contaminacion"}, 0);
        Object[] fila = new Object[modeloTabla.getColumnCount()];
        
        this.tablaProductosR.setModel(modeloTabla);
        
        if (listaProductos != null){
            
            if (!listaProductos.isEmpty()) {
                
                for (int i = 0; i<listaProductos.size(); i++){
                    
                    fila[0] = (i+1);
                    fila[1] = listaProductos.get(i).getClave();
                    fila[2] = listaProductos.get(i).getDescripcion();
                    fila[3] = listaProductos.get(i).getCantidad();
                    fila[4] = listaProductos.get(i).getNumeroLote();
                    fila[5] = listaProductos.get(i).getFechaCaducidad();
                    fila[6] = listaProductos.get(i).getTemperaturaRecomendada().replace("/", " ");
                    fila[7] = listaProductos.get(i).getTipoContaminacion();
                    
                    modeloTabla.addRow(fila);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Lista vacia.\nNo se han encontrado registros.", "Lista productos frescos", 3);
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Lista vacia.\nNo se han encontrado registros.", "Lista productos frescos", 3);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductosR = new javax.swing.JTable();

        setClosable(true);
        setTitle("Listado productos refrigerados");

        tablaProductosR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Clave", "Descripción", "Cantidad", "No. Lote", "Fecha Cad.", "Temperatura Recom.", "Tipo de contaminación"
            }
        ));
        jScrollPane1.setViewportView(tablaProductosR);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaProductosR;
    // End of variables declaration//GEN-END:variables

}