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
public class evento {
      
      private String evento;
      public evento(){
        super();
    }
    
      public evento(String evento){
          
            this.evento = evento;
        }

   

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }
    @Override
     public String toString() {
        return evento;
    }
     
     @Override
    public boolean equals(Object obj) {
         String cod1=this.getEvento();
        //hacemos un cast - por que entrando un objeto
        String cod2 =((evento) obj).getEvento();
        if(cod1.equals(cod2)){
            return true;
        }else{
            return false;
        }
    } 
      
}
