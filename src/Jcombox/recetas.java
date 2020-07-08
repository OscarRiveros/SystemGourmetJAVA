
package Jcombox;


public class recetas {
    private Integer idproducto;
    private String descripcion;
    public recetas(){
        super();
    }
        public recetas(Integer codigo, String descripcion){
            this.idproducto = codigo;
            this.descripcion = descripcion;
        }

    public Integer getCodigo() {
        return idproducto;
    }

    public String getDescripccion() {
        return descripcion;
    }

    public void setCodigo(Integer codigo) {
        this.idproducto = codigo;
    }

    public void setDescripccion(String descripcion) {
        this.descripcion = descripcion;
    }
    @Override
    public String toString() {
        return idproducto+"-"+descripcion;
    }

    @Override
    public boolean equals(Object obj) {
         Integer cod1=this.getCodigo();
        //hacemos un cast - por que entrando un objeto
        Integer cod2 =((recetas) obj).getCodigo();
        if(cod1.equals(cod2)){
            return true;
        }else{
            return false;
        }
    } 
}

