/*
 * Javier García Pechero
 * GRADO INGENIERÍA INFORMÁTICA 
 * GRUPO: PA3
 */
package data;

import java.io.Serializable;

/**
 *
 * @author javi
 */
public class Datos_equipo implements Serializable{

    private String nombreEquipo; /*Nombre equipo*/
    private int pj; /*Partidos jugados*/
    private int pg; /*Partidos ganados*/
    private int pp; /*Puntos perdidos*/
    private int pf; /*Puntos puntos a favor*/
    private int pc; /*Puntos clasificacion*/
    private int pclasif; /*Puntos clasificacion*/ //2*PG + PP

     /*CONSTRUCTOR*/
    
    public Datos_equipo(String nombreEquipo, int pj, int pg, int pp, int pf, int pc, int pclasif) {
        this.nombreEquipo = nombreEquipo;
        this.pj = pj;
        this.pg = pg;
        this.pp = pp;
        this.pf = pf;
        this.pc = pc;
        this.pclasif = pclasif;
    }
    
    /*GETTERS AND SETTERS*/
        
    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public int getPj() {
        return pj;
    }

    public void setPj(int pj) {
        this.pj = pj;
    }

    public int getPg() {
        return pg;
    }

    public void setPg(int pg) {
        this.pg = pg;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public int getPf() {
        return pf;
    }

    public void setPf(int pf) {
        this.pf = pf;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getPclasif() {
        return pclasif;
    }

    public void setPclasif(int pclasif) {
        this.pclasif = pclasif;
    }

    public String asHTMLTableRow(int posicion) {
       StringBuilder sb = new StringBuilder();
       sb.append(String.format("<TR>"+"<TD>&nbsp%d&nbsp</TD>"+"<TD>%s&nbsp</TD>"+"<TD>%d&nbsp</TD>"+"<TD>%d&nbsp</TD>"+"<TD>%d&nbsp</TD>"+"<TD>%d&nbsp</TD>"+"<TD>%d&nbsp</TD>"+"<TD>%d&nbsp</TD>"+"</TR>",posicion,this.nombreEquipo,this.pj,this.pg,this.pp,this.pf,this.pc,this.pclasif));    
       return sb.toString();
        
    }
}
