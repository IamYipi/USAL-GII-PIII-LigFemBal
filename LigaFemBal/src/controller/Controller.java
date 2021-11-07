/*
 * Javier García Pechero
 * GRADO INGENIERÍA INFORMÁTICA 
 * GRUPO: PA3
 */
package controller;

import data.Datos_equipo;
import data.Equipo;
import data.Jornada;
import data.Jugadora;
import data.LigaFem;
import data.Partido;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;


/**
 *
 * @author javi
 */
public class Controller implements Serializable {
    
    LigaFem m;
    
    boolean ligaFemBalReady;
    
    /*CONSTRUCTOR*/
    public Controller(){
        this.m = null;
        this.ligaFemBalReady = false;
    }
    
    /*BINARY FILE*/
    //Cargar
    public boolean load(String location) {
        File binaryLigFemBal = new File(location + File.separator + "ligFemBal.bin");
        if(!binaryLigFemBal.exists()){
            m = new LigaFem();
            ligaFemBalReady = true;
            return false;
        }else{
            try{
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(binaryLigFemBal)));
                m = (LigaFem) ois.readObject();
                ois.close();
            }catch(IOException | ClassNotFoundException e){
                System.err.println("ERROR: NO SE HA PODIDO CARGAR EL FICHERO BINARIO");
                System.err.println("EXCEPTION"+e);
                m = new LigaFem();
                ligaFemBalReady = true;
                return false;
            }
        }
        ligaFemBalReady = true;
        return true;
    }
    //Guardar
    public boolean save(String location) {
        File binaryLigFemBal = new File(location + File.separator + "ligFemBal.bin");
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(binaryLigFemBal)));
            oos.writeObject(m);
            oos.close();
        }catch(IOException e){
            System.err.println("[ERROR] NO SE HA PODIDO GUARDAR EL FICHERO BINARIO");
            System.err.println("Exception"+e);
            return false;
        }
        return true;
    }

    /*OPCION 1 GESTION DE TEMPORADAS --- OPCION 2 CARGAR JORNADAS*/
    //CARGA DE JORNADAS
    public boolean cargarJornadas(String location) {
        return m.cargarJornadas(location);
    }
    
    // OPCION 1 GESTION DE TEMPORADAS -- OPCION 3 CARGAR EQUIPOS
    //CARGAR EQUIPOS
    
     public boolean cargarEquipos(String location) {
         return m.cargarEquipos(location);
     }
     
    // OPCION 1 GESTION DE TEMPORADAS -- OPCION 3 CARGAR JUGADORAS
     
    public boolean cargarJugadoras(String location) {
        return m.cargarJugadoras(location);
    }
   
    //Registrar temporada

    public boolean registerTemporada(String temporada) {
        return m.registerTemporada(temporada);
    }
    
    // OPCION 2 GESTION DE JUGADORAS -- OPCION 1 MODIFICAR JUGADORA

    public List<Equipo> getEquipo() {
        return m.getEquipos();
    }
    
    public void setEquipos(List<Equipo> equipos) {
        m.setEquipos(equipos);
    }
    
    public List<Jornada> getJornadas() {
        return m.getJornadas();
    }

    public void setJornadas(List<Jornada> jornadas) {
        m.setJornadas(jornadas);
    }

   public String getTemporada() {
       return m.getTemporada();
    }

    public void setTemporada(String temporada) {
        m.setTemporada(temporada);
    }

    public List<String> getNombreJugadoras() {
        return m.getNombreJugadoras();
    }

    public List<Jugadora> getJugadoras() {
        return m.getJugadoras();
    }

    public Jugadora modificarJugadora(Jugadora jugadora) {
      return m.modificarJugadora(jugadora);
    }

    public String getNombreEquipoJugadora(String nombre) {
        return m.getNombreEquipoJugadora(nombre);
    }

    public void guardarJugadoraModificada(Jugadora j, String nombreEquipo) {
        m.guardarJugadoraModificada(j,nombreEquipo);
    }    
    
    //OPCION 2 GESTION DE JUGADORAS - OPCION 2 ELIMINAR JUGADORA
    
    public void eliminarJugadora(Jugadora j,String nombreEquipo) {
        m.eliminarJugadora(j,nombreEquipo);
    }
    
    //OPCION 2 GESTION DE JUGADORAS -- OPCION 3 AÑADIR JUGADORA

    public boolean addNewPlayer(List<Jugadora> jugadoras, String nombreEquipo) {
        return m.addNewPlayer(jugadoras,nombreEquipo);    
    }
 
    /*GESTION JORNADA*/
 
    public boolean cargarResultadosJornada(String location, int i,int first) {
        return m.cargarResultadosJornada(location,i,first);
    }

    public boolean modificarFechaJornada(int i) {
        return m.modificarFechaJornada(i);
    }

    public boolean modificarFechaPartido(int numJornada, String nombreEquipo) {
        return m.modificarFechaPartido(numJornada,nombreEquipo);
    }

    public boolean modificarHoraJornada(int numJornada, String nombreEquipo) {
        return m.modificarHoraPartido(numJornada,nombreEquipo);
    }

    public List<Partido> getResultadosJornada(int numJornada) {
        return m.getResultadosJornada(numJornada);
    }
    
    /*VISUALIZAR RESULTADOS*/

    public void ordenarClasificacion(List<Datos_equipo> clasificacion) {
        m.ordenarClasificacion(clasificacion);
    }

    public void ordenarJugadoras(List<Jugadora> jugadoras) {
        m.ordenarJugadoras(jugadoras);
    }

    public void ordenarEquipos(List<Equipo> equipos) {
        m.ordenarEquipos(equipos);
    }

    public List<Jugadora> ordenarJugadorasFiltradas(List<Jugadora> jugadorasFiltradas) {
       return m.ordenarJugadorasFiltradas(jugadorasFiltradas);
    }

    /*ALMACENAR RESULTADOS*/
    
    public void exportJugadorasDeUnEquipoColumnFormat(String location, List<Jugadora> jugadoras, String nombreEquipo) throws FileNotFoundException {
         m.exportarJugadorasDeUnEquipoColumnFormat(location,jugadoras,nombreEquipo);
    }

    public void exportRelacionEquipo(String location, List<Equipo> equipos) throws FileNotFoundException {
           m.exportarRelacionEquipo(location,equipos);
    }

    public void exportClasificacionJornadaHTML(String location, List<Datos_equipo> clasificacion, int i) throws FileNotFoundException {
        m.exportClasificacionJornadaHTML(location,clasificacion,i);
    }

    


    
}
