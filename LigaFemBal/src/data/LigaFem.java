/*
 * Javier García Pechero 
 * GRADO INGENIERÍA INFORMÁTICA 
 * GRUPO: PA3
 */
package data;

import com.coti.tools.Esdia;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author javi
 */
public class LigaFem implements Serializable{
    private String temporada;
    private List<Jornada> jornadas;
    private List<Equipo> equipos;
    
    /*CONSTRUCTOR*/

    public LigaFem() {
        this.temporada = null;
        this.jornadas = new ArrayList<>();
        this.equipos = new ArrayList<>();
    }

    
    /*GETTERS AND SETTERS*/
    
    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public List<Jornada> getJornadas() {
        return jornadas;
    }

    public void setJornadas(List<Jornada> jornadas) {
        this.jornadas = jornadas;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }
    
    //INICIAR TEMPORADA

    public boolean registerTemporada(String temporada) {
           if(temporada!=null){
               this.temporada = temporada;
               return true;
           }else{
               return false;
           }
    }    
    
    //CARGAR JORNADAS
    
    public boolean cargarJornadas(String location) {
        this.jornadas = new ArrayList<>();
        return (this.cargarJornadastxt(location));
    }

    private boolean cargarJornadastxt(String location) {
        File datosJornadastxt = new File(location);
        List<String> importedDatosJornadas;
        if(!datosJornadastxt.exists()){
            return false;
        }else{
            try{
               importedDatosJornadas = Files.readAllLines(datosJornadastxt.toPath());
            }catch (IOException e){
                importedDatosJornadas = null;
                System.err.println("[ERROR] NO SE HA PODIDO IMPORTAR LAS JORNADAS");
                System.err.println("Exception"+e);
                return false;
            }
        }  
        if(importedDatosJornadas != null){
            for (String s : importedDatosJornadas){
                if(s!=null){
                    Jornada newJornada = Jornada.instanceFromString(s);
                    if(newJornada != null ){
                        this.jornadas.add(newJornada);
                    }
                }
            }
        }
        return true;
    }

    public boolean cargarEquipos(String location) {
        this.equipos = new ArrayList<>();
        File datosEquipostxt = new File(location);
        List<String> importedDatosEquipos;
        if(!datosEquipostxt.exists()){
            return false;
        }else{
            try{
                importedDatosEquipos = Files.readAllLines(datosEquipostxt.toPath());
            }catch(IOException e){
                importedDatosEquipos = null;
                System.err.println("[ERROR] NO SE HA PODIDO IMPORTAR LOS EQUIPOS");
                System.err.println("Exception"+e);
                return false;
            }
        }
        if(importedDatosEquipos!= null){
            for (String s : importedDatosEquipos){
                if(s!=null){
                    Equipo newEquipo = Equipo.instanceFromString(s);
                    if(newEquipo != null ){
                        this.equipos.add(newEquipo);
                    }
                }
            }
        }
        return true;
    }
    
    

    public boolean cargarJugadoras(String location) {
        if(this.equipos.isEmpty()){
            System.out.println("\nNO SE HAN CARGADO LOS EQUIPOS");
            System.err.println("\n[ERROR] NO SE PUEDEN CARGAR LAS JUGADORAS SIN ANTES CARGAR LOS EQUIPOS");
            return false;
        }
        List<String>importedDatosJugadoras;
        List<String> nombreEquipos = getNombreEquipos(); 
        List<String> cadenaDireccion = getDireccion();
        List<String> numeroTelefono = getTelefono();
        List<String> cadenaWeb = getWeb();
        List<String> cadenaEmail = getEmail();
        List<Jugadora> jugadoras = new ArrayList<>(); 
        for(int i= 0; i<16;i++){
        List<Jugadora> jugadora = new ArrayList<>();
        String datosJugadorastxt =(location + File.separator + nombreEquipos.get(i).toUpperCase() + ".txt");
        File   datosJugadorasTxT = new File(datosJugadorastxt);
        if(!datosJugadorasTxT.exists()){
            return false;
        }else{
            try{
        importedDatosJugadoras = Files.readAllLines(datosJugadorasTxT.toPath());
            }catch(IOException e){
                importedDatosJugadoras = null;
                System.err.println("[ERROR] NO SE HA PODIDO IMPORTAR LAS JUGADORAS");
                System.err.println("Exception"+e);
                return false;
            }
            }
        if(importedDatosJugadoras!= null){
            for (String s : importedDatosJugadoras){
                if(s!=null){                  
                    jugadoras = Equipo.cargarJugadoras(s,jugadora);                   
                }
            }         
            Equipo newEquipo = new Equipo(nombreEquipos.get(i),cadenaDireccion.get(i),numeroTelefono.get(i),cadenaWeb.get(i),cadenaEmail.get(i), jugadoras);
            jugadoras = new ArrayList();
            if(newEquipo != null ){
                this.equipos.set(i,newEquipo);
            }
        }   
      }
        return true;
    }

