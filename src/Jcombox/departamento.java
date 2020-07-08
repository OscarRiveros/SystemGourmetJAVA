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
public class departamento {
     private Integer iddepartamento;
    private String descripcion;
    public departamento(){
        super();
    }

     public departamento(Integer codigo, String descripcion){
            this.iddepartamento = codigo;
            this.descripcion = descripcion;
        }

   public Integer getCodigo() {
        return iddepartamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCodigo(Integer codigo) {
        this.iddepartamento = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

     @Override
    public String toString() {
        return iddepartamento+"-"+descripcion;
    }

    @Override
    public boolean equals(Object obj) {
         Integer cod1=this.getCodigo();
        //hacemos un cast - por que entrando un objeto
        Integer cod2 =((departamento) obj).getCodigo();
        if(cod1.equals(cod2)){
            return true;
        }else{
            return false;
        }
    }
}
