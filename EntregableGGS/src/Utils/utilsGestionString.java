/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.util.Scanner;

/**
 *
 * @author dev
 */
public class utilsGestionString {
    public static String normalizaTamanyos(String str, int n){
        if (str.length() > n) {
            if (str.length() == n - 1) {
                str = str.substring(0, n - 1) + ".";
            }else if (str.length() == n - 2) {
                str = str.substring(0, n - 2) + "..";
            }else{
                str = str.substring(0, n - 3) + "...";
            }
        }
        while(str.length() < n){
            str += " ";
        }
        return str;
    }
    
    public static String normalizaTamanyos(String str, int n, boolean porAtras){
        if (porAtras) {
            str = normalizaTamanyos(str, n);
        }else{
            if (str.length() > n) {
                str = str.substring(str.length() - n);
            }
            while(str.length() < n){
                str = " " + str;
            }
        }
        return str;
    }
    
    public static double dejarNDecimales(double num, int n){
        return ((int)(num*(10*n)))/(10*n);
    }
    public static String dejarNDecimalesStr(double num, int n){
        return ((int)(num*(10*n)))/(10*n) + "";
    }
    
    public static String creadorDeMenus(String accionesStr){ //Solo las preguntas
        return creadorDeMenus(accionesStr, true, "", -1);
    }
    public static String creadorDeMenus(String accionesStr, String posAPreguntar){ // preguntas + P.Seguridad
        return creadorDeMenus(accionesStr, true, posAPreguntar, -1);
    }
    public static String creadorDeMenus(String accionesStr, boolean salir){//preguntas + salir
        return creadorDeMenus(accionesStr, salir, "", -1);
    }
    public static String creadorDeMenus(String accionesStr, boolean salir, String posAPreguntar){//preguntas + salir + P.Seguridad
        return creadorDeMenus(accionesStr, salir, posAPreguntar, -1);
    }
    public static String creadorDeMenus(String accionesStr, int posAct, String posAPreguntar){//preguntas + P.Seguridad + posicion actual
        return creadorDeMenus(accionesStr, true, posAPreguntar , posAct);
    }
    public static String creadorDeMenus(String accionesStr, int posAct){//preguntas + + posicion actual
        return creadorDeMenus(accionesStr, true, "" , posAct);
    }
    public static String creadorDeMenus(String accionesStr, boolean salir, int posActual){//preguntas + + posicion actual
        return creadorDeMenus(accionesStr, salir, "" , posActual);
    }
    public static String creadorDeMenus(String accionesStr, boolean salir, String posAPreguntar, int posActual){
        Scanner sc = new Scanner(System.in);
        String[] acciones = accionesStr.split("#");
        String entrada;
        String mensaje = "";
        String asterisco = "";
        mensaje += "\n\n\t" + acciones[0] + "\n\n";
        for (int i = 1; i < acciones.length; i++) {
            if (i == posActual) {
                asterisco = "*";
            }else{
                asterisco = "";
            }
            mensaje += "\t\t(" + i + ") - " + acciones[i] + "." + asterisco + "\n";
        }
        if (salir) {
            mensaje +=  "\t\t(S) - Salir.\n";
        }
        boolean repetir;
        do {
            System.out.println(mensaje);
            entrada = sc.nextLine();
            try{
                repetir = !(
                    entrada.equalsIgnoreCase("s") || 
                    (Integer.parseInt(entrada) < acciones.length && Integer.parseInt(entrada) > 0)
                );
                if (repetir) {
                    System.out.println("VALOR NO PERMITIDO");
                }
            }catch(NumberFormatException e){
                System.out.println("VALOR NO PERMITIDO");
                repetir = true;
            }catch(Exception e){
                System.out.println(e.toString());
                repetir = true;
            }
            if (!entrada.equalsIgnoreCase("S")) {
                repetir = preguntaDeSeguridad(posAPreguntar, entrada, acciones);
            }
        } while (repetir);
        return entrada;
    }
    
    public static boolean preguntaDeSeguridad(String posAPreguntar, String entrada, String[] acciones){
        boolean repetir = false;
        if (!posAPreguntar.isEmpty()) {
            String[] posPreguntasConSeguro = posAPreguntar.split(" ");
            String respuestaDeSeguridad;
            int nReturn = Integer.parseInt(entrada);
            for (int i = 0; i < posPreguntasConSeguro.length; i++) {
                if (posPreguntasConSeguro[i].equals(entrada)) {
                    respuestaDeSeguridad = creadorDeMenus(
                            "\t¿ ESTAS SEGURO DE \"" + acciones[nReturn] + "\" ?#"
                            + "SI#"
                            + "NO", false
                    );
                    if (respuestaDeSeguridad.equals("2")) {
                        repetir = true;
                    }
                }
            }
        }
        return repetir;
    }
    public static String pregunta(String str){
        Scanner sc = new Scanner(System.in);
        System.out.print(str + ":\n\t->");
        return sc.nextLine();
    }
}
