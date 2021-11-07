/*
 * Javier García Pechero 
 * GRADO INGENIERÍA INFORMÁTICA 
 * GRUPO: PA3
 */
package data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author javi
 */
public class Equipo implements Serializable{

    private String nombre;
    private String direccion;
    private String telefono;
    private String web;
    private String email;
    private List<Jugadora> jugadoras;

    public Equipo(String nombre, String direccion, String telefono, String web, String email, List<Jugadora> jugadoras) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.web = web;
        this.email = email;
        this.jugadoras = jugadoras;
    }
 

    public Equipo(List<Jugadora> jugadoras) {
        this.jugadoras = jugadoras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Jugadora> getJugadoras() {
        return jugadoras;
    }

    public void setJugadoras(List<Jugadora> jugadoras) {
        this.jugadoras = jugadoras;
    }
    
    //METODOS
    //Lectura fichero equipos.txt
    
     public static Equipo instanceFromString(String equipos) {
         String delimiter = "#";
         String [] data = equipos.split(delimiter);
         Equipo newEquipo;
         try{
             String n = data[0];
             String d = data[1];
             String t = data[2];
             String w = data[3];
             String e = data[4];
             newEquipo = new Equipo(n,d,t,w,e,null); 
         }catch(NumberFormatException e){
            System.err.println("[ERROR] NO SE HA PODIDO CREAR LOS EQUIPOS");
            System.err.println("Exception"+e);
            newEquipo = null;
         }
         return newEquipo;
     }
    
    //Carga jugadoras
    
    
    public static List<Jugadora> cargarJugadoras(String s, List<Jugadora> jugadora) {
      try {
          jugadora.add(Jugadora.instanceFromString(s));
      }catch(NumberFormatException e){
          System.err.println("[ERROR] NO SE HA PODIDO CARGAR LAS JUGADORAS");
            System.err.println("Exception"+e);
      }   
       return jugadora;
    }
    
   //FICHERO ENCOLUMNADO
    public String toColString(int first){
         StringBuilder sb = new StringBuilder();
         if(first == 0){
             sb.append(String.format("RELACION EQUIPOS FICHERO ENCOLUMNADO"));
             sb.append("\n");
             sb.append("======================================================================================================================================================================================");
             sb.append("\n");
             sb.append(String.format("|%-40s %-10s %-42s %-40s %-60s ","NOMBRE","TELEFONO","EMAIL","WEB","DIRECCION"));
             sb.append("\n");
         }
        sb.append(String.format("|%-40s %-10s %-42s %-44s %-60s ",this.nombre,this.telefono,this.email,this.web,this.direccion));
        sb.append("\n");
        return sb.toString();
     }
    
 }

    

