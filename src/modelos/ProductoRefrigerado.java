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
public class ProductoRefrigerado extends Producto{
    private String tipoContaminacion;

    public ProductoRefrigerado() {
    }

    public ProductoRefrigerado(String tipoContaminacion) {
        this.tipoContaminacion = tipoContaminacion;
    }

    public String getTipoContaminacion() {
        return tipoContaminacion;
    }

    public void setTipoContaminacion(String tipoContaminacion) {
        this.tipoContaminacion = tipoContaminacion;
    }
}
