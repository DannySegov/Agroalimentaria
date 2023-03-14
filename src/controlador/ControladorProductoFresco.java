/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import modelos.ProductoFresco;

/**
 *
 * @author carol
 */
public class ControladorProductoFresco {

    private List<ProductoFresco> listaProductosFrescos = new ArrayList<>();
    private final List<String> listaTemporal = new ArrayList<>();

    private ProductoFresco productoFresco = new ProductoFresco();
    private final File archivo = new File("src/archivosTXT/productosFrescos.txt");

    private FileWriter escribirArchivo;
    private PrintWriter escribirLinea;
    private FileReader leerArchivo;
    private BufferedReader bufferReader;
    private BufferedWriter bufferWriter;

    public void agregarProducto(ProductoFresco productoF) {

        String[] auxiliar;

        try {
            auxiliar = new String[7];

            auxiliar[0] = productoF.getClave();
            auxiliar[1] = productoF.getDescripcion();
            auxiliar[2] = Integer.toString(productoF.getCantidad());
            auxiliar[3] = productoF.getNumeroLote();
            auxiliar[4] = productoF.getFechaCaducidad();
            auxiliar[5] = productoF.getTemperaturaRecomendada();
            auxiliar[6] = productoF.getTipoAlimento();

            escribirArchivo = new FileWriter(archivo, true);
            escribirLinea = new PrintWriter(escribirArchivo);

            escribirLinea.println(Arrays.toString(auxiliar));

            JOptionPane.showMessageDialog(null, "Producto agreagdo", "Producto fresco", 1);

            escribirArchivo.close();
            escribirLinea.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e, "Agregar producto fresco\n" + e, 2);
        }

    }

    public boolean validarClave(String clave) {

        String comparar;
        String linea;
        String[] auxiliar;

        try {

            leerArchivo = new FileReader(archivo);
            bufferReader = new BufferedReader(leerArchivo);

            while ((comparar = bufferReader.readLine()) != null) {
                auxiliar = comparar.split(",");
                linea = auxiliar[0].replace("[", "");

                if (linea.equals(clave)) {
                    return true;
                }
            }
            leerArchivo.close();
            bufferReader.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error de archivo\n" + e, "Buscar producto fresco", 2);
        }
        return false;
    }

    public ProductoFresco buscarProducto(String clave) {

        String comparar;
        String linea;
        String[] auxiliar;

        try {

            leerArchivo = new FileReader(archivo);
            bufferReader = new BufferedReader(leerArchivo);

            while ((comparar = bufferReader.readLine()) != null) {

                auxiliar = comparar.split(",");
                linea = auxiliar[0].replace("[", "");

                if (linea.equals(clave)) {

                    productoFresco = new ProductoFresco();

                    productoFresco.setClave(auxiliar[0].replace("[", ""));
                    productoFresco.setDescripcion(auxiliar[1].replace(" ", ""));
                    productoFresco.setCantidad(Integer.parseInt(auxiliar[2].replace(" ", "")));
                    productoFresco.setNumeroLote(auxiliar[3].replace(" ", ""));
                    productoFresco.setFechaCaducidad(auxiliar[4].replace(" ", ""));
                    productoFresco.setTemperaturaRecomendada(auxiliar[5].replace(" ", ""));
                    String aux = auxiliar[6].replace(" ", "");
                    productoFresco.setTipoAlimento(aux.replace("]", ""));

                    return productoFresco;
                }
            }
            leerArchivo.close();
            bufferReader.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error de archivo\n" + e, "Buscar producto fresco", 2);
        }
        return null;
    }

    public void modificarProducto(ProductoFresco productoF) {
        String comparar;
        String[] auxiliar, productoModificado;
        String linea;
        try {
            /**
             * Agregar los registros del archivo en una lisa temporal y vaciar
             * el archivo Excluyendo el producto que se desea modificar
             */
            leerArchivo = new FileReader(archivo);
            bufferReader = new BufferedReader(leerArchivo);

            while ((comparar = bufferReader.readLine()) != null) {
                auxiliar = comparar.split(",");
                linea = auxiliar[0].replace("[", "");

                if (!linea.equals(productoF.getClave())) {
                    listaTemporal.add(comparar);
                }

            }

            bufferWriter = new BufferedWriter(new FileWriter(archivo));
            bufferWriter.write("");
            bufferWriter.close();

            /**
             * Agregar el dato modificado a la lista temporal
             */
            productoModificado = new String[7];

            productoModificado[0] = productoF.getClave();
            productoModificado[1] = productoF.getDescripcion();
            productoModificado[2] = Integer.toString(productoF.getCantidad());
            productoModificado[3] = productoF.getNumeroLote();
            productoModificado[4] = productoF.getFechaCaducidad();
            productoModificado[5] = productoF.getTemperaturaRecomendada();
            productoModificado[6] = productoF.getTipoAlimento();
            
            listaTemporal.add(Arrays.toString(productoModificado));
            
            /**
             * Escribir el archivo con los datos de la lista temporal
             */

            escribirArchivo = new FileWriter(archivo, true);
            escribirLinea = new PrintWriter(escribirArchivo);

            listaTemporal.forEach((producto) -> {
                escribirLinea.println(producto);
            });
            
            JOptionPane.showMessageDialog(null, "Producto modificado satisfactoriamente", "Modificar prodcuto fresco", 1);
            escribirArchivo.close();
            escribirLinea.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error:\n"+e.getMessage(), "Modificar prodcuto fresco", 3);
        }
    }
    
    public List<ProductoFresco> mostrarTodoProductosF(){
        String linea, auxiliarProdcuto;
        String[] producto;
        try{
            leerArchivo = new FileReader(archivo);
            bufferReader = new BufferedReader(leerArchivo);
            
            while ((linea = bufferReader.readLine()) != null) {
                
                producto = linea.split(",");
                productoFresco = new ProductoFresco();
                
                productoFresco.setClave(producto[0].replace("[", "").trim());
                productoFresco.setDescripcion(producto[1].trim());
                productoFresco.setCantidad(Integer.parseInt(producto[2].trim()));
                productoFresco.setNumeroLote(producto[3].trim());
                productoFresco.setFechaCaducidad(producto[4].trim());
                productoFresco.setTemperaturaRecomendada(producto[5].trim());
                auxiliarProdcuto = producto[6].replace("]", "");
                productoFresco.setTipoAlimento(auxiliarProdcuto.trim());
                
                listaProductosFrescos.add(productoFresco);
            }
            leerArchivo.close();
            bufferReader.close();
            return listaProductosFrescos;
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error:\n"+e, "Listado productos frescos", 3);
            return null;
        }
    }

    public List<ProductoFresco> getListaProductosFrescos() {
        return listaProductosFrescos;
    }

    public void setListaProductosFrescos(List<ProductoFresco> listaProductosFrescos) {
        this.listaProductosFrescos = listaProductosFrescos;
    }

    public ProductoFresco getProductoFresco() {
        return productoFresco;
    }

    public void setProductoFresco(ProductoFresco productoFresco) {
        this.productoFresco = productoFresco;
    }
}
