import java.awt.image.BufferedImage;

public class Projector {

    public static double[][] project(BufferedImage img, int N) {

        int W = img.getWidth();
        int H = img.getHeight();

        double[][] coeffs = new double[N][3];

        double weight = 4.0 * Math.PI / (W * H);

        for (int i = 0; i < N; i++) {

            double ar = 0, ag = 0, ab = 0;

            for (int y = 0; y < H; y++) {
                double theta = Math.PI * y / H;

                for (int x = 0; x < W; x++) {
                    double phi = 2.0 * Math.PI * x / W;

                    int rgb = img.getRGB(x, y);

                    double r = ((rgb >> 16) & 0xFF) / 255.0;
                    double g = ((rgb >> 8) & 0xFF) / 255.0;
                    double b = (rgb & 0xFF) / 255.0;

                    double basis = Basis.eval(i, theta, phi);

                    ar += r * basis * weight;
                    ag += g * basis * weight;
                    ab += b * basis * weight;
                }
            }

            coeffs[i][0] = ar;
            coeffs[i][1] = ag;
            coeffs[i][2] = ab;
        }

        return coeffs;
    }
}
