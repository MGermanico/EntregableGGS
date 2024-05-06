/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import GUI.ImagenVentana;
import Utils.utilsGestionString;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev
 */
public class TrataImagenes {
    private String ruta;
    private String rutaImagenSinGuardar = "./src/GUI/imagen-sinGuardar.pgm";
    private String mensaje;
    private int blanco;
    private int nFilas;
    private int nColumnas;
    private int[][] imagenArr;
    private ImagenVentana frame = null;
    public TrataImagenes(){
    }
    public TrataImagenes(String ruta) throws FileNotFoundException{
        File file = new File(ruta);
        try(Scanner scFile = new Scanner(file)){
            if (!scFile.nextLine().equals("P2")) {
//                throw new ();
            }
            this.mensaje = scFile.nextLine();
            this.nColumnas = scFile.nextInt();
            this.nFilas = scFile.nextInt();
            this.blanco = scFile.nextInt();
            this.ruta = ruta;
            
            this.imagenArr = new int[nFilas][nColumnas];
            for (int i = 0; i < this.nFilas; i++) {
                for (int j = 0; j < this.nColumnas; j++) {
                    this.imagenArr[i][j] = scFile.nextInt();
                }
            }
        }
        normalizar();
        this.blanco = 255;
    }
    
    public void aumentarResolucion() throws Exception{
        int[][] imagenArrTmp = new int[this.nFilas*2][this.nColumnas*2];
        imagenArrTmp = intercalaCeros(imagenArrTmp);
        this.nFilas *= 2;
        this.nColumnas *= 2;
        this.imagenArr = imagenArrTmp;
        try {
            this.grabar();
        } catch (Exception ex) {
            throw ex;
        }
    }
    private int[][] intercalaCeros(int[][] imagenArrTmp){
        System.out.println("FILAS: " + this.nFilas);
        System.out.println("CLMNS: " + this.nColumnas);
        for (int iRapida = 0, iLenta = 0; iLenta < this.nFilas; iRapida += 2, iLenta++) {
            for (int jRapida = 0, jLenta = 0; jLenta < this.nColumnas; jRapida += 2, jLenta++) {
                System.out.println("jRapida " + jRapida + ", jLenta " + jLenta + "\t   \tiR" + iRapida + ", iL" + iLenta);
                imagenArrTmp[iRapida][jRapida] = this.imagenArr[iLenta][jLenta];
                System.out.println("");
            }
        }
        return imagenArrTmp;
    }
    private boolean casillasPatron(int i, int j){
        return (i % 2 != 0 && j % 2 == 0)||(i % 2 == 0 && j % 2 != 0);
    }
    private int sumaAlrededores(int iOrig, int jOrig, int[][] img) {
        System.out.println("-----:");
        int sumaAct = 0;
        int c = 0;
        for (int i = iOrig - 1; i <= iOrig + 1; i++) {
            if (i != iOrig) {
                try{
                    sumaAct = img[i][jOrig];
                    c++;
                    System.out.println(i + "," + jOrig + " : " + img[i][jOrig]);
                }catch(IndexOutOfBoundsException e){
                }
            }
        }
        System.out.println("---");
        for (int j = jOrig - 1; j <= jOrig + 2; j++) {
            if (j != iOrig) {
                try{
                    sumaAct = img[iOrig][j];
                    c++;
                    System.out.println(iOrig + "," + j + " : " + img[iOrig][j]);
                }catch(IndexOutOfBoundsException e){
                }
            }
        }
        return sumaAct/c;
    }
    
