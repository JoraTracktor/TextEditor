package converter;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import javax.swing.*;

public class SVGConverter{
    public static ImageIcon convertToPNG(String iconName, float width, float height){
        BufferedImageTranscoder imageTranscoder = new BufferedImageTranscoder();
        imageTranscoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, width);
        imageTranscoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, height);

        TranscoderInput input = new TranscoderInput(iconName);

        try {
            imageTranscoder.transcode(input, null);
        } catch (TranscoderException e) {
            e.printStackTrace();
        }
        return new ImageIcon(imageTranscoder.getBufferedImage());
    }
}
