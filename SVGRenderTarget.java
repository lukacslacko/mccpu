import org.bukkit.Material;
import org.bukkit.block.BlockFace;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class SVGRenderTarget implements RenderTarget {
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
    private int layerSize = 10;

    SVGRenderTarget() {}

    SVGRenderTarget(int gridSize, int layerSize) {
        this.gridSize = gridSize;
        this.layerSize = layerSize;
    }

    String getSVG() {
        String svg = "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"" + (maxX - minX) + "px\" height=\"" + (maxY - minY) + "px\">";
        svg += "<g transform=\"translate(" + (-minX) + "," + (-minY) + ")\">";
        for (Location location : layers.keySet()) {
            svg += layers.get(location);
        }
        svg += "</g></svg>";
        return svg;
    }

    private int projX(Location location) {
        return location.getX() * gridSize - location.getY() * layerSize;
    }

    private int projY(Location location) {
        return location.getZ() * gridSize - location.getY() * layerSize;
    }

    private void add(Location location, String piece) {
        layers.put(location, layers.getOrDefault(location, "") + piece);
        minX = Math.min(minX, projX(location) - 2 * gridSize);
        minY = Math.min(minY, projY(location) - 2 * gridSize);
        maxX = Math.max(maxX, projX(location) + 2 * gridSize);
        maxY = Math.max(maxY, projY(location) + 2 * gridSize);
    }

    private String polygon(int[][] points, String color) {
        String poly = "<polygon points=\"";
        boolean first = true;
        for (int[] point : points) {
            poly += (first ? "" : " ") + point[0] + "," + point[1];
            first = false;
        }
        poly += "\" style=\"fill:" + color + ";stroke:black;stroke-width:1\"/>";
        return poly;
    }

    private String circle(int cx, int cy, int r, String color) {
        return "<circle cx=\"" + cx + "\" cy=\"" + cy + "\" r=\"" + r + "\" style=\"fill:" + color + ";stroke:black;stroke-width:1\"/>";
    }

    private String line(int x1, int y1, int x2, int y2, String color) {
        return "<line x1=\"" + x1 + "\" y1=\"" + y1 + "\" x2=\"" + x2 + "\" y2=\"" + y2 + "\" style=\"stroke:" + color + ";stroke-width:2\"/>";
    }

    private String color(Material material) {
        switch (material) {
            case BLUE_WOOL: return "blue";
            case YELLOW_WOOL: return "yellow";
            case PINK_WOOL: return "pink";
            case LIGHT_BLUE_WOOL: return "light_blue";
            case LIME_WOOL: return "lime";
            default: return "light_gray";
        }
    }

    @Override
    public void message(String msg) {
        System.err.println(msg);
    }

    @Override
    public void setBlock(Location location, Material material) {
        String cube = "";
        int x = projX(location);
        int y = projY(location);
        String color = color(material);
        cube += polygon(
                new int[][] {{x, y}, {x + gridSize, y}, {x + gridSize, y + gridSize}, {x, y + gridSize}},
                color);
        cube += polygon(
                new int[][] {{x + gridSize, y}, {x + gridSize, y + gridSize}, {x + gridSize + layerSize, y + gridSize + layerSize}, {x + gridSize + layerSize, y + layerSize}},
                color);
        cube += polygon(
                new int[][] {{x, y + gridSize}, {x + gridSize, y + gridSize}, {x + gridSize + layerSize, y + gridSize + layerSize}, {x + layerSize, y + gridSize + layerSize}},
                color);
        add(location, cube);
    }

    @Override
    public void setWire(Location location) {
        int x = projX(location);
        int y = projY(location);
        add(location, circle(x + layerSize + gridSize /2, y + layerSize + gridSize /2, gridSize /3, "red"));
    }

    @Override
    public void setRepeater(Location location, BlockFace facing) {
        int x = projX(location) + layerSize + gridSize /2;
        int y = projY(location) + layerSize + gridSize /2;
        int dx = (facing == BlockFace.EAST) ? -gridSize /2 : ((facing == BlockFace.WEST) ? gridSize /2 : 0);
        int dy = (facing == BlockFace.NORTH) ? gridSize /2 : ((facing == BlockFace.SOUTH) ? -gridSize /2 : 0);
        add(location, line(x+dx,y+dy,x-dx,y-dy, "red"));
        add(location, line(x+dx,y+dy,x-dy,y+dx, "red"));
        add(location, line(x+dx,y+dy,x+dy,y-dx, "red"));
    }

    @Override
    public void setTopSlab(Location location) {
        String cube = "";
        int x = projX(location);
        int y = projY(location);
        String color = "white";
        cube += polygon(
                new int[][] {{x, y}, {x + gridSize, y}, {x + gridSize, y + gridSize}, {x, y + gridSize}},
                color);
        cube += polygon(
                new int[][] {{x + gridSize, y}, {x + gridSize, y + gridSize}, {x + gridSize + layerSize /2, y + gridSize + layerSize /2}, {x + gridSize + layerSize /2, y + layerSize /2}},
                color);
        cube += polygon(
                new int[][] {{x, y + gridSize}, {x + gridSize, y + gridSize}, {x + gridSize + layerSize /2, y + gridSize + layerSize /2}, {x + layerSize /2, y + gridSize + layerSize /2}},
                color);
        add(location, cube);
    }

    @Override
    public void setWallTorch(Location location, BlockFace facing) {
        int x = projX(location) + gridSize /2 + layerSize /2;
        int y = projY(location) + gridSize /2 + layerSize /2;
        int dx = (facing == BlockFace.EAST) ? -gridSize /2 : ((facing == BlockFace.WEST) ? gridSize /2 : 0);
        int dy = (facing == BlockFace.NORTH) ? gridSize /2 : ((facing == BlockFace.SOUTH) ? -gridSize /2 : 0);
        add(location, line(x, y, x+dx, y+dy, "red"));
    }
}
