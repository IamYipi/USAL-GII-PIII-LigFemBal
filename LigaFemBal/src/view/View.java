/*
 * Javier García Pechero 
 * GRADO INGENIERÍA INFORMÁTICA 
 * GRUPO: PA3
 */
package view;

import com.coti.tools.Esdia;
import static com.coti.tools.Esdia.readString_ne;
import controller.Controller;
import data.Datos_equipo;
import data.Equipo;
import data.Jornada;
import data.Jugadora;
import data.Partido;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import main.MainLigaFemBal;

/**
 *
 * @author javi
 */
public class View implements Serializable {
    
    public final static String textFileJornadasFolder = MainLigaFemBal.pathLigaFemBalFolder + File.separator + "datosjornadas.txt";
    public final static String textFileDatosEquiposFolder = MainLigaFemBal.pathLigaFemBalFolder + File.separator + "datosequipos.txt";
    public final static String textFileDatosJugadorasFolder = MainLigaFemBal.pathLigaFemBalFolder + File.separator + "jugadoras";
    public final static String textFileResulJornadas = MainLigaFemBal.pathLigaFemBalFolder + File.separator + "resul_jornadas";
    public final static String textFileFichSalida = MainLigaFemBal.pathLigaFemBalFolder + File.separator + "fichsalida";
    
    Controller c = new Controller();
    
    /*BINARY FILE*/
    //CARGA
    public boolean loadLigFemBal(String location) {
        return c.load(location);
    }
    //GUARDADO
    public boolean saveLigFemBal(String location) {
        return c.save(location);
    }
    /*MENU*/

    public void runMenu(String menu) throws IOException {
        String opc;
        String[] options = {"1","2","3","4","5","s"};
        boolean exit = false;
        do{
            System.out.println(menu);
            opc = Esdia.readString("%nIntroduzca una opcion: ",options);
            switch(opc){
                case "1" -> gestionTemporada();
                case "2" -> gestionJugadoras();
                case "3" -> gestionJornada();
                case "4" -> visualizarResultados();
                case "5" -> almacenamientoResultados();
                case "s" -> exit = Esdia.yesOrNo("%nEstas seguro que quieres salir del programa? :");
            }     
        }while(!exit);
        System.out.println("\nFinalizando el programa...");
    }
    
    /*OPCION GESTION TEMPORADA*/

    private void gestionTemporada(){
        boolean exit = false;
        String opc;
        String[] options = {"1","2","3","4","5"};
        do{
            System.out.printf("\nHAS ELEGIDO LA OPCION \"GESTION TEMPORADA\" ");
            System.out.println("\n==========================================="
                         + "\n          GESTION TEMPORADA"
                         + "\n==========================================="
                         + "\n1.Iniciar Temporada"
                         + "\n2.Cargar  Jornadas"
                         + "\n3.Cargar  Equipos"
                         + "\n4.Cargar  Jugadoras"
                         + "\n5.Volver"
                         + "\n=============================================");
            opc = Esdia.readString("%nIntroduzca una opcion",options);
            switch(opc){
            case "1" : this.iniciarTemporada(); break;
            case "2" : this.cargarJornadas(); break;
            case "3" : this.cargarEquipos(); break;
            case "4" : this.cargarJugadoras(); break;
            case "5" : exit=true; break;
            default  : System.out.println("%n[ERROR] Opcion incorrecta");
            }
        }while(!exit);
    }
   
    //Opcion 1
    
    private void iniciarTemporada() {
        String temporada = readString_ne("%nIntroduzca año de temporada: ");
        if(c.registerTemporada(temporada)){
            System.out.println("\nLa temporada se ha registrado correctamente");
            String c = Esdia.readString("Pulsa intro para continuar");
        }else{
            System.out.println("\nNo se ha podido registrar la temporada");
            String c = Esdia.readString("Pulsa intro para continuar");
        }
    }
    
    //Opcion 2
    
    private void cargarJornadas(){
        String temporada = c.getTemporada();
        if(temporada!=null){
         if(!c.cargarJornadas(textFileJornadasFolder)){
         System.err.printf("\n[ERROR] NO SE HA PODIDO CARGAR EL FICHERO DATOSJORNADAS.TXT");
        }else{
            System.out.println("\n[INFO] SE HA CARGADO CORRECTAMENTE EL FICHERO DATOSJORNADAS.TXT");
            String c = Esdia.readString("Pulsa intro para continuar");
        }
        }else{
            System.err.println("\n[ERROR] PRIMERO DEBES INICIALIZAR LA TEMPORADA [OPCION 1]");
            System.out.println("\n[ERROR] PRIMERO DEBES INICIALIZAR LA TEMPORADA [OPCION 1]");
            String c = Esdia.readString("Pulsa intro para continuar");
        }
    }
    
