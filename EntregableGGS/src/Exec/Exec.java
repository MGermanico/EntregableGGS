/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exec;

import Objetos.TrataImagenes;
import java.io.FileNotFoundException;

/**
 *
 * @author dev
 */
public class Exec {
    public static void main(String[] args) {
        try{
            TrataImagenes ti = new TrataImagenes("src/Imagenes/imagen.pgm");
            ti.muestraPrueba();
            ti.girarIzquierda();
            System.out.println("---");
            ti.muestraPrueba();
        }catch(FileNotFoundException e){
            System.out.println("NO SE ENCONTRÓ");
        }
    }
}
