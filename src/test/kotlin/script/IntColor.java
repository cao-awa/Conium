package script;

public class IntColor {
    public static void main(String[] args) {
        int rgb = toInt(
                123,
                100,
                55
        );

        int r = (rgb & 0xff0000) >> 16;
        int g = (rgb & 0xff00) >> 8;
        int b = (rgb & 0xff);

        System.out.println(r);
        System.out.println(g);
        System.out.println(b);
    }

    public static int toInt(int r, int g, int b) {
        return (0xFF << 24) |
                (r << 16) |
                (g << 8) |
                (b);
    }
}
