/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package code;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

/**
 *
 * @author lenadro
 */
public class PruebaResolucion {
    public static void main(String[] args) {
        try {
            pdfToPng(new File("/home/lenadro/Documentos/Libre Office/Libreria PanamaHitek.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(PruebaResolucion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // Convertir un PDF a varias imagenes png. 
    public static void pdfToPng(File imputFile) throws IOException {
        PDDocument document = PDDocument.load(imputFile);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        final short DPI = 1200;
        
        //for (short page = 0; page < document.getNumberOfPages(); page++) {
            BufferedImage img = pdfRenderer.renderImageWithDPI(0, DPI);
            File imgOutput = new File("/home/lenadro/PruebaPDF/PruebaCalidad_1200 DPI.png");
            //File imgOutput = new File(urlSave+ fileName +page+ ".png");
            ImageIO.write(img, "png", imgOutput);
    //}
        document.close();
        //JOptionPane.showMessageDialog(null, "Si Funciona");
    }
}