    //Opcion 3
    
    private void cargarEquipos() {
        List<Jornada> jornadas = c.getJornadas();
        if(!jornadas.isEmpty()){
            if(!c.cargarEquipos(textFileDatosEquiposFolder)){
            System.err.printf("\n[ERROR] NO SE HA PODIDO CARGAR EL FICHERO DATOSEQUIPOS.TXT");
        }else{
            System.out.println("\n[INFO] SE HA CARGADO CORRECTAMENTE EL FICHERO DATOSEQUIPOS.TXT");
            String c = Esdia.readString("Pulsa intro para continuar");
        }       
        }else{
            System.err.println("\n[ERROR] PRIMERO DEBES CARGAR LAS JORNADAS [OPCION 2]");
            System.out.println("\n[ERROR] PRIMERO DEBES CARGAR LAS JORNADAS [OPCION 2]");
            String c = Esdia.readString("Pulsa intro para continuar");
        }
    }

    //Opcion 4
    
     private void cargarJugadoras() {
         List <Equipo> equipos = c.getEquipo();
         if(!equipos.isEmpty()){
         if(!c.cargarJugadoras(textFileDatosJugadorasFolder)){
         System.err.printf("\n[ERROR] NO SE HA PODIDO CARGAR EL FICHERO DATOSJUGADORAS.TXT\n");
        }else{
         System.out.println("\n[INFO] SE HA CARGADO CORRECTAMENTE LAS JUGADORAS DE CADA EQUIPO");
         String c = Esdia.readString("Pulsa intro para continuar");
        }
         }else{
             System.err.println("\n[ERROR] PRIMERO DEBES CARGAR LOS EQUIPOS [OPCION 3]");
             System.out.println("\n[ERROR] PRIMERO DEBES CARGAR LOS EQUIPOS [OPCION 3]");
             String c = Esdia.readString("Pulsa intro para continuar");
         }
     }

    /*OPCION GESTION JUGADORAS*/
     
    private void gestionJugadoras() {
        boolean exit = false;
        String opc;
        String[] options = {"1","2","3","4"};
        do{
            System.out.printf("\nHAS ELEGIDO LA OPCION \"GESTION JUGADORAS\" ");
            System.out.println("\n==========================================="
                         + "\n          GESTION JUGADORAS"
                         + "\n==========================================="
                         + "\n1.Modificar Datos de una Jugadora"
                         + "\n2.Eliminar una Jugadora de un Equipo"
                         + "\n3.Añadir una jugadora a un equipo"
                         + "\n4.Volver"
                         + "\n=============================================");
            opc = Esdia.readString("%nIntroduzca una opcion",options);
            switch(opc){
            case "1" : this.modificarDatosDeUnaJugadora(); break;
            case "2" : this.eliminarJugadoraDeUnEquipo(); break;
            case "3" : this.añadirJugadoraEquipo(); break;
            case "4" : exit=true; break;
            default  : System.out.println("%n[ERROR] Opcion incorrecta");
            }
        }while(!exit);
    }
    
    //OPCION 1
    
     private void modificarDatosDeUnaJugadora() {
         if(this.printJugadoras()){
             List<String> jugadoras = c.getNombreJugadoras();
             
             int nom = Esdia.readInt("Introduzca el numero de la Jugadora a modificar:",1,jugadoras.size());
             String nombre = jugadoras.get(nom-1);
             List<Jugadora> allJugadoras = c.getJugadoras();
             String nombreEquipo = c.getNombreEquipoJugadora(nombre);
             if(allJugadoras!=null){
                 List<Jugadora> jugadoraEncontrada = new ArrayList<>();
                 List<Integer> posiciones = new ArrayList<>();
                 int pos = 0;
                 for(Jugadora as : allJugadoras){
                     if(nombre.compareTo(as.getNombre())== 0){
                         jugadoraEncontrada.add(as);
                         posiciones.add(pos);
                     }
                     pos++;
                 }
                 
                Jugadora j = c.modificarJugadora(jugadoraEncontrada.get(0));
                
                 if(j!=null){
                     System.out.println("\nLa jugadora se ha modificado correctamente");
                     allJugadoras.set(posiciones.get(0), j);
                     c.guardarJugadoraModificada(j,nombreEquipo);
                 }else{
                     System.out.println("\nNo se ha podido modificar la jugadora");
                 }
             }
         }
     }
     