    private List<String> getNombreEquipos() {
        List<Equipo> equ = getEquipos();
        List <String> equi = new ArrayList<>();
        for (Equipo e : equ){
            equi.add(e.getNombre().replace('á','a').replace('í','i'));
        }
        return equi;
    }

    private List<String> getDireccion() {
        List<Equipo> equ = getEquipos();
        List <String> equi = new ArrayList<>();
        for (Equipo e : equ){
            equi.add(e.getDireccion());
        }
        return equi;
    }

    private List<String> getTelefono() {
        List<Equipo> equ = getEquipos();
        List <String> equi = new ArrayList<>();
        for (Equipo e : equ){
            equi.add(e.getTelefono());
        }
        return equi;
    }

    private List<String> getWeb() {
    List<Equipo> equ = getEquipos();
        List <String> equi = new ArrayList<>();
        for (Equipo e : equ){
            equi.add(e.getWeb());
        }
        return equi;
    }

    private List<String> getEmail() {
        List<Equipo> equ = getEquipos();
        List <String> equi = new ArrayList<>();
        for (Equipo e : equ){
            equi.add(e.getEmail());
        }
        return equi;
    }
    
    //GESTION JUGADORAS
   public List<String> getNombreJugadoras(){
       List<Equipo> equ = getEquipos();
       List<Jugadora> j = new ArrayList();
       List<String> x = new ArrayList();
       for (Equipo e : equ){
           j = (e.getJugadoras());
           for(Jugadora a: j){
               x.add(a.getNombre());
           }
       }
       return x;
   }
   
   public List<Jugadora> getJugadoras(){   
       List<Equipo> equipo = getEquipos();
       List<Jugadora> allJugadoras = new ArrayList<>();
            for(Equipo e : equipo){
                 allJugadoras.addAll(e.getJugadoras());
            }
       return allJugadoras;
   }

    public Jugadora modificarJugadora(Jugadora jugadora) {
      return Jugadora.modificarCopiaJugadora(jugadora);
    }

    public String getNombreEquipoJugadora(String nombre) {
        String nombreEquipo = null;
        List<Equipo> equipo = getEquipos();
        List<Jugadora> allJugadoras = new ArrayList<>();
        for(Equipo e : equipo){
            allJugadoras = e.getJugadoras();
            for(Jugadora a : allJugadoras){
                if(nombre.compareTo(a.getNombre())==0){
                    nombreEquipo=e.getNombre();
                }
            }
        }
        return nombreEquipo;
    }

    public void guardarJugadoraModificada(Jugadora j, String nombreEquipo) {
        List<Equipo> equipo = getEquipos();
        List<Jugadora> jugadoras = new ArrayList<>();
        int i = 0;
        for(Equipo e : equipo){
            if(nombreEquipo.compareTo(e.getNombre())==0){
                jugadoras = e.getJugadoras();
                for(Jugadora jug : jugadoras){
                    if(jug.getNombre().compareTo(j.getNombre())==0){
                        jugadoras.set(i,j);
                        e.setJugadoras(jugadoras);
                    }
                    i++;
                }               
            }
        }
        
    }

    public void eliminarJugadora(Jugadora j,String nombreEquipo) {
        List<Equipo> equipo = getEquipos();
        List<Jugadora> jugadoras = new ArrayList<>();
        int i = 0;
        int posicion = 0;
        for (Equipo e : equipo){
            if(nombreEquipo.compareTo(e.getNombre())== 0){
                jugadoras = e.getJugadoras();
                try{
                for(Jugadora jug : jugadoras){
                    if(jug.getNombre().compareTo(j.getNombre())== 0){
                           posicion = i;            
                    }
                    i++;
                }
                jugadoras.remove(posicion);
                e.setJugadoras(jugadoras);
            }catch(NumberFormatException x){
                    System.out.println("Error "+x);
            }
          
        }
        }
    }

    public boolean addNewPlayer(List<Jugadora> jugadoras, String nombreEquipo) {
        if(jugadoras!=null){
        Jugadora j;
        j = Jugadora.crearNuevaJugadora();
        jugadoras.add(j);
        List<Equipo> equipo = getEquipos();
        int i = 0; 
        for (Equipo e : equipo){
            if(nombreEquipo.compareTo(e.getNombre())==0){
                e.setJugadoras(jugadoras);
            }
            i++;
        }
            return true;
        }else{
            return false;
        }
    }
    
    

