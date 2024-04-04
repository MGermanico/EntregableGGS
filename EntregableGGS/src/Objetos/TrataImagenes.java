/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author dev
 */
public class TrataImagenes {
    private String ruta;
    private int nFilas;
    private int nColumnas;
    private int[][] imagenArr;

    public TrataImagenes(String ruta) throws FileNotFoundException{
        File file = new File(ruta);
        try(Scanner scFile = new Scanner(file)){
            
        }
    }
    
    public void pgmToArray(){
        
    }
    
    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getnFilas() {
        return nFilas;
    }

    public void setnFilas(int nFilas) {
        this.nFilas = nFilas;
    }

    public int getnColumnas() {
        return nColumnas;
    }

    public void setnColumnas(int nColumnas) {
        this.nColumnas = nColumnas;
    }

    public int[][] getImagenArr() {
        return imagenArr;
    }

    public void setImagenArr(int[][] imagenArr) {
        this.imagenArr = imagenArr;
    }
    
    
}