     private boolean printJugadoras() {
     List<String> jugadoras = c.getNombreJugadoras();
     int i = 1;
     System.out.println("");
     if(jugadoras != null){
            if(!jugadoras.isEmpty()){
                for(String a : jugadoras){
                if(a!=null){
                    System.out.println(i + " " + a);
                    i++;
                    }
                }
            }else{
                System.out.println("\nNo hay jugadoras registradas");
            }
         }else{
             System.out.println("\nNo se ha podido obtener las jugadoras");
             return false;
         }
        return true;
     }

    //OPCION 2
     
    private void eliminarJugadoraDeUnEquipo() {
        if(this.printEquipos()){
            List<Equipo> equipos = c.getEquipo();
            Equipo e;
            int numEquipo = Esdia.readInt("Introduzca el numero del equipo de la jugadora que desea eliminar: ",1,equipos.size());
            e = equipos.get(numEquipo-1);
            String nombreEquipo = e.getNombre();
            List<Jugadora> jugadoras = e.getJugadoras();
            this.printJugadorasDeUnEquipos(jugadoras,nombreEquipo);
            int numJugadora = Esdia.readInt("Introduzca el numero de la jugadora que desea eliminar: ",1,jugadoras.size());
            Jugadora j = jugadoras.get(numJugadora-1);
            c.eliminarJugadora(j,nombreEquipo);
            System.out.println("\nSe ha eliminado la jugadora correctamente");       
        }else{
            System.out.println("\nCargue los datos primero en Gestion Temporada\n");
        }
    }
    
     private void printJugadorasDeUnEquipos(List<Jugadora> jugadoras,String nombreEquipo) {
         if(jugadoras!=null){
             System.out.println("");
             int n = 1;
             for(Jugadora a : jugadoras){
                 if(a!=null){
                     System.out.println(n + " " + a.getNombre());
                     n++;
                 }else{
                     System.out.println("\nNo hay jugadoras en el equipo" + nombreEquipo);
                 }
             }
             
         }else{
             System.out.println("\nNo hay jugadoras guardadas en el equipo");
         }
     }

    
    private boolean printEquipos() {
        List<Equipo> equipos = c.getEquipo();
        int i = 1;
        System.out.println("");
     if(!equipos.isEmpty()){
            if(!equipos.isEmpty()){
                for(Equipo a : equipos){
                if(a!=null){
                    System.out.println(i + " " + a.getNombre());
                    i++;
                    }
                }
            }else{
                System.out.println("\nNo hay equipos registrados");
            }
         }else{
             System.out.println("\nNo se ha podido obtener los equipos");
             System.err.printf("\nComprueba carga de equipos en gestion Temporada\n");
             return false;
         }
        return true;
    }
    


    //OPCION 3
    
    private void añadirJugadoraEquipo() {
        if(this.printEquipos()){
            List<Equipo> equipos = c.getEquipo();  
            int numEquipo = Esdia.readInt("Introduzca el numero del equipo de la jugadora que desea añadir: ",1,equipos.size());
            Equipo e;
            e = equipos.get(numEquipo-1);
            String nombreEquipo = e.getNombre();
            List<Jugadora> jugadoras = e.getJugadoras();
            if(c.addNewPlayer(jugadoras,nombreEquipo)){
                System.out.println("\nSe ha añadido correctamente");
            }else{
                System.out.println("\nNo se ha podido añadir la jugadora");
            }
        }else{
          System.out.println("\nCargue los datos primero en Gestion Temporada\n");
        }
    }
    
    /*OPCION GESTION JORNADA*/
    
