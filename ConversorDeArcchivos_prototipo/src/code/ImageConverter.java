package code;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author lenadro
 */
public class ImageConverter {
    
    private String imgName = "";
    private String imgFormat = "";
    private File imputImage;
    private File outputImage;

    private final String MSG_ERR_NAME_FORMAT = 
            "Por favor elija un nombre y formato para la imagen.";
    private final String MSG_ERR_SOURCES = 
            "Por favor elija una imagen y guardela en una ruta segura.";
    private final int MSG_TYPE = 0;
    private boolean isError = false;

    // Constructor.
    public ImageConverter(String imgName, String imgFormat) {

        if (imgName.isEmpty() || imgFormat.isEmpty()) {
            JOptionPane.showMessageDialog
        (null, MSG_ERR_NAME_FORMAT, "Error", MSG_TYPE);
            isError = true;
        } else {
            this.imgName = imgName;
            this.imgFormat = imgFormat;
            isError = false;
        }
    }// fin construtor.

    public void addSources(String imputImage, String outputImage) {
        
        if (imputImage.isEmpty() || outputImage.isEmpty()) {
            JOptionPane.showMessageDialog
        (null, MSG_ERR_SOURCES, "Error", MSG_TYPE);
            isError = true;
        } else {
            this.imputImage = new File(imputImage);
            this.outputImage = new File
            (outputImage + "/" + imgName + "." + imgFormat);
            isError = false;
        }

    }

    // Metodos.
    public void imgConverter() throws IOException {
        if (!isError) {
            // Cargar imagen
            BufferedImage bufer = ImageIO.read(imputImage);

            // Convierte la imagen y la guarda.
            ImageIO.write(bufer, imgFormat, outputImage);
        }
    }
}// fin class
