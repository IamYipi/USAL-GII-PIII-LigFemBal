/*
 * Javier García Pechero
 * GRADO INGENIERÍA INFORMÁTICA 
 * GRUPO: PA3
 */
package main;


import com.coti.tools.Rutas;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import view.View;

/**
 *
 * @author javi
 */
public class MainLigaFemBal implements Serializable{
    
    //Ruta de el archivo binarios
    public final static String pathLigaFemBalFolder =(Rutas.pathToFolderOnDesktop("LigFemBal").toString());
    public final static String binaryLigFemBalFolder = pathLigaFemBalFolder + File.separator + "binarios";

    public static void main(String[] args) throws IOException {
        View v = new View();
        //Primero intentamos cargar el binario
        if(!v.loadLigFemBal(binaryLigFemBalFolder)){
            System.err.println("[ERROR] No se pudo cargar el fichero binario");
        }else{
            System.out.println("[INFO]ARCHIVO BINARIO CARGADO");
        }
        //run Menu
        v.runMenu("\n==============================="
                + "\n          LIGAFEMBAL"
                + "\n==============================="
                + "\n1.Gestion de Temporada"
                + "\n2.Gestion de Jugadoras"
                + "\n3.Gestion de Jornada"
                + "\n4.Visualizacion de Resultados"
                + "\n5.Almacenamiento de Resultados"
                + "\nS.Salir"
                + "\n================================");      
        
        if(v.saveLigFemBal(binaryLigFemBalFolder)){
            System.out.println("[INFO] SE HA GUARDADO EL ARCHIVO BINARIO CORRECTAMENTE");
        }else{
            System.err.println("[ERROR] NO SE HA PODIDO GUARDAR EL ARCHIVO BINARIO");
        }
    }
    
    
    
}