    private void gestionJornada() {
         boolean exit = false;
        String opc;
        String[] options = {"1","2","3","4","5","6"};
        do{
            System.out.printf("\nHAS ELEGIDO LA OPCION \"GESTION JORNADA\" ");
            System.out.println("\n==========================================="
                         + "\n          GESTION JORNADA"
                         + "\n==========================================="
                         + "\n1.Leer los resultados de la Jornada"
                         + "\n2.Modificar Fecha de la Jornada"
                         + "\n3.Modificar Fecha u Hora de la Jornada"
                         + "\n4.Mostrar los Resultados de la Jornada"
                         + "\n5.Mostrar la Clasificacion de una Jornada"
                         + "\n6.Volver"
                         + "\n=============================================");
            opc = Esdia.readString("%nIntroduzca una opcion",options);
            switch(opc){
            case "1" : this.leerResultadosDeLaJornada(); break;
            case "2" : this.modificarFechaDeLaJornada(); break;
            case "3" : this.modificarFechauHoraDeLaJornada(); break;
            case "4" : this.mostrarLosResultadosDeLaJornada();break;
            case "5" : this.mostrarLaClasificacionDeUnaJornada();break;
            case "6" : exit = true; break;
            default  : System.out.println("%n[ERROR] Opcion incorrecta");
            }
        }while(!exit);
    }
    
    //OPCION 1

     private void leerResultadosDeLaJornada() {
         int first = 0;
         int i = Esdia.readInt("Introduzca el numero de la jornada que desea leer sus resultados: ",1,15);
         if(!c.cargarResultadosJornada(textFileResulJornadas,i,first)){
            System.err.printf("\n[ERROR] NO SE HA PODIDO CARGAR LOS RESULTADOS DE LA JORNADA");
        }else{
            first = 1;
            System.out.println("\n[INFO] SE HA CARGADO CORRECTAMENTE LA JORNADA NUMERO"+i);
        }
         
     }
     
     //OPCION 2

    private void modificarFechaDeLaJornada() {
        int i = Esdia.readInt("\nIntroduzca el numero de la jornada que desea cambiar la fecha: ",1,15);
        if(!c.modificarFechaJornada(i-1)){
            System.err.printf("\n[ERROR] NO SE HA PODIDO MODIFICAR LA FECHA DE LA JORNADA");
        }else{
            System.out.println("\n[INFO] SE HA CARGADO CORRECTAMENTE LA FECHA DE LA JORNADA NUMERO"+i);
        }
    }

    private void modificarFechauHoraDeLaJornada() {
        int i = Esdia.readInt("\nIntroduzca el numero de la jornada que desea cambiar la fecha: ",1,15);
        System.out.println("\n CAMBIAR FECHA U HORA"
                        +  "\n1.Fecha"
                        +  "\n2.Hora");
        int j = Esdia.readInt("Introduzca que opcion desea modificar",1,2); 
        if(this.printEquipos()){
        List<Equipo> equipos = c.getEquipo();
        Equipo e;
        int numEquipo = Esdia.readInt("Introduzca el numero del equipo del equipo que desea modificar su jornada: ",1,equipos.size());
        e = equipos.get(numEquipo-1);
        String s = e.getNombre();
        if(j == 1){
            if(!c.modificarFechaPartido(i-1,s)){
                System.err.printf("\n[ERROR] NO SE HA PODIDO MODIFICAR LA FECHA DE LA JORNADA");
            }else{
                System.out.println("\n[INFO] SE HA CARGADO CORRECTAMENTE LA FECHA DE LA JORNADA NUMERO"+i);
            }
        }else{
            if(!c.modificarHoraJornada(i-1,s)){
                System.err.printf("\n[ERROR] NO SE HA PODIDO MODIFICAR LA HORA DE LA JORNADA");
            }else{
                System.out.println("\n[INFO] SE HA CARGADO CORRECTAMENTE LA HORA DE LA JORNADA NUMERO"+i);
            }
        }
        
    }
}
    private void mostrarLosResultadosDeLaJornada() {
        int i = Esdia.readInt("\nIntroduzca el numero de la jornada que desea visualizar los resultados: ",1,15);
        List<Partido> partidos = new ArrayList<>();
        partidos = c.getResultadosJornada(i-1);
        if(partidos==null){
            System.err.printf("\n[ERROR] NO SE HA PODIDO OBTENER LOS RESULTADOS DE LA JORNADA"+i+"\n");
            System.out.println("\nCOMPRUEBA FICHEROS JORNADA");
        }else{
            System.out.printf("\nJORNADA: %d",i);
            System.out.println("\n=========================================================================================================");
            System.out.printf("|%-37s|%-37s|%-11s|%-15s|","EQUIPO LOCAL","EQUIPO VISITANTE","PUNTOSLOCAL","PUNTOSVISITANTE" );
            for(Partido p: partidos){
                System.out.printf("\n|%-37s|%-37s|%-11d|%-15d|",p.getEquipoLocal(),p.getEquipoVisitante(),p.getPuntosEquipoLocal(),p.getPuntosEquipoVisitante());
            }
            System.out.println("\n=========================================================================================================");
        }
        String f = Esdia.readString("\nPulse intro para continuar");
    }

