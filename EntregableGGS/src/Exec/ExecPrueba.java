/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exec;

import Objetos.TrataImagenes;
import Utils.utilsCreadorMenus;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev
 */
public class ExecPrueba {
    public static void main(String[] args) {
//        try {
//            TrataImagenes ti = new TrataImagenes("src/Imagenes/gato.pgm");
//            ti.guardar();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(ExecPrueba.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException e){
//            
//        }

        utilsCreadorMenus.creadorDeMenus("Elige una opcion:#"
                + "Cagar#"
                + "Mear#"
                + "Peerse");
    }
}
