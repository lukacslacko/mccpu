package cpu.render;

import cpu.Location;
import org.bukkit.Material;

public class SVG {
    private final StringBuilder svg = new StringBuilder();

    private int minX = Integer.MAX_VALUE;
    private int minY = Integer.MAX_VALUE;
    private int maxX = Integer.MIN_VALUE;
    private int maxY = Integer.MIN_VALUE;

    public int getGridSize() {
        return gridSize;
    }

    public int getLayerSizeX() {
        return layerSizeX;
    }

    public int getLayerSizeY() {
        return layerSizeY;
    }

    private int gridSize = 30;
    private int layerSizeX = 10;
    private int layerSizeY = 8;
    private int blockStrokeWidth = 1;

    private String style;

    public SVG(int gridSize, int layerSizeX, int layerSizeY, int blockStrokeWidth, float opacity) {
        this.gridSize = gridSize;
        this.layerSizeX = layerSizeX;
        this.layerSizeY = layerSizeY;
        this.blockStrokeWidth = blockStrokeWidth;
        this.style = "fill-opacity:" + opacity + ";stroke-opacity:" + opacity;
    }

    public String getSvg() {
        StringBuilder svgBuilder = new StringBuilder("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"" + (maxX - minX) + "px\" height=\"" + (maxY - minY) + "px\">");
        svgBuilder.append("<defs>");
        svgBuilder.append("<linearGradient id=\"lamp\" x1=\"0%\" y1=\"0%\" x2=\"100%\" y2=\"0%\">");
        svgBuilder.append("<stop offset=\"0%\" style=\"stop-color:yellow\"/>");
        svgBuilder.append("<stop offset=\"100%\" style=\"stop-color:red\"/>");
        svgBuilder.append("</linearGradient>");
        svgBuilder.append("</defs>");
        svgBuilder.append("<g transform=\"translate(").append(-minX).append(",").append(-minY).append(")\">");
        svgBuilder.append(svg.toString());
        svgBuilder.append("</g></svg>");
        return svgBuilder.toString();
    }

    private int projX(Location location) {
        return location.getX() * gridSize - location.getY() * layerSizeX;
    }

    private int projY(Location location) {
        return location.getZ() * gridSize - location.getY() * layerSizeY;
    }

    public void append(Location location, String piece) {
        svg.append(piece);
        svg.append("\n");

        minX = Math.min(minX, projX(location) - 2 * gridSize);
        minY = Math.min(minY, projY(location) - 2 * gridSize);
        maxX = Math.max(maxX, projX(location) + 2 * gridSize);
        maxY = Math.max(maxY, projY(location) + 2 * gridSize);
    }

    private String polygon(float[][] points, String color) {
        String poly = "<polygon points=\"";
        boolean first = true;
        for (float[] point : points) {
            poly += (first ? "" : " ") + point[0] + "," + point[1];
            first = false;
        }
        poly += "\" style=\"" + style + ";fill:" + color + ";stroke:black;stroke-width:" + blockStrokeWidth + "\"/>";
        return poly;
    }

    private String circle(int cx, int cy, int r, String color) {
        return "<circle cx=\"" + cx + "\" cy=\"" + cy + "\" r=\"" + r + "\" style=\"" + style + ";fill:" + color + ";stroke:none;stroke-width:" + blockStrokeWidth + "\"/>";
    }

    private String line(int x1, int y1, int x2, int y2, String color) {
        return "<line x1=\"" + x1 + "\" y1=\"" + y1 + "\" x2=\"" + x2 + "\" y2=\"" + y2 + "\" style=\"stroke:" + color + ";stroke-width:2\"/>";
    }

    public String color(Material material) {
        switch (material) {
            case BLUE_WOOL: return "blue";
            case YELLOW_WOOL: return "yellow";
            case PINK_WOOL: return "pink";
            case LIGHT_BLUE_WOOL: return "lightblue";
            case LIME_WOOL: return "lime";
            case RED_WOOL: return "red";
            case GREEN_WOOL: return "green";
            case ORANGE_WOOL: return "orange";
            case PURPLE_WOOL: return "purple";
            case BROWN_WOOL: return "brown";
            default: throw new IllegalArgumentException("Ismeretlen anyag " + material);
        }
    }

    @FunctionalInterface
    interface Converter {
        float[] apply(float a, float b, float c);
    }

    public String cuboid(Location location, float x1, float y1, float z1, float x2, float y2, float z2, String color) {
        String cube = "";
        int x = projX(location) + layerSizeX;
        int y = projY(location) + layerSizeY;
        Converter p = (a, b, c) -> new float[] {x + a*gridSize - b*layerSizeX, y + c*gridSize -b*layerSizeY};
        cube += polygon(
                new float[][] {p.apply(x1, y1, z1), p.apply(x2, y1, z1), p.apply(x2, y1, z2), p.apply(x1, y1, z2)},
                color);
        cube += polygon(
                new float[][] {p.apply(x2, y1, z1), p.apply(x2, y1, z2), p.apply(x2, y2, z2), p.apply(x2, y2, z1)},
                color);
        cube += polygon(
                new float[][] {p.apply(x1, y1, z2), p.apply(x1, y2, z2), p.apply(x2, y2, z2), p.apply(x2, y1, z2)},
                color);
        return cube;
    }

    public void setWire(Location location) {
        int x = projX(location);
        int y = projY(location);
        append(location, circle(x + layerSizeX + gridSize /2, y + layerSizeY + gridSize /2, gridSize /3, "red"));
    }

}