    private void mostrarLaClasificacionDeUnaJornada() {
        int i = Esdia.readInt("\nIntroduzca el numero de la jornada que desea visualizar los resultados: ",1,15);
        List<Jornada>jornada = new ArrayList<>();
        jornada = c.getJornadas();
        if(!jornada.isEmpty()){
        Jornada j = jornada.get(i-1);
        if(j == null){
            System.err.printf("\n[ERROR] NO SE HA PODIDO OBTENER LA CLASIFICACION DE LA JORNADA "+i);
        }else{        
            List<Datos_equipo>  clasificacion = j.getClasificacion();
            if(clasificacion!=null){
            c.ordenarClasificacion(clasificacion);      
            int posicion = 1;
                System.out.printf("\n CLASIFICACION JORNADA: %d",i);
            System.out.printf("\n=================================================================================================================================");          
            System.out.printf("\n|%-10s|%-50s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|","PUESTO","EQUIPO","PJ","PG","PP","PF","PC","PTS" );
            for(Datos_equipo e : clasificacion){
                System.out.printf("\n|%-10d|%-50s|%-10d|%-10d|%-10d|%-10d|%-10d|%-10d|",posicion,e.getNombreEquipo(),e.getPj(),e.getPg(),e.getPp(),e.getPf(),e.getPc(),e.getPclasif());
                posicion++;
            }
            System.out.println("\n================================================================================================================================");          
            String f = Esdia.readString("\nPulse intro para continuar");
            }else{
                System.out.println("\nNo existe clasificacion para esta jornada, compruebe los archivos de carga");
                System.err.printf("\nCompruebe archivos de carga\n");
            }
        }
    }else{
            System.out.println("\n[ERROR] NO SE HA PODIDO OBTENER LA CLASIFICACION DE LA JORNADA "+i);
            System.err.printf("\n[ERROR] NO SE HA PODIDO OBTENER LA CLASIFICACION DE LA JORNADA "+i+" \n");
            System.err.printf("\n[ERROR] CARGUE PRIMERO LAS JORNADAS\n");
        }
    }    
    
    /*OPCION VISUALIZACION DE RESULTADOS (LISTADOS)*/
    
    private void visualizarResultados() {
        boolean exit = false;
        String opc;
        String[] options = {"1","2","3","4"};
        do{
            System.out.printf("\nHAS ELEGIDO LA OPCION \"VISUALIZACION DE RESULTADOS\" ");
            System.out.println("\n==========================================="
                         + "\n          VISUALIZACION DE RESULTADOS"
                         + "\n==========================================="
                         + "\n1.Jugadoras de un equipo"
                         + "\n2.Relacion de equipos"
                         + "\n3.Relacion de jugadoras"
                         + "\n4.Volver"
                         + "\n=============================================");
            opc = Esdia.readString("%nIntroduzca una opcion",options);
            switch(opc){
            case "1" : this.visualizarJugadorasDeUnEquipo(); break;
            case "2" : this.relacionDeEquipos(); break;
            case "3" : this.relacionDeJugadoras(); break;
            case "4" : exit = true; break;
            default  : System.out.println("%n[ERROR] Opcion incorrecta");
            }
        }while(!exit);   
    }
    
    //OPCION 1
    
