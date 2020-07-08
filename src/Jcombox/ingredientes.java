/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jcombox;
    

/**
 *
 * @author USER
 */
public class ingredientes {
    private Integer idproductos;
    private String descripcion;
    
    public ingredientes(){
        super();
    }
    public ingredientes(Integer codigo, String descripcion){
            this.idproductos = codigo;
            this.descripcion = descripcion;
        }

    public Integer getCodigo() {
        return idproductos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCodigo(Integer codigo) {
        this.idproductos = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
     @Override
    public String toString() {
        return descripcion;
    }

    @Override
    public boolean equals(Object obj) {
         Integer cod1=this.getCodigo();
        //hacemos un cast - por que entrando un objeto
        Integer cod2 =((ingredientes) obj).getCodigo();
        if(cod1.equals(cod2)){
            return true;
        }else{
            return false;
        }
    } 
}
