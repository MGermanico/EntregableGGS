/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exec;

import Objetos.TrataImagenes;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev
 */
public class ExecPrueba {
    public static void main(String[] args) {
        try {
            TrataImagenes ti = new TrataImagenes("src/Imagenes/PRUEBA.pgm");
            ti.muestraPrueba();
            ti.girarDerecha();
            ti.muestraPrueba();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExecPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