    private void visualizarJugadorasDeUnEquipo() {
        if(this.printEquipos()){
            List<Equipo> equipos = c.getEquipo();       
            int numEquipo = Esdia.readInt("Introduzca el numero del equipo que desea visualizar sus jugadoras: ",1,equipos.size());
            Equipo e;
            e = equipos.get(numEquipo-1);
            List<Jugadora> jugadoras = e.getJugadoras();
            if(jugadoras == null){
                System.err.printf("\n[ERROR] NO SE HA PODIDO OBTENER LAS JUGADORAS DEL EQUIPO "+ e.getNombre() + "\n");
                System.err.printf("\nTienes que cargarlas en gestion temporada\n");
            }else{
            c.ordenarJugadoras(jugadoras);
            System.out.printf("\nJUGADORAS DEL " + e.getNombre().toUpperCase());   
            System.out.println("\n===================================================================================================================================================================================");          
            System.out.printf("|%-40s|%-20s|%-20s|%-20s|%-20s|%-55s|","NOMBRE","POSICION","ALTURA","DORSAL","NACION","NACIMIENTO" );
            System.out.printf("\n===================================================================================================================================================================================");          
            for(Jugadora j : jugadoras){
                System.out.printf("\n|%-40s|%-20s|%-20d|%-20d|%-20s|%-55s|",j.getNombre(),j.getPosicion(),j.getAltura(),j.getDorsal(),j.getNacionalidad(),j.getNacimiento());
               
            }
            System.out.println("\n===================================================================================================================================================================================");          
            String f = Esdia.readString("\nPulse intro para continuar");
            }        
        }else{
            System.out.println("\nPorfavor cargue los equipos en gestion Temporada\n");
            System.err.printf("\nPorfavor cargue los equipos en gestion Temporada\n");
        }
    }

    //OPCION 2
    
    private void relacionDeEquipos() {
        List<Equipo> equipos = c.getEquipo();
        if (!equipos.isEmpty()){
        c.ordenarEquipos(equipos);
        System.out.println("\n================================================================================================================================================================================================================================================================");          
        System.out.printf("|%-40s|%-10s|%-50s|%-50s|%-100s|","NOMBRE","TELEFONO","EMAIL","WEB","DIRECCION");
        System.out.printf("\n================================================================================================================================================================================================================================================================");          
        for(Equipo e : equipos){
              System.out.printf("\n|%-40s|%-10s|%-50s|%-50s|%-100s|",e.getNombre(),e.getTelefono(),e.getEmail(),e.getWeb(),e.getDireccion());
        }
         System.out.println("\n===============================================================================================================================================================================================================================================================");
         String f = Esdia.readString("\nPulse intro para continuar");
        }else{
            System.out.println("\nCOMPRUEBA LA CARGA DE LOS EQUIPOS EN GESTION TEMPORADA");
            System.err.printf("\n[ERROR]NO SE HAN PODIDO OBTENER LOS EQUIPOS\n");
        }
    }

    //OPCION 3      
    private void relacionDeJugadoras() {
        System.out.println("LISTADO DE JUGADORAS");
        String i = Esdia.readString_ne("\nIntroduzca la letra inicial del nombre:");
        List<Jugadora> jugadoras = c.getJugadoras();
        List<Jugadora> jugadorasOrdenadas = new ArrayList<>();
        if(!jugadoras.isEmpty()){
            List<Jugadora> jugadorasFiltradas = new ArrayList<>();
        for(Jugadora j : jugadoras){
            if(j.getNombre().startsWith(i.toUpperCase())){
                jugadorasFiltradas.add(j);
            }
        }      
        jugadorasOrdenadas = c.ordenarJugadorasFiltradas(jugadorasFiltradas);      
        System.out.println("\n======================================================================================================================================================================================");          
        System.out.printf("|%-40s|%-20s|%-20s|%-20s|%-20s|%-55s|","NOMBRE","POSICION","ALTURA","DORSAL","NACION","NACIMIENTO" );
        System.out.printf("\n======================================================================================================================================================================================");          
            for(Jugadora j : jugadorasOrdenadas){
                System.out.printf("\n|%-40s|%-20s|%-20d|%-20d|%-20s|%-55s|",j.getNombre(),j.getPosicion(),j.getAltura(),j.getDorsal(),j.getNacionalidad(),j.getNacimiento());    
            }
        System.out.println("\n======================================================================================================================================================================================");          
        String f = Esdia.readString("\nPulse intro para continuar");
        }else{
            System.out.println("\n[ERROR] NO EXISTEN JUGADORAS\n");
            System.err.printf("\n [ERROR] Cargue las jugadoras en GESTION TEMPORADA\n");
        }
    }

    
    
    /*OPCION ALMACENAMIENTO DE RESULTADOS*/
    
