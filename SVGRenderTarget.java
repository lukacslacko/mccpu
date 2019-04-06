import org.apache.logging.log4j.util.TriConsumer;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

public class SVGRenderTarget extends RenderTarget {
    private Map<Location, String> layers = new TreeMap<>(new Comparator<Location>() {
        @Override
        public int compare(Location o1, Location o2) {
            int compY = Integer.compare(o1.getY(), o2.getY());
            if (compY != 0) return compY;
            int compZ = Integer.compare(o1.getZ(), o2.getZ());
            if (compZ != 0) return compZ;
            return Integer.compare(o1.getX(), o2.getX());
        }
    });

    private int minX = Integer.MAX_VALUE;
    private int minY = Integer.MAX_VALUE;
    private int maxX = Integer.MIN_VALUE;
    private int maxY = Integer.MIN_VALUE;

    private int gridSize = 30;
    private int layerSizeX = 10;
    private int layerSizeY = 8;
    private int blockStrokeWidth = 1;

    private String style;

    SVGRenderTarget() {}

    SVGRenderTarget(int gridSize, int layerSizeX, int layerSizeY, int blockStrokeWidth, float opacity) {
        this.gridSize = gridSize;
        this.layerSizeX = layerSizeX;
        this.layerSizeY = layerSizeY;
        this.blockStrokeWidth = blockStrokeWidth;
        this.style = "fill-opacity:" + opacity + ";stroke-opacity:" + opacity;
    }

    String getSVG() {
        StringBuilder svg = new StringBuilder("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"" + (maxX - minX) + "px\" height=\"" + (maxY - minY) + "px\">");
        svg.append("<defs>");
        svg.append("<linearGradient id=\"lamp\" x1=\"0%\" y1=\"0%\" x2=\"100%\" y2=\"0%\">");
        svg.append("<stop offset=\"0%\" style=\"stop-color:yellow\"/>");
        svg.append("<stop offset=\"100%\" style=\"stop-color:red\"/>");
        svg.append("</linearGradient>");
        svg.append("</defs>");
        svg.append("<g transform=\"translate(").append(-minX).append(",").append(-minY).append(")\">");
        System.out.println(layers.size());
        for (Location location : layers.keySet()) {
            svg.append(layers.get(location));
        }
        svg.append("</g></svg>");
        return svg.toString();
    }

    private int projX(Location location) {
        return location.getX() * gridSize - location.getY() * layerSizeX;
    }

    private int projY(Location location) {
        return location.getZ() * gridSize - location.getY() * layerSizeY;
    }

    private void add(Location location, String piece) {
        layers.put(location, layers.getOrDefault(location, "") + piece);
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

    private String color(Material material) {
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
            default: return "gray";
        }
    }

    @FunctionalInterface
    interface Converter {
        float[] apply(float a, float b, float c);
    }

    private String cuboid(Location location, float x1, float y1, float z1, float x2, float y2, float z2, String color) {
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

    @Override
    public void message(String msg) {
        System.err.println(msg);
    }

    @Override
    public void setBlock(Location location, Material material) {
        add(location, cuboid(location, 0f, 1f, 0f, 1f, 0f, 1f, color(material)));
    }

    @Override
    public void setWire(Location location) {
        int x = projX(location);
        int y = projY(location);
        add(location, circle(x + layerSizeX + gridSize /2, y + layerSizeY + gridSize /2, gridSize /3, "red"));
    }

    @Override
    public void setRepeater(Location location, BlockFace facing) {
        int x = projX(location) + layerSizeX + gridSize /2;
        int y = projY(location) + layerSizeY + gridSize /2;
        int dx = (facing == BlockFace.EAST) ? -gridSize /2 : ((facing == BlockFace.WEST) ? gridSize /2 : 0);
        int dy = (facing == BlockFace.NORTH) ? gridSize /2 : ((facing == BlockFace.SOUTH) ? -gridSize /2 : 0);
        add(location, line(x+dx,y+dy,x-dx,y-dy, "white"));
        add(location, line(x+dx,y+dy,x-dy,y+dx, "white"));
        add(location, line(x+dx,y+dy,x+dy,y-dx, "white"));
    }

    @Override
    public void setTopSlab(Location location) {
        add(location, cuboid(location, 0f, 1f, 0f, 1f, 0.5f, 1f, "white"));
    }

    @Override
    public void setWallTorch(Location location, BlockFace facing) {
        int x = projX(location) + gridSize /2 + layerSizeX /2;
        int y = projY(location) + gridSize /2 + layerSizeY /2;
        int dx = (facing == BlockFace.EAST) ? -gridSize /2 : ((facing == BlockFace.WEST) ? gridSize /2 : 0);
        int dy = (facing == BlockFace.NORTH) ? gridSize /2 : ((facing == BlockFace.SOUTH) ? -gridSize /2 : 0);
        add(location, line(x, y, x+dx, y+dy, "red"));
    }

    @Override
    void setRedstoneTorch(Location location) {
        int x = projX(location);
        int y = projY(location);
        add(location, circle(x + layerSizeX + gridSize / 2, y + layerSizeY + gridSize / 2, gridSize / 3, "yellow"));
        add(location, circle(x + layerSizeX + gridSize / 2, y + layerSizeY + gridSize / 2, gridSize / 4, "red"));
    }

    @Override
    public void setStickyPiston(Location location, BlockFace facing) {
        if (facing == BlockFace.NORTH) {
            add(location, cuboid(location, 0f, 1f, 0f, 1f, 0f, 0.25f, "lime"));
            add(location, cuboid(location, 0f, 1f, 0.25f, 1f, 0f, 1f, "gray"));
        }
        if (facing == BlockFace.SOUTH) {
            add(location, cuboid(location, 0f, 1f, 0f, 1f, 0f, 0.75f, "gray"));
            add(location, cuboid(location, 0f, 1f, 0.75f, 1f, 0f, 1f, "lime"));
        }
        if (facing == BlockFace.EAST) {
            add(location, cuboid(location, 0f, 1f, 0f, 0.75f, 0f, 1f, "gray"));
            add(location, cuboid(location, 0.75f, 1f, 0f, 1f, 0f, 1f, "lime"));
        }
        if (facing == BlockFace.WEST) {
            add(location, cuboid(location, 0f, 1f, 0f, 0.25f, 0f, 1f, "lime"));
            add(location, cuboid(location, 0.25f, 1f, 0f, 1f, 0f, 1f, "gray"));
        }
    }

    @Override
    void setComparator(Location location, BlockFace facing) {
        int x = projX(location) + layerSizeX + gridSize /2;
        int y = projY(location) + layerSizeY + gridSize /2;
        int dx = (facing == BlockFace.EAST) ? -gridSize /3 : ((facing == BlockFace.WEST) ? gridSize /3 : 0);
        int dy = (facing == BlockFace.NORTH) ? gridSize /3 : ((facing == BlockFace.SOUTH) ? -gridSize /3 : 0);
        add(location, line(x-dx,y-dy,x+dx,y+dy, "white"));
        add(location, line(x-dx+dy,y-dy-dx,x-dx-dy,y-dy+dx, "pink"));
    }
    
    @Override
    void setAir(Location location) {
    }

    @Override
    void setLamp(Location location) {
        add(location, cuboid(location, 0f, 1f, 0f, 1f, 0f, 1f, "url(#lamp)"));
    }
}
