package code;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author lenadro
 */
public class PDFData {
    /*
    * /home/lenadro/Documentos/Libre Office/Horario 4to Cuatrimestre.pdf
    * /home/lenadro/Documentos/Libre Office/Libreria PanamaHitek.pdf
    * /home/lenadro/ArchivoSalida.pdf
     */
    //private static String[] archivosPrueba = {"", ""};
    
    private String fileName = "NOPE";
    private static final String MSG_FILE_PROCESING = "Archivo correctamente procesado.";
    private short DPI = 300;

    public static void main(String[] args) {
        
    }
    
    public PDFData(String fileName, short dpi){
        this.fileName = fileName;
        this.DPI = dpi;
    }

    // Juntar PDFs. OK
    public void meregePDFs(String[] files, String urlSaveFile) throws IOException {
        PDFMergerUtility merger = new PDFMergerUtility();
        MemoryUsageSetting memory = MemoryUsageSetting.setupMainMemoryOnly();
        
        for (String s : files) {merger.addSource(s);}// Agrega los PDFs

        merger.setDestinationFileName(urlSaveFile+"/" +fileName+ ".pdf");
        merger.mergeDocuments(memory);
        JOptionPane.showMessageDialog(null, MSG_FILE_PROCESING);
    }
    
    // Optener Metadatos del PDF. OK
    public void getMetadatos(File imputFile) throws IOException {
        PDDocument document = PDDocument.load(imputFile);

        PDDocumentInformation docInformation = document.getDocumentInformation();
        Set<String> metadataKeys = docInformation.getMetadataKeys();
        document.close();

        /* Test.
        for(String key : metadataKeys){
            String values = docInformation.getCustomMetadataValue(key);
            System.out.println(key+ ": " +values);
        }
         */
    }
    
    // Convertir un PDF a varias imagenes png. OK
    public void pdfToPng(File imputFile, String urlSave) throws IOException {
        PDDocument document = PDDocument.load(imputFile);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        
        // DPI minimo: 300. maximo: 1200
        
        for (short page = 0; page < document.getNumberOfPages(); page++) {
            BufferedImage img = pdfRenderer.renderImageWithDPI(page, DPI);
            File imgOutput = new File(urlSave+ "/" +fileName +page+ ".png");
            ImageIO.write(img, "png", imgOutput);
        }
        document.close();
        JOptionPane.showMessageDialog(null, MSG_FILE_PROCESING);
    }
    
    // Extraer todo el texto del documento PDF. OK
    public String getPdfText(File imputFile) throws IOException{
        PDDocument document = new PDDocument().load(imputFile);
        PDFTextStripper textExtractor = new PDFTextStripper();
        String textData = textExtractor.getText(document);
        
        document.close();
        JOptionPane.showMessageDialog(null, MSG_FILE_PROCESING);
        
        // Test.
        //System.out.println(textData);
        return textData;
    }
}// fin class
