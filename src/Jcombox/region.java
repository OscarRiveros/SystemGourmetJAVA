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
public class region {
    private Integer idregion;
    private String descripcion;
    public region(){
        super();
    }
     public region(Integer codigo, String descripcion){
            this.idregion = codigo;
            this.descripcion = descripcion;
        }

    public Integer getCodigo() {
        return idregion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCodigo(Integer codigo) {
        this.idregion = codigo;
           }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
   @Override
    public String toString() {
        return idregion+"-"+descripcion;
    }

    @Override
    public boolean equals(Object obj) {
         Integer cod1=this.getCodigo();
        //hacemos un cast - por que entrando un objeto
        Integer cod2 =((region) obj).getCodigo();
        if(cod1.equals(cod2)){
            return true;
        }else{
            return false;
        }
    }
    
     
     
}
