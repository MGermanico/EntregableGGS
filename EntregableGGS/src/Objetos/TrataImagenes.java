/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import Utils.utilsGestionString;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author dev
 */
public class TrataImagenes {
    private String ruta;
    private int blanco;
    private int nFilas;
    private int nColumnas;
    private int[][] imagenArr;

    public TrataImagenes(String ruta) throws FileNotFoundException{
        File file = new File(ruta);
        try(Scanner scFile = new Scanner(file)){
            scFile.nextLine();
            scFile.nextLine();
            this.nColumnas = scFile.nextInt();
            this.nFilas = scFile.nextInt();
            this.blanco = scFile.nextInt();
            
            this.imagenArr = new int[nFilas][nColumnas];
            for (int i = 0; i < this.nFilas; i++) {
                for (int j = 0; j < this.nColumnas; j++) {
                    this.imagenArr[i][j] = scFile.nextInt();
                }
            }
        }
        normalizar();
    }
    
    public void muestraPrueba(){
        for (int i = 0; i < this.nFilas; i++) {
            for (int j = 0; j < this.nColumnas; j++) {
                System.out.print(utilsGestionString.normalizaTamanyos(Integer.toString(this.imagenArr[i][j]), 3) + " ");
            }
            System.out.println();
        }
    }
    
    public void normalizar(){
        for (int i = 0; i < this.nFilas; i++) {
            for (int j = 0; j < this.nColumnas; j++) {
                this.imagenArr[i][j] = Math.round(this.imagenArr[i][j]*255/this.blanco);
            }
        }
    }
    
    public void negativo(){
        for (int i = 0; i < this.nFilas; i++) {
            for (int j = 0; j < this.nColumnas; j++) {
                this.imagenArr[i][j] = this.blanco - this.imagenArr[i][j];
            }
        }
    }
    public void girarDerecha(){
        int[][] imagenArrTmp = new int[this.nColumnas][this.nFilas];
        int nFilasTmp = this.nFilas;
        this.nFilas = this.nColumnas;
        this.nColumnas = nFilasTmp;
        for (int i = 0; i < this.nFilas; i++) {
            for (int j = this.nColumnas-1, jTmp = 0; j > 0; j--, jTmp++) {
                imagenArrTmp[i][jTmp] = this.imagenArr[j][i];
            }
        }
        this.imagenArr = imagenArrTmp;
    }
    public void girarIzquierda(){
        int[][] imagenArrTmp = new int[this.nColumnas][this.nFilas];
        int nFilasTmp = this.nFilas;
        this.nFilas = this.nColumnas;
        this.nColumnas = nFilasTmp;
        for (int i = this.nFilas, iTmp = 0; i > 0; i--, iTmp++) {
            for (int j = 0; j > this.nColumnas; j--) {
                imagenArrTmp[iTmp][j] = this.imagenArr[j][i];
            }
        }
        this.imagenArr = imagenArrTmp;
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

    public int getBlanco() {
        return blanco;
    }

    public void setBlanco(int blanco) {
        this.blanco = blanco;
    }
    
    
}
