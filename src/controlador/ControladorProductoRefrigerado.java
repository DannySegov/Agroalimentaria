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
import modelos.ProductoRefrigerado;

/**
 *
 * @author carol
 */
public class ControladorProductoRefrigerado {
    
    private List<ProductoRefrigerado> listaProductosRefrigerados = new ArrayList<>();
    private final List<String> listaTemporal = new ArrayList<>();

    private ProductoRefrigerado productoRefrigerado = new ProductoRefrigerado();
    private final File archivo = new File("src/archivosTXT/productosRefrigerado.txt");

    private FileWriter escribirArchivo;
    private PrintWriter escribirLinea;
    private FileReader leerArchivo;
    private BufferedReader bufferReader;
    private BufferedWriter bufferWriter;
    
    public void agregarProducto(ProductoRefrigerado productoF) {

        String[] auxiliar;

        try {
            auxiliar = new String[7];

            auxiliar[0] = productoF.getClave();
            auxiliar[1] = productoF.getDescripcion();
            auxiliar[2] = Integer.toString(productoF.getCantidad());
            auxiliar[3] = productoF.getNumeroLote();
            auxiliar[4] = productoF.getFechaCaducidad();
            auxiliar[5] = productoF.getTemperaturaRecomendada();
            auxiliar[6] = productoF.getTipoContaminacion();

            escribirArchivo = new FileWriter(archivo, true);
            escribirLinea = new PrintWriter(escribirArchivo);

            escribirLinea.println(Arrays.toString(auxiliar));

            JOptionPane.showMessageDialog(null, "Producto agreagdo", "Producto refrigerado", 1);

            escribirArchivo.close();
            escribirLinea.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e, "Agregar producto refrigerado\n" + e, 3);
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
            JOptionPane.showMessageDialog(null, "Error de archivo\n" + e, "Buscar producto refrigerado", 2);
        }
        return false;
    }

    public ProductoRefrigerado buscarProducto(String clave) {

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

                    productoRefrigerado = new ProductoRefrigerado();

                    productoRefrigerado.setClave(auxiliar[0].replace("[", ""));
                    productoRefrigerado.setDescripcion(auxiliar[1].replace(" ", ""));
                    productoRefrigerado.setCantidad(Integer.parseInt(auxiliar[2].replace(" ", "")));
                    productoRefrigerado.setNumeroLote(auxiliar[3].replace(" ", ""));
                    productoRefrigerado.setFechaCaducidad(auxiliar[4].replace(" ", ""));
                    productoRefrigerado.setTemperaturaRecomendada(auxiliar[5].replace(" ", ""));
                    String aux = auxiliar[6].replace(" ", "");
                    productoRefrigerado.setTipoContaminacion(aux.replace("]", ""));

                    return productoRefrigerado;
                }
            }
            leerArchivo.close();
            bufferReader.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error de archivo\n" + e, "Buscar producto refrigerado", 3);
        }
        return null;
    }

    public void modificarProducto(ProductoRefrigerado productoF) {
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
            productoModificado[6] = productoF.getTipoContaminacion();
            
            listaTemporal.add(Arrays.toString(productoModificado));
            
            /**
             * Escribir el archivo con los datos de la lista temporal
             */

            escribirArchivo = new FileWriter(archivo, true);
            escribirLinea = new PrintWriter(escribirArchivo);

            listaTemporal.forEach((producto) -> {
                escribirLinea.println(producto);
            });
            
            JOptionPane.showMessageDialog(null, "Producto modificado satisfactoriamente", "Modificar prodcuto refrigerado", 1);
            escribirArchivo.close();
            escribirLinea.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error:\n"+e.getMessage(), "Modificar prodcuto refrigerado", 3);
        }
    }
    
    public List<ProductoRefrigerado> mostrarTodoProductosF(){
        String linea, auxiliarProdcuto;
        String[] producto;
        try{
            leerArchivo = new FileReader(archivo);
            bufferReader = new BufferedReader(leerArchivo);
            
            while ((linea = bufferReader.readLine()) != null) {
                
                producto = linea.split(",");
                productoRefrigerado = new ProductoRefrigerado();
                
                productoRefrigerado.setClave(producto[0].replace("[", "").trim());
                productoRefrigerado.setDescripcion(producto[1].trim());
                productoRefrigerado.setCantidad(Integer.parseInt(producto[2].trim()));
                productoRefrigerado.setNumeroLote(producto[3].trim());
                productoRefrigerado.setFechaCaducidad(producto[4].trim());
                productoRefrigerado.setTemperaturaRecomendada(producto[5].trim());
                auxiliarProdcuto = producto[6].replace("]", "");
                productoRefrigerado.setTipoContaminacion(auxiliarProdcuto.trim());
                
                listaProductosRefrigerados.add(productoRefrigerado);
            }
            leerArchivo.close();
            bufferReader.close();
            return listaProductosRefrigerados;
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error:\n"+e, "Listado productos refrigerado", 3);
            return null;
        }
    }



    public List<ProductoRefrigerado> getListaProductosRefrigerados() {
        return listaProductosRefrigerados;
    }

    public void setListaProductosRefrigerados(List<ProductoRefrigerado> listaProductosRefrigerados) {
        this.listaProductosRefrigerados = listaProductosRefrigerados;
    }
    
    
}
