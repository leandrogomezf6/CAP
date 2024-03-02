package code;

import be.tarsos.dsp.io.TarsosDSPAudioFormat;
import be.tarsos.dsp.io.TarsosDSPAudioInputStream;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import java.io.File;

/**
 *
 * @author lenadro
 */
public class AudioConverter {

    static File inputSound = new File("/home/lenadro/Descargas/AudioParaPrueba.wav");
    static File outputSound = new File("/home/lenadro/audioPruebaBien.mp3");

    public static void main(String[] args) {
        wav_to_mp3(inputSound, outputSound, "Hola");
    }

    public static void wav_to_mp3(File input, File output, String name) {
        
        TarsosDSPAudioInputStream audioInput = 
        (TarsosDSPAudioInputStream) AudioDispatcherFactory.fromPipe
        ( inputSound.getAbsolutePath(), 44100, 1024, 0);
        
        TarsosDSPAudioFormat audioFormat = audioInput.getFormat();
        
    }
}
