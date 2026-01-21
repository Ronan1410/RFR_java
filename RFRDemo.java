import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class RFRDemo {

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            System.out.println("Usage: java RFRDemo <numBasis>");
            return;
        }

        int N = Integer.parseInt(args[0]);

        BufferedImage input = ImageIO.read(new File("input.jpg"));

        double[][] coeffs = Projector.project(input, N);

        BufferedImage output = Renderer.render(
                input.getWidth(),
                input.getHeight(),
                coeffs
        );

        ImageIO.write(output, "PNG", new File("reconstructed.png"));

        System.out.println("Rendered using " + N + " basis functions.");
    }
}
