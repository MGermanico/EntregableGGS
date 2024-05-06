/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.io.File;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author migue
 */
public class ImagenVentana extends JFrame{
    private JPanel contentPane;
    private File imagen;
    private int x;
    private int y;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
//                    File img = new File("./src/Imagenes/perro.pgm");
                    File img = new File("./src/Imagenes/perrete.pgm");
//                    File img = new File("./src/Imagenes/gato.pgm");
//                    File img = new File("./src/Imagenes/imagen.pgm");
                    ImagenVentana frame = new ImagenVentana(img, 600);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ImagenVentana(File imagen, int pos) throws Exception {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        try(Scanner fileScanner = new Scanner(imagen)){
            fileScanner.nextLine();
            fileScanner.nextLine();
            String[] medidasTmp = fileScanner.nextLine().split(" ");
            x = Integer.parseInt(medidasTmp[0]);
            y = Integer.parseInt(medidasTmp[1]);
        }catch(Exception e){
            throw e;
        }
        setBounds(pos,0,y,x);
        this.imagen = imagen;
    }
    
    public void paint (Graphics g)
    {
        super.paint(g);
        int n = 1;
//        g.fillRect(8, 33, 10, 10);
        try(Scanner imgScanner = new Scanner(imagen)){
            System.out.println(imgScanner.nextLine());
            System.out.println(imgScanner.nextLine());
            System.out.println(imgScanner.nextLine());
            System.out.println(imgScanner.nextInt());
            
            int colorAct = 0;
            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    colorAct = imgScanner.nextInt();
                    
                    g.setColor(new Color(colorAct, colorAct, colorAct));
                    g.fillRect((i*n),(j*n), n, n);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    g.setColor(new Color(255, 0, 0));
                    g.fillRect(8 + (i*n), 33 + (j*n), 10, 10);
                }
            }
        }
    }
    
}