    public File[] getImagenesFiles() throws Exception{
        File[] rutas;
        File carpetaImagenes = new File("src/Imagenes/");
        rutas = carpetaImagenes.listFiles();
        return rutas;
    }
    public void muestraImagen(int pos) throws Exception{
        File img = new File(this.rutaImagenSinGuardar);
        try {
            this.grabar();
            
            frame = new ImagenVentana(img, pos);
        } catch (Exception ex) {
            throw ex;
        }
        frame.setVisible(true);
    }
    public void cierraImagen(){
        frame.dispose();
    }
    public void muestraPrueba(){
        String pixelActualNormalizado;
        System.out.println("RUTA: " + this.ruta);
        for (int i = 0; i < this.nFilas; i++) {
            for (int j = 0; j < this.nColumnas; j++) {
                pixelActualNormalizado = utilsGestionString.normalizaTamanyos(Integer.toString(this.imagenArr[i][j]), 3);
                System.out.print(pixelActualNormalizado + " ");
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
    public void girarIzquierda(){
        int[][] imagenArrTmp = new int[this.nColumnas][this.nFilas];
        int nFilasTmp = this.nFilas;
        this.nFilas = this.nColumnas;
        this.nColumnas = nFilasTmp;
        for (int filas = 0; filas < this.nFilas; filas++) {
            for (int columnas = this.nColumnas-1, columnasTmp = 0; columnas >= 0; columnas--, columnasTmp++) {
//                System.out.println("c,f : " + filas + ", " + columnas + " -> " + this.imagenArr[columnas][filas]);
                imagenArrTmp[filas][columnasTmp] = this.imagenArr[columnas][filas];
            }
        }
        this.imagenArr = imagenArrTmp;
    }
    public void girarDerecha(){
        int[][] imagenArrTmp = new int[this.nColumnas][this.nFilas];
        int nFilasTmp = this.nFilas;
        this.nFilas = this.nColumnas;
        this.nColumnas = nFilasTmp;
        for (int filas = this.nFilas-1, filasTmp = 0; filas >= 0; filas--, filasTmp++) {
            for (int columnas = 0; columnas < this.nColumnas; columnas++) {
                imagenArrTmp[filasTmp][columnas] = this.imagenArr[columnas][filas];
            }
        }
        this.imagenArr = imagenArrTmp;
    }
    public void flipHorizontal(){
        int[][] imagenArrTmp = new int[this.nFilas][this.nColumnas];
        for (int filas = 0; filas < this.nFilas; filas++) {
            for (int columnas = this.nColumnas-1, columnasTmp = 0; columnas >= 0; columnas--, columnasTmp++) {
                imagenArrTmp[filas][columnasTmp] = this.imagenArr[filas][columnas];
            }
        }
        this.imagenArr = imagenArrTmp;
    }
    public void flipVertical(){
        int[][] imagenArrTmp = new int[this.nFilas][this.nColumnas];
        for (int filas = this.nFilas-1, filasTmp = 0; filas >= 0; filas--, filasTmp++) {
            for (int columnas = 0; columnas < this.nColumnas; columnas++) {
                imagenArrTmp[filasTmp][columnas] = this.imagenArr[filas][columnas];
            }
        }
        this.imagenArr = imagenArrTmp;
    }
    
    public void filtroCaja(){
        int[][] imagenArrTmp = new int[this.nFilas][this.nColumnas];
        for (int i = 0; i < this.nFilas; i++) {
            for (int j = 0; j < this.nColumnas; j++) {
//                System.out.println("\t\t\t\t:" + i + " , " + j);
                imagenArrTmp[i][j] = mediaAlrededores(i, j);
            }
        }
        this.imagenArr = imagenArrTmp;
    }
    
    public int mediaAlrededores(int fila, int columna){
        int contCasillas = 0;
        int posFila;
        int posColumna;
        int sumaTot = 0;
        for (int i = -1; i <= 1; i++) {
            posFila = fila + i;
            for (int j = -1; j <= 1; j++) {
                posColumna = columna + j;
                if((posFila >= 0 && posFila < this.nFilas) && (posColumna >= 0 && posColumna < this.nColumnas)){
                    sumaTot += this.imagenArr[posFila][posColumna];
                    contCasillas++;
                }
            }
        }
        return (int)Math.round(sumaTot*1.0/contCasillas);
    }
    
    public void grabar()throws Exception{
        File file = new File(this.rutaImagenSinGuardar);
        FileWriter fw = new FileWriter(file, false);
        
        fw.write("P2\n" + this.getMensaje() + "\n" + this.nColumnas + " " + this.nFilas + "\n" + this.blanco + "\n");
        for (int i = 0; i < this.nFilas; i++) {
            for (int j = 0; j < this.nColumnas; j++) {
                fw.write(this.imagenArr[i][j] + " ");
            }
            fw.write("\n");
        }
        fw.close();
    }
    
    public void guardar() throws IOException {
        File file = new File(this.ruta);
        FileWriter fw = new FileWriter(file, false);
        
        fw.write("P2\n" + this.getMensaje() + "\n" + this.nColumnas + " " + this.nFilas + "\n" + this.blanco + "\n");
        for (int i = 0; i < this.nFilas; i++) {
            for (int j = 0; j < this.nColumnas; j++) {
                fw.write(this.imagenArr[i][j] + " ");
            }
            fw.write("\n");
        }
        fw.close();
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
