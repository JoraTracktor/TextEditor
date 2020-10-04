package converter;

import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;

import java.awt.image.BufferedImage;

public class BufferedImageTranscoder extends ImageTranscoder {

    private BufferedImage image = null;

    @Override
    public BufferedImage createImage(int weight, int height) {
        BufferedImage bufferedImage = new BufferedImage(weight, height, BufferedImage.TYPE_INT_ARGB);
        return bufferedImage;
    }

    @Override
    public void writeImage(BufferedImage bufferedImage, TranscoderOutput transcoderOutput) {
        this.image = bufferedImage;
    }

    public BufferedImage getBufferedImage() {
        return image;
    }
}
