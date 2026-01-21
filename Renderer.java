import java.awt.image.BufferedImage;

public class Renderer {

    public static BufferedImage render(int W, int H, double[][] coeffs) {

        int N = coeffs.length;
        BufferedImage out = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < H; y++) {
            double theta = Math.PI * y / H;

            for (int x = 0; x < W; x++) {
                double phi = 2.0 * Math.PI * x / W;

                double r = 0, g = 0, b = 0;

                for (int i = 0; i < N; i++) {
                    double phi_i = Basis.eval(i, theta, phi);

                    r += coeffs[i][0] * phi_i;
                    g += coeffs[i][1] * phi_i;
                    b += coeffs[i][2] * phi_i;
                }

                int rgb =
                        ((int)(clamp(r) * 255) << 16) |
                        ((int)(clamp(g) * 255) << 8) |
                        (int)(clamp(b) * 255);

                out.setRGB(x, y, rgb);
            }
        }

        return out;
    }

    private static double clamp(double v) {
        return Math.max(0.0, Math.min(1.0, v));
    }
}