    public boolean cargarResultadosJornada(String location, int numJornada, int first) {
       String [] num = {"uno","dos","tres","cuatro","cinco","seis","siete","ocho","nueve","diez","once","doce","trece","catorce","quince"};
       List<Equipo> equipos = getEquipos();
       List<Jornada> jornada = getJornadas();
       List<Datos_equipo> clasificacion = new ArrayList<>();
       int out = 0;
       if(jornada.isEmpty() || equipos.isEmpty()){
           return false;
       }
       for(Jornada j : jornada){
           clasificacion = j.getClasificacion();
           if(null == clasificacion){
               first = 0;
           }else{
               first = 1;    
               out = first;
           }
       }
     for(int i = 0; i<numJornada;i++){
        String ruta = location + File.separator + num[i] + ".txt";
        File datosResultadoJornadatxt = new File(ruta);
        List<String> importedDatosJornadas;
        
        if(!datosResultadoJornadatxt.exists()){
            return false;
        }else{
            try{
               importedDatosJornadas = Files.readAllLines(datosResultadoJornadatxt.toPath());
            }catch (IOException e){
                importedDatosJornadas = null;
                System.err.println("[ERROR] NO SE HA PODIDO IMPORTAR LAS JORNADAS");
                System.err.println("Exception"+e);
                return false;
            }
        }     
        
        out = 1;

        if(importedDatosJornadas != null){
            for (String s : importedDatosJornadas){
                if(s!=null){
                  if(i==0){
                      Jornada.guardarDatosPrimeraJornada(s,i,jornada.get(i),out);
                      out = 0;
                  }else{
                      Jornada.guardarDatosJornada(s,numJornada,jornada.get(i),jornada.get(i-1),out,equipos);
                      out = 0;
                  }
                                     
                }
            }             
        }
     }
        return true;
    }

    public boolean modificarFechaJornada(int numJornada) {
        List<Jornada> jornada = getJornadas();
        Jornada j = jornada.get(numJornada);
        String f;
        System.out.printf("\nFecha actual: %s",j.getFechaJornada());
        if(Esdia.yesOrNo("\nDesea modificar la fecha actual?: ")){
          f = Esdia.readString_ne("\nIntroduzca la nueva fecha: ");
          j.setFechaJornada(f);
            return true;
        }else{
            return false;
        }
    }

    public boolean modificarFechaPartido(int numJornada, String nombreEquipo) {
        List<Jornada> jornadass = getJornadas();
        Jornada jornada = jornadass.get(numJornada);
        List<Partido> partidos = jornada.getPartidos();
        String f;
        for(Partido p : partidos){
            if(p.getEquipoLocal().compareTo(nombreEquipo.toUpperCase())==0 || p.getEquipoVisitante().compareTo(nombreEquipo.toUpperCase())==0){
                System.out.printf("\nLa fecha actual del partido es: %s ",p.getFechaPartido());
                if(Esdia.yesOrNo("Desea cambiar la fecha?: ")){
                    f = Esdia.readString_ne("\nIntroduzca la nueva fecha: ");
                    p.setFechaPartido(f);
                    return true;
                }else{
                    System.out.println("\nNo se ha modificado la fecha del partido");
                    return true;
                }
            }
        }
        System.out.println("\nEl equipo " + nombreEquipo + " no ha disputado ningun partido en esta jornada");
       return false;
    }

    public boolean modificarHoraPartido(int numJornada, String nombreEquipo) {
        List<Jornada> jornadass = getJornadas();
        Jornada jornada = jornadass.get(numJornada);
        List<Partido> partidos = jornada.getPartidos();
        String h;
        for(Partido p : partidos){
            if(p.getEquipoLocal().compareTo(nombreEquipo.toUpperCase())==0 || p.getEquipoVisitante().compareTo(nombreEquipo.toUpperCase())==0){
                System.out.printf("\nLa hora actual del partido es: %s ",p.getHoraPartido());
                if(Esdia.yesOrNo("Desea cambiar la hora?: ")){
                    h = Esdia.readString_ne("\nIntroduzca la nueva hora: ");
                    p.setHoraPartido(h);
                    return true;
                }else{
                    System.out.println("No se ha modificado la hora del partido");
                    return true;
                }
            }
        }
        System.out.println("\nEl equipo " + nombreEquipo + " no ha disputado ningun partido en esta jornada");
       return false;
    }

