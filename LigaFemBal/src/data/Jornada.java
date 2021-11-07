/*
 * Javier García Pechero 
 * GRADO INGENIERÍA INFORMÁTICA 
 * GRUPO: PA3
 */
package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author javi
 */
public class Jornada implements Serializable{

    private int numJornada;
    private String fechaJornada;
    private List<Partido> partidos;
    private List<Datos_equipo> clasificacion;
    
    /*CONSTRUCTOR*/
    
    public Jornada(int numJornada, String fechaJornada, List<Partido> partidos, List<Datos_equipo> clasificacion) {
        this.numJornada = numJornada;
        this.fechaJornada = fechaJornada;
        this.partidos = partidos;
        this.clasificacion = clasificacion;
    }
    
    /*GETTERS AND SETTERS*/
    
    public int getNumJornada() {
        return numJornada;
    }

    public void setNumJornada(int numJornada) {
        this.numJornada = numJornada;
    }

    public String getFechaJornada() {
        return fechaJornada;
    }

    public void setFechaJornada(String fechaJornada) {
        this.fechaJornada = fechaJornada;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public List<Datos_equipo> getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(List<Datos_equipo> clasificacion) {
        this.clasificacion = clasificacion;
    }
    
    //METODOS
    
    public static Jornada instanceFromString(String jornadas) {
        String delimiter1 = "\\+";
        String delimiter2 = "#";
        String delimiter3 = "\\$";
        Jornada newJornada;
        try{
            String[] data = jornadas.split(delimiter1);
            int n = Integer.parseInt(data[0]);
            String f = data[1];
            List<String> p = Arrays.asList(data[2].split(delimiter2));     
            List<Partido> partidos = new ArrayList<>();
            for (String s: p){
                if(!s.isEmpty()){
                    String[] datitos =  s.split(delimiter3);
                    String el = datitos[0];
                    String ev = datitos[1];
                    String fp = datitos[2];
                    String hp = datitos[3];
                    Partido par = new Partido(el,ev,0,0,fp,hp);
                    if(par != null){
                        partidos.add(par);
                    }
                }
            }
            newJornada = new Jornada(n,f,partidos,null); 
        }catch(NumberFormatException e){
            System.err.println("[ERROR] NO SE HA PODIDO CREAR LA JORNADA");
            System.err.println("Exception"+e);
            newJornada = null;
        }
        return newJornada;
    }
    
   

    public static void guardarDatosJornada(String datosJornada, int numJornada, Jornada jornada,Jornada jornadaAnterior,int first,List<Equipo> equipos) {
        int encontrado = 0;   
        if(first!=0){
            jornada.clasificacion=null;
        }
        List<Partido> p = jornada.getPartidos();
        if(jornada.clasificacion==null){
            jornada.clasificacion = new ArrayList<>();
        }   
        List<Datos_equipo> clasificacionAnterior = jornadaAnterior.getClasificacion();
        Datos_equipo eq = null;
        String delimiter = "=";   
        String equiLocal = null;
        String equiVisitante = null;
        int puntosLocal = 0;
        int puntosVisitante = 0;
        int pj;
        int pg;
        int pp;
        int pf;
        int pc;
        int pclasif;
        int pjLocal = 0;
        int pgLocal = 0;
        int ppLocal = 0;
        int pfLocal = 0;
        int pcLocal = 0;
        int pclasifLocal;
        int pjLocalanterior = 0;
        int pgLocalanterior = 0;
        int ppLocalanterior = 0;
        int pfLocalanterior = 0;
        int pcLocalanterior = 0;
        int pclasifLocalanterior;
        int pjVisitante = 0;
        int pgVisitante = 0;
        int ppVisitante = 0;
        int pfVisitante = 0;
        int pcVisitante = 0;
        int pclasifVisitante;
        int pjVisitanteanterior = 0;
        int pgVisitanteanterior = 0;
        int ppVisitanteanterior = 0;
        int pfVisitanteanterior = 0;
        int pcVisitanteanterior = 0;
        int pclasifVisitanteanterior;
        int i;
        int j;
        Equipo equilikua;
        Partido pa;
        int numFaltan;
        int x=0;
        int k = 0;
        List<String> equiposTotales = new ArrayList<>();
        List<String> nombreEquipoPerdido = new ArrayList<>();
        if(first!=0){
        if(p.size()<8){
            numFaltan = (8 - p.size())*2;
                            
                    for(j=0;j<p.size();j++){
                        pa = p.get(j);
                       equiposTotales.add(pa.getEquipoLocal());       
                       equiposTotales.add(pa.getEquipoVisitante());
                       
                    }
                 for(i=0;i<16;i++){
                     equilikua = equipos.get(i);
                  for(j=0;j<equiposTotales.size();j++){
                      if(equilikua.getNombre().toUpperCase().compareTo(equiposTotales.get(j))== 0){
                          break;
                      }
                      if(j==equiposTotales.size()-1){
                         nombreEquipoPerdido.add(equilikua.getNombre().toUpperCase());           
                      }
                  }          
                 }
                 
                 for(i = 0; i < numFaltan;i++){
                     for(Datos_equipo equip : clasificacionAnterior){
                     if(nombreEquipoPerdido.get(i).compareTo(equip.getNombreEquipo())==0){ 
                         pj = equip.getPj();
                         pg = equip.getPg();
                         pp = equip.getPp();
                         pf = equip.getPf();
                         pc = equip.getPc();
                         pclasif = equip.getPclasif();
                         eq = new Datos_equipo(equip.getNombreEquipo(),pj,pg,pp,pf,pc,pclasif);
                         jornada.clasificacion.add(eq);               
                     }
                    }
                 }        
        }    
    }
        
        
        try{
            String[] data = datosJornada.split(delimiter);
            equiLocal = data[0];
            equiVisitante = data[1];
            puntosLocal = Integer.parseInt(data[2]);
            puntosVisitante = Integer.parseInt(data[3]);        
        }catch(NumberFormatException e){
            System.err.println("[ERROR] NO SE HA PODIDO GUARDAR LOS DATOS DE LA JORNADA");
            System.err.println("Exception"+e);       
        }
        for(Partido par : p){ 
            if(par.getEquipoLocal().compareTo(equiLocal) == 0 && par.getEquipoVisitante().compareTo(equiVisitante) == 0){
                par.setPuntosEquipoLocal(puntosLocal);
                par.setPuntosEquipoVisitante(puntosVisitante);
                    for(Datos_equipo equ:clasificacionAnterior){
                     if(equ.getNombreEquipo().compareTo(equiLocal)==0){
                          pjLocalanterior = equ.getPj();
                          pgLocalanterior = equ.getPg();
                          ppLocalanterior = equ.getPp();
                          pfLocalanterior = equ.getPf();
                          pcLocalanterior = equ.getPc();
                          pclasifLocalanterior = equ.getPclasif();
                     }
                     if(equ.getNombreEquipo().compareTo(equiVisitante)==0){
                         pjVisitanteanterior = equ.getPj();
                          pgVisitanteanterior = equ.getPg();
                          ppVisitanteanterior = equ.getPp();
                          pfVisitanteanterior = equ.getPf();
                          pcVisitanteanterior = equ.getPc();
                          pclasifVisitanteanterior = equ.getPclasif();
                     }
                    }
                     if(puntosLocal>puntosVisitante){
                         pjLocal = pjLocalanterior+1;
                         pgLocal = pgLocalanterior+1;
                         ppLocal = ppLocalanterior;
                         pfLocal = pfLocalanterior+puntosLocal;
                         pcLocal = pcLocalanterior+puntosVisitante;
                         eq = new Datos_equipo(equiLocal,pjLocal,pgLocal,ppLocal,pfLocal,pcLocal,2*pgLocal+ppLocal);
                         jornada.clasificacion.add(eq);
                         pjVisitante = pjVisitanteanterior+1;
                         pgVisitante = pgVisitanteanterior;
                         ppVisitante = ppVisitanteanterior+1;
                         pfVisitante = pfVisitanteanterior+puntosVisitante;
                         pcVisitante = pcVisitanteanterior+puntosLocal;
                         eq = new Datos_equipo(equiVisitante,pjVisitante,pgVisitante,ppVisitante,pfVisitante,pcVisitante,2*pgVisitante+ppVisitante);
                         jornada.clasificacion.add(eq);
                        
                     }else{
                         if(puntosLocal == puntosVisitante){
                             if(puntosLocal == 0 && puntosVisitante == 0){
                             //PARTIDO NO JUGADO PERO EXISTE EN EL FICHERO CON VALORES 0 0
                             pjLocal = pjLocalanterior;
                             pgLocal = pgLocalanterior;
                             pfLocal = pfLocalanterior;
                             pcLocal = pcLocalanterior;
                             eq = new Datos_equipo(equiLocal,pjLocal,pgLocal,ppLocal,pfLocal,pcLocal,2*pgLocal+ppLocal);
                             jornada.clasificacion.add(eq);
                             pjVisitante = pjVisitanteanterior;
                             pgVisitante = pgVisitanteanterior;
                             ppVisitante = ppVisitanteanterior;
                             pfVisitante = pfVisitanteanterior;
                             pcVisitante = pcVisitanteanterior;
                             eq = new Datos_equipo(equiVisitante,pjVisitante,pgVisitante,ppVisitante,pfVisitante,pcVisitante,2*pgVisitante+ppVisitante);
                             jornada.clasificacion.add(eq);
                             }else{
                                 //EMPATE
                                 pjLocal = pjLocalanterior+1;
                                 pgLocal = pgLocalanterior;
                                 ppLocal = ppLocalanterior;
                                 pfLocal = pfLocalanterior+puntosLocal;
                                 pcLocal = pcLocalanterior+puntosVisitante;
                                 eq = new Datos_equipo(equiLocal,pjLocal,pgLocal,ppLocal,pfLocal,pcLocal,2*pgLocal+ppLocal);
                                 jornada.clasificacion.add(eq);
                                 pjVisitante = pjVisitanteanterior+1;
                                 pgVisitante = pgVisitanteanterior;
                                 ppVisitante = ppVisitanteanterior;
                                 pfVisitante = pfVisitanteanterior+puntosVisitante;
                                 pcVisitante = pcVisitanteanterior+puntosLocal;
                                 eq = new Datos_equipo(equiVisitante,pjVisitante,pgVisitante,ppVisitante,pfVisitante,pcVisitante,2*pgVisitante+ppVisitante);
                                 jornada.clasificacion.add(eq);  
                             }
                         }else{
                         pjLocal = pjLocalanterior+1;
                         pgLocal = pgLocalanterior;
                         ppLocal = ppLocalanterior+1;
                         pfLocal = pfLocalanterior+puntosLocal;
                         pcLocal = pcLocalanterior+puntosVisitante;
                         eq = new Datos_equipo(equiLocal,pjLocal,pgLocal,ppLocal,pfLocal,pcLocal,2*pgLocal+ppLocal);
                         jornada.clasificacion.add(eq);
                         pjVisitante = pjVisitanteanterior+1;
                         pgVisitante = pgVisitanteanterior+1;
                         ppVisitante = ppVisitanteanterior;
                         pfVisitante = pfVisitanteanterior+puntosVisitante;
                         pcVisitante = pcVisitanteanterior+puntosLocal;
                         eq = new Datos_equipo(equiVisitante,pjVisitante,pgVisitante,ppVisitante,pfVisitante,pcVisitante,2*pgVisitante+ppVisitante);
                         jornada.clasificacion.add(eq); 
                        }
                     }
                    }
                }             
            }
        
    
    
     public static void guardarDatosPrimeraJornada(String datosJornada, int numJornada, Jornada jornada,int first) {          
        if(first!=0){
             jornada.clasificacion=null;
         } 
        List<Partido> p = jornada.getPartidos();    
        List<Datos_equipo> clasificacion = new ArrayList<>();
        if(jornada.clasificacion==null){
            jornada.clasificacion = new ArrayList<>();    
        }
        Datos_equipo eq = null;
        String delimiter = "=";   
        String equiLocal = null;
        String equiVisitante = null;
        int puntosLocal = 0;
        int puntosVisitante = 0;
        try{
            String[] data = datosJornada.split(delimiter);
            equiLocal = data[0];
            equiVisitante = data[1];
            puntosLocal = Integer.parseInt(data[2]);
            puntosVisitante = Integer.parseInt(data[3]);        
        }catch(NumberFormatException e){
            System.err.println("[ERROR] NO SE HA PODIDO GUARDAR LOS DATOS DE LA JORNADA");
            System.err.println("Exception"+e);       
        }
        for(Partido par : p){
            if(par.getEquipoLocal().compareTo(equiLocal)== 0 && par.getEquipoVisitante().compareTo(equiVisitante)== 0){
                par.setPuntosEquipoLocal(puntosLocal);
                par.setPuntosEquipoVisitante(puntosVisitante);
                    if(puntosLocal>puntosVisitante){
                    eq = new Datos_equipo(equiLocal,1,1,0,puntosLocal,puntosVisitante,2*1+0);
                    jornada.clasificacion.add(eq);
                    eq = new Datos_equipo(equiVisitante,1,0,1,puntosVisitante,puntosLocal,1);
                    jornada.clasificacion.add(eq);
                }else{
                        eq = new Datos_equipo(equiLocal,1,0,1,puntosLocal,puntosVisitante,1);
                        jornada.clasificacion.add(eq);
                        eq = new Datos_equipo(equiVisitante,1,1,0,puntosVisitante,puntosLocal,2*1+0);
                        jornada.clasificacion.add(eq);
                      }
                    }
             }   
    }

}
