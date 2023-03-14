/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author carol
 */
public class ProductoCongelado extends Producto {
    
    private String tipoCongelacion;

    public ProductoCongelado() {
    }

    public ProductoCongelado(String tipoCongelacion) {
        this.tipoCongelacion = tipoCongelacion;
    }
    

    public String getTipoCongelacion() {
        return tipoCongelacion;
    }

    public void setTipoCongelacion(String tipoCongelacion) {
        this.tipoCongelacion = tipoCongelacion;
    }
    
    
}