    public List<Partido> getResultadosJornada(int numJornada) {
        List<Jornada> jornadass = getJornadas();
        if(!jornadass.isEmpty()){
        Jornada jornada = jornadass.get(numJornada);
        List<Partido> partidos = jornada.getPartidos();
        return partidos;
        }else{
          return null;
        }
        
    }

    public void ordenarClasificacion(List<Datos_equipo> clasificacion) {
        Comparator<Datos_equipo> pclasf = Comparator.comparing(Datos_equipo::getPclasif);  
        Collections.sort(clasificacion, pclasf.reversed());
    }

    public void ordenarJugadoras(List<Jugadora> jugadoras) {
        Comparator<Jugadora> posiAlt = Comparator.comparing(Jugadora::getPosicion).thenComparing(Jugadora::getAltura);
        Collections.sort(jugadoras,posiAlt.reversed());
    }

    public void ordenarEquipos(List<Equipo> equipos) {
        Comparator<Equipo> equi = Comparator.comparing(Equipo::getTelefono);
        Collections.sort(equipos,equi);
    }

    public List<Jugadora> ordenarJugadorasFiltradas(List<Jugadora> jugadorasFiltradas) {
        String delimiter1 = " ";
        String delimiter2 = "/";
        String nacimiento = null;
        List<Jugadora> jugadorasOrdenadas = new ArrayList<>();
        List<Jugadora> jugadoras = new ArrayList<>();
        Jugadora jug;
        for(Jugadora j : jugadorasFiltradas){
            String[] data = j.getNacimiento().split(delimiter1);
            String[] fecha = data[0].split(delimiter2);
            nacimiento = fecha[2] + delimiter2 + fecha[1] + delimiter2 + fecha[0];           
            jug = new Jugadora(j.getNombre(),j.getPosicion(),j.getDorsal(),nacimiento,j.getNacionalidad(),j.getAltura());
            jugadorasOrdenadas.add(jug);
        }
        
        Comparator<Jugadora> nac = Comparator.comparing(Jugadora::getNacimiento);
        Collections.sort(jugadorasOrdenadas,nac.reversed());
        for(Jugadora je : jugadorasOrdenadas){
            for(Jugadora ja : jugadorasFiltradas){
                if(je.getNombre()==ja.getNombre()){
                    jugadoras.add(ja);
                }
            }
        }     
        return jugadoras;
    }
    
    /*ALMACENAR RESULTADOS*/

    public void exportarJugadorasDeUnEquipoColumnFormat(String location, List<Jugadora> jugadoras, String nombreEquipo) throws FileNotFoundException {
         File exitJugadorasFile = new File(location + File.separator + "jugadoras"+ nombreEquipo  +".enc");
        try (PrintWriter pw = new PrintWriter(exitJugadorasFile)) {
            int first = 0;
            for(Jugadora j : jugadoras){
                if(j!=null){
                    pw.append(j.toColString(nombreEquipo,first));
                    first = 1;
                }
            }
        }
    }


    public void exportarRelacionEquipo(String location, List<Equipo> equipos) throws FileNotFoundException {
        File exitEquiposFile = new File(location + File.separator + "equipos.enc");
        PrintWriter pw = new PrintWriter(exitEquiposFile); 
        int first = 0;
        for(Equipo e : equipos){
            if(e!=null){
                pw.append(e.toColString(first));
                first = 1;
            }
        }
        pw.close();
    }

    public void exportClasificacionJornadaHTML(String location, List<Datos_equipo> clasificacion, int i) throws FileNotFoundException {
        File exitClasificacionJornada = new File(location + File.separator + "fich_html_" + i + ".html" );
        int posicion = 1;
        PrintWriter pw = new PrintWriter(exitClasificacionJornada); 
        pw.printf("<!DOCTYPE html>%n<HTML>%n<HEAD><meta charset=\"UTF-8\"><H1>CLASIFICACION JORNADA %d</H1></HEAD>%n<BODY>%n",i);
        pw.printf("<TABLE BORDER=5>\n");
        pw.printf("<TBODY STYLE=\"background: rgba(164,171,255,10);\">\n");
        pw.printf("<TR>"+"<TH>POSICION</TH>"+"<TH>EQUIPO</TH>"+"<TH>PJ</TH>"+"<TH>PG</TH>"+"<TH>PP</TH>"+"<TH>PF</TH>"+"<TH>PC</TH>"+"<TH>PTS</TH>"+"</TR></TBODY>");
        for(Datos_equipo e : clasificacion){
            if(e!=null){
                pw.printf("%s\n",e.asHTMLTableRow(posicion));
                posicion++;
            }
        }
        pw.printf("</TABLE>");
        pw.printf("</BODY>\n<HTML>");
        pw.close();
    }

   
}
