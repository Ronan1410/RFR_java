public class Basis {

    public static double eval(int i, double theta, double phi) {

        int l = (int)Math.floor(Math.sqrt(i));
        int m = i - l * l - l;

        double x = Math.cos(theta);

        double P = legendre(l, Math.abs(m), x);
        double N = normalization(l, m);

        if (m >= 0)
            return N * P * Math.cos(m * phi);
        else
            return N * P * Math.sin(-m * phi);
    }

    private static double normalization(int l, int m) {
        double num = (2.0 * l + 1.0) * factorial(l - Math.abs(m));
        double den = 4.0 * Math.PI * factorial(l + Math.abs(m));
        return Math.sqrt(num / den);
    }

    private static double legendre(int l, int m, double x) {

        if (l == m) {
            double p = 1.0;
            for (int i = 1; i <= m; i++)
                p *= -(2 * i - 1) * Math.sqrt(1 - x * x);
            return p;
        }

        if (l == m + 1)
            return x * (2 * m + 1) * legendre(m, m, x);

        double p1 = legendre(l - 1, m, x);
        double p2 = legendre(l - 2, m, x);

        return ((2 * l - 1) * x * p1 - (l + m - 1) * p2) / (l - m);
    }

    private static double factorial(int n) {
        double f = 1.0;
        for (int i = 2; i <= n; i++) f *= i;
        return f;
    }
}
