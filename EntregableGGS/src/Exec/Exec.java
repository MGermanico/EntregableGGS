/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exec;

import Objetos.TrataImagenes;
import Utils.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author dev
 */
public class Exec {
    public static void main(String[] args) {
        TrataImagenes ti;
        
        do {
            ti = seleccionaRuta();
        } while (ti == null);
        
        boolean repetir = true;
        boolean mostrar = true;
        String mostrarOcultar = "mostrar";
        
        do {
            utilsCreadorMenus.separador();
            if (mostrar) {
                ti.muestraPrueba();
            }
            String entrada = utilsCreadorMenus.creadorDeMenus("Seleccione una opcion:#"
                    + "Seleccionar otra imagen#"
                    + "Girar imagen derecha#"
                    + "Girar imagen izquierda#"
                    + "Flip vertical#"
                    + "Flip horizontal#"
                    + "Filtro negativo#"
                    + "Filtro de caja#"
                    + mostrarOcultar + " pixeles",
                    true,true);
            if (entrada.equals("1")) {
                seleccionaRuta();
            }else if (entrada.equals("2")) {
                ti.girarDerecha();
            }else if (entrada.equals("3")) {
                ti.girarIzquierda();
            }else if (entrada.equals("4")) {
                ti.flipVertical();
            }else if (entrada.equals("5")) {
                ti.flipHorizontal();
            }else if (entrada.equals("6")) {
                ti.negativo();
            }else if (entrada.equals("7")) {
                ti.filtroCaja();
            }else if (entrada.equals("8")) {
                if (mostrar) {
                    mostrar = false;
                    mostrarOcultar = "Mostrar";
                }else{
                    mostrar = true;
                    mostrarOcultar = "Ocultar";
                }
            } else if (entrada.equalsIgnoreCase("s")) {
                repetir = false;
            }
        }while(repetir);
    }
    public static TrataImagenes seleccionaRuta(){
        TrataImagenes ti;
        
        boolean introducirRuta = true;
        String entrada;
        String ruta = "";
        String imagenes;

        entrada = utilsCreadorMenus.creadorDeMenus("Seleccione una opcion de ruta:#"
            + "Escribir a mano#"
            + "Seleccionar rutas existentes",
                false, true);
        utilsCreadorMenus.separador();
        
        if (entrada.equals("1")) {
            entrada = utilsCreadorMenus.pregunta("\tIntroduce una ruta");
            ruta = entrada;  
        }else if (entrada.equals("2")) {
            imagenes = sacaImagenes();
            entrada = utilsCreadorMenus.creadorDeMenus("Seleccione una ruta:#"
                    + imagenes,
                    true, true);
            utilsCreadorMenus.separador();
            
            if (entrada.equalsIgnoreCase("S")) {
                introducirRuta = false;
            }else{
                ruta = "src/Imagenes/" + imagenes.split("#")[Integer.parseInt(entrada) - 1];
            }
        }
        if (introducirRuta) {
            try{
                ti = new TrataImagenes(ruta); 
            }catch(FileNotFoundException e){
                System.out.println("\tNo se ha encontrado el archivo deseado");
                ti = null;
            }
        }else{
            ti = null;
        }
        return ti;
    }
    public static String sacaImagenes(){
        File carpeta = new File("src/Imagenes");
        File[] imagenes = carpeta.listFiles();
        String imagenesStr = "";
        for (int i = 0; i < imagenes.length; i++) {
            imagenesStr += imagenes[i].getName() + "#";
        }
        return imagenesStr;
    }
}
