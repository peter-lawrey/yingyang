import java.awt.Color;

public class Theme {
    private Color colorA;  // color for left side tiles
    private Color colorB;  // color for right side tiles

    public static final Theme CLASSIC = new Theme(Color.BLACK, Color.WHITE);
    public static final Theme PASTEL = new Theme(new Color(255, 179, 186), new Color(186, 225, 255));
    public static final Theme NEON = new Theme(new Color(57, 255, 20), new Color(255, 20, 147));

    public Theme(Color colorA, Color colorB) {
        this.colorA = colorA;
        this.colorB = colorB;
    }

    public Color getColorA() {
        return colorA;
    }

    public Color getColorB() {
        return colorB;
    }
}
