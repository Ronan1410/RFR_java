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

        long startTime = System.nanoTime();

        BufferedImage input = ImageIO.read(new File("input.jpg"));

        double[][] coeffs = Projector.project(input, N);

        BufferedImage output = Renderer.render(
                input.getWidth(),
                input.getHeight(),
                coeffs
        );

        ImageIO.write(output, "PNG", new File("reconstructed_with_" + N + "numBasis.png"));

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;

        System.out.println("Rendered using " + N + " basis functions.");
        System.out.println("Total time: " + duration + " ms");
    }
}