     private void almacenamientoResultados() {
         boolean exit = false;
        String opc;
        String[] options = {"1","2","3","4"};
        do{
            System.out.printf("\nHAS ELEGIDO LA OPCION \"ALMACENAMIENTO DE RESULTADOS\" ");
            System.out.println("\n==========================================="
                         + "\n          ALMACENAMIENTO RESULTADOS"
                         + "\n==========================================="
                         + "\n1.Jugadoras de un equipo"
                         + "\n2.Relacion de equipos"
                         + "\n3.Clasificacion de una Jornada"
                         + "\n4.Volver"
                         + "\n=============================================");
            opc = Esdia.readString("%nIntroduzca una opcion",options);
            switch(opc){
            case "1" : this.jugadorasDeUnEquipoFichEnc(); break;
            case "2" : this.relacionDeEquiposFichEnc(); break;
            case "3" : this.clasificacionDeUnaJornada(); break;
            case "4" : exit = true; break;
            default  : System.out.println("%n[ERROR] Opcion incorrecta");
            }
        }while(!exit);
         
     }

    private void jugadorasDeUnEquipoFichEnc() {
        if(this.printEquipos()){
        List<Equipo> equipos = c.getEquipo();       
        int numEquipo = Esdia.readInt("Introduzca el numero del equipo que desea exportar a archivo encolumnado sus jugadoras: ",1,equipos.size());
        Equipo e;
        e = equipos.get(numEquipo-1);
        List<Jugadora> jugadoras = e.getJugadoras();    
        try{
            c.exportJugadorasDeUnEquipoColumnFormat(textFileFichSalida,jugadoras,e.getNombre());
        }catch(FileNotFoundException ex){
            System.err.println("\n[ERROR] No se ha podido exportar el archivo de las jugadoras del equipo " + e.getNombre());
            System.err.println("\n[ERROR] Exception" + ex);
        }
        System.out.println("\n[INFO] El archivo del equipo " + e.getNombre() + " con sus jugadoras se ha exportado correctamente en la ruta" + textFileFichSalida);
    }else{
            System.out.println("\n[ERROR] CARGUE LAS JUGADORAS EN GESTION TEMPORADA\n");
            System.err.printf("\n[ERROR] CARGUE LAS JUGADORAS EN GESTION TEMPORADA\n");
        }
}

    private void relacionDeEquiposFichEnc() {
        List<Equipo> equipos = c.getEquipo();
        if(!equipos.isEmpty()){           
        try{
            c.exportRelacionEquipo(textFileFichSalida,equipos);
        }catch(FileNotFoundException ex){
            System.err.println("\n[ERROR] No se ha podido exportar el archivo de equipos");
            System.err.println("\n[ERROR] Exception" + ex);
        }
        System.out.println("\n[INFO] El archivo relacion de equipos se ha exportado correctamente en la ruta " + textFileFichSalida);
    }else{
            System.out.println("\n[ERROR] No se ha podido exportar la relacion de equipos");
            System.err.println("\n[ERROR] No se ha podido exportar la relacion de equipos\n");
        }
}
    

    private void clasificacionDeUnaJornada() {
        int i = Esdia.readInt("\nIntroduzca el numero de la jornada que desea visualizar los resultados: ",1,15);
        List<Jornada> jornada = c.getJornadas();
        if(!jornada.isEmpty()){
        Jornada j = jornada.get(i-1);
        if(j == null){
            System.err.printf("\n[ERROR] NO SE HA PODIDO OBTENER LA CLASIFICACION DE LA JORNADA"+i);
        }else{        
            List<Datos_equipo>  clasificacion = j.getClasificacion();
            if(clasificacion!=null){
            c.ordenarClasificacion(clasificacion);
            try{
                c.exportClasificacionJornadaHTML(textFileFichSalida,clasificacion,i);
                System.out.println("Se ha exportado la clasificacion de la jornada " + i + " al archivo HTML en la ruta "+textFileFichSalida);
            }catch(FileNotFoundException ex){
              System.err.println("\n[ERROR] No se ha podido exportar el archivo de clasificacion de la jornada " + i);
              System.err.println("\n[ERROR] Exception" + ex);
            }
            }else{
                System.out.println("\nNo existe clasificacion para esta jornada, compruebe los archivos de carga");
            }
        }     
        }else{
            System.out.println("\n[ERROR] CARGUE LAS JORNADAS EN GESTION JORNADAS");
            System.err.printf("\n[ERROR] CARGUE LAS JORNADAS EN GESTION JORNADAS\n");
        }
    }

    
   
}
