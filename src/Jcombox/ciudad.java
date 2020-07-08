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
public class ciudad {
    
    private Integer idciudad;
    private String descripcion;
    public ciudad(){
        super();
    }
    public ciudad(Integer codigo, String descripcion){
            this.idciudad = codigo;
            this.descripcion = descripcion;
        }

    public Integer getCodigo() {
        return idciudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCodigo(Integer codigo) {
        this.idciudad = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
     public String toString() {
        return idciudad+"-"+descripcion;
    }

    @Override
    public boolean equals(Object obj) {
         Integer cod1=this.getCodigo();
        //hacemos un cast - por que entrando un objeto
        Integer cod2 =((ciudad) obj).getCodigo();
        if(cod1.equals(cod2)){
            return true;
        }else{
            return false;
        }
    }
}
