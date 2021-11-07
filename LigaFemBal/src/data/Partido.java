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
public class Partido implements Serializable{
    private String equipoLocal;
    private String equipoVisitante;
    private int puntosEquipoLocal;
    private int puntosEquipoVisitante;
    private String fechaPartido;
    private String horaPartido;

    public Partido(String equipoLocal, String equipoVisitante, int puntosEquipoLocal, int puntosEquipoVisitante, String fechaPartido, String horaPartido) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.puntosEquipoLocal = puntosEquipoLocal;
        this.puntosEquipoVisitante = puntosEquipoVisitante;
        this.fechaPartido = fechaPartido;
        this.horaPartido = horaPartido;
    }

    public String getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(String equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public String getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(String equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int getPuntosEquipoLocal() {
        return puntosEquipoLocal;
    }

    public void setPuntosEquipoLocal(int puntosEquipoLocal) {
        this.puntosEquipoLocal = puntosEquipoLocal;
    }

    public int getPuntosEquipoVisitante() {
        return puntosEquipoVisitante;
    }

    public void setPuntosEquipoVisitante(int puntosEquipoVisitante) {
        this.puntosEquipoVisitante = puntosEquipoVisitante;
    }

    public String getFechaPartido() {
        return fechaPartido;
    }

    public void setFechaPartido(String fechaPartido) {
        this.fechaPartido = fechaPartido;
    }

    public String getHoraPartido() {
        return horaPartido;
    }

    public void setHoraPartido(String horaPartido) {
        this.horaPartido = horaPartido;
    }
    
    
}
