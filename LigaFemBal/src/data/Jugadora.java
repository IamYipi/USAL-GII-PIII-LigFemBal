/*
 * Javier García Pechero 
 * GRADO INGENIERÍA INFORMÁTICA 
 * GRUPO: PA3
 */
package data;

import com.coti.tools.Esdia;
import java.io.Serializable;

/**
 *
 * @author javi
 */
public class Jugadora implements Serializable{ 

    
    /*VARIABLES*/
    
    private String nombre;
    private String posicion;
    private int dorsal;
    private String nacimiento;
    private String nacionalidad;
    private int altura;
    
    /*CONSTRUCTOR*/

    public Jugadora(String nombre, String posicion, int dorsal, String nacimiento, String nacionalidad,int altura) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.dorsal = dorsal;
        this.nacimiento = nacimiento;
        this.nacionalidad = nacionalidad;
        this.altura = altura;
    }

    /*GETTERS AND SETTERS*/
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    /*METODOS*/
    public static Jugadora crearNuevaJugadora(){
        Jugadora jugadora;
        try{
            String n = Esdia.readString_ne("\nIntroduzca el nombre de la jugadora: ");
            String p = Esdia.readString_ne("Introduzca la posicion de la jugadora(alero~pivot~base~ala-pivot: ");
            int d    = Esdia.readInt("Introduzca el dorsal de la jugadora: ",0,99);
            String nacimiento = Esdia.readString_ne("Introduzca fecha de nacimiento,ciudad,provincia: ");
            String nacion = Esdia.readString_ne("Introduzca nacionalidad de la jugadora: ");
            int altura = Esdia.readInt("Introduzca altura de la jugadora (en centimetros):",150,250);
            jugadora = new Jugadora(n,p,d,nacimiento,nacion,altura);
        }catch(Exception e){
            jugadora = null;
            System.err.println("%n[ERROR] NO SE HA PODIDO CREAR LA JUGADORA%n");
            System.err.println("Exception:"+e);
        }
        return jugadora;
    }
    
    //Carga Jugadoras
    public static Jugadora instanceFromString(String jugadoras) {
         String delimiter = "\t";
         String [] data = jugadoras.split(delimiter);
         Jugadora newJugadora;
         String n;
         String p;
         int d;
         String nac;
         String nacion;
         int alt;
         try{
             if(data[0].isBlank() || data[1].startsWith("-")){
                n = "No existe el dato";
             }else{
                n = data[0];
             }
             
             if(data[1].isBlank() || data[1].startsWith("-")){
                p = "No existe el dato";
             }else{
                p = data[1];
             }
            
             if(data[2].isBlank() || data[2].startsWith("-")){
                 d = -1;
             }else{
                 d = Integer.parseInt(data[2]);
             }
             if(data[3].isBlank() || data[3].startsWith("-")){
                nac = "No existe el dato";
             }else{
                nac = data[3];
             }
             if(data[4].isBlank() || data[4].startsWith("-")){
                nacion = "No existe el dato";
             }else{
                nacion = data[4];
             }
             if(data.length < 6 || data[5].isBlank() || data[5].startsWith("-")){
                 alt = -1;
             }else{
                alt = Integer.parseInt(data[5]);  
             } 
            newJugadora = new Jugadora(n,p,d,nac,nacion,alt);   
         }catch(NumberFormatException e){
            System.err.println("[ERROR] NO SE HA PODIDO CREAR LAS JUGADORAS");
            System.err.println("Exception"+e);
            newJugadora = null;
         }
         return newJugadora;
    }
    
    //MODIFICAR JUGADORA
    
     public static Jugadora modificarCopiaJugadora(Jugadora jugadora) {
         String p;    
         System.out.printf("\nPosicion actual: %s",jugadora.getPosicion());
         if(Esdia.yesOrNo("\nQuieres modificar la posicion?:"))
             p = Esdia.readString_ne("\nIntroduzca la posicion(Base~Alero~Escolta~Ala-pivot): ");
         else
             p = jugadora.getPosicion();
         int d;
         System.out.printf("\nDorsal actual: %d",jugadora.getDorsal());
         if(Esdia.yesOrNo("\nQuieres modificar el dorsal?"))
             d = Esdia.readInt("\nIntroduzca el dorsal: ",0,99);
         else
             d = jugadora.getDorsal();
         String nac;
         System.out.printf("\nNacimiento acutal: %s",jugadora.getNacimiento());
         if(Esdia.yesOrNo("\nQuieres modificar el nacimiento?:"))
             nac = Esdia.readString_ne("\nIntroduzca nacimiento(fecha ciudad provincia): ");
         else
             nac = jugadora.getNacimiento();
         String nacion;
         System.out.printf("\nNacionalidad actual: %s",jugadora.getNacionalidad());
         if(Esdia.yesOrNo("\nQuiere modificar la nacionalidad?:"))
             nacion = Esdia.readString_ne("\nIntroduzca la nacionalidad: ");
         else
             nacion = jugadora.getNacionalidad();
         int alt;
         System.out.printf("\nAltura actual: %d",jugadora.getAltura());
         if(Esdia.yesOrNo("\nQuiere modificar la altura?:"))
             alt = Esdia.readInt("\nIntroduzca la altura en centimetros: ", 150, 250);
         else
             alt = jugadora.getAltura();
         
         return (new Jugadora(jugadora.getNombre(),p,d,nac,nacion,alt));
     }
     
     //FICHERO ENCOLUMNADO
     public String toColString(String nombreEquipo,int first){
         StringBuilder sb = new StringBuilder();
         if(first == 0){
             sb.append(String.format("EQUIPO: %-100s",nombreEquipo));
             sb.append("\n");
             sb.append("======================================================================================================================================================================================");
             sb.append("\n");
         }
        sb.append(String.format("|%-40s|%-20s|%-20s|%-20s|%-20s|%-55s|",this.nombre,this.posicion,this.dorsal,this.altura,this.nacionalidad,this.nacimiento));
         sb.append("\n");
         return sb.toString();
     }
     
    
    
}
