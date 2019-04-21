package cpu;

import cpu.blocks.Air;
import cpu.blocks.ErrorBlock;
import cpu.blocks.Solid;
import cpu.render.Minecraft;
import cpu.render.SVG;

import java.util.Map;
import java.util.TreeMap;

public abstract class Component {
    private Map<Location, Block> blocks = new TreeMap<>();
    private Map<String, Location> markers = new TreeMap<>();

    public abstract String name();
    public abstract String kind();

    public String describe() { return name() + " of kind " + kind(); }

    protected void add(Location location, Block block) {
        if (blocks.containsKey(location)) {
            if (block.equals(blocks.get(location)) || block instanceof Air) {
                return;
            }
            if (!(blocks.get(location) instanceof Air)) {
                if (true) {
                    throw new IllegalArgumentException(
                            "In component " + describe() + " location " + location
                                    + " contains " + blocks.get(location) + ", cannot add " + block);
                } else {
                    System.out.println("Error in Component.Add!");
                    blocks.put(location, new ErrorBlock());
                }
            }
        }
        blocks.put(location, block);
    }

    protected void remove(Location location) {
        if (blocks.containsKey(location)) {
            blocks.remove(location);
        } else {
            throw new IllegalArgumentException(
                    "In component " + describe() + " location " + location + " does not contain "
                    + " any block, it cannot be removed");
        }
    }

    public void merge(Component other) {
        for (Location location : other.blocks.keySet()) {
            try {
                add(location, other.blocks.get(location));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        "Cannot merge " + other.describe() + " into " + describe(), e);
            }
        }
        for (String marker : other.markers.keySet()) {
            String markerName = other.name() + "/" + marker;
            if (markers.containsKey(markerName)) {
                throw new IllegalArgumentException(
                        "Cannot merge " + other.describe() + " into " + describe() + " because marker "
                        + markerName + " already exists");
            }
            markers.put(markerName, other.markers.get(marker));
        }
    }

    protected void inheritMarkers(Component other) {
        for (String marker : other.markers.keySet()) {
            setMarker(marker, other.getMarker(marker));
        }
    }

    public void shift(Vector v) {
        Map<Location, Block> newBlocks = new TreeMap<>();
        for (Location location : blocks.keySet()) {
            newBlocks.put(location.shifted(v), blocks.get(location));
        }
        blocks = newBlocks;
        for (String marker : markers.keySet()) {
            markers.put(marker, markers.get(marker).shifted(v));
        }
    }

    public void rotateAround(Location center, int quarters) {
        Map<Location, Block> newBlocks = new TreeMap<>();
        for (Location location : blocks.keySet()) {
            Block block = blocks.get(location);
            block.rotate(quarters);
            newBlocks.put(location.rotatedAround(center, quarters), block);
        }
        blocks = newBlocks;
        for (String marker : markers.keySet()) {
            markers.put(marker, markers.get(marker).rotatedAround(center, quarters));
        }
    }

    public void flipX() {
        Map<Location, Block> newBlocks = new TreeMap<>();
        for (Location location : blocks.keySet()) {
            Block block = blocks.get(location);
            block.flipX();
            newBlocks.put(new Location(-location.getX(), location.getY(), location.getZ()), block);
        }
        blocks = newBlocks;
        for (String marker : markers.keySet()) {
            Location location = markers.get(marker);
            markers.put(marker, new Location(-location.getX(), location.getY(), location.getZ()));
        }
    }

    public void put(SVG svg) {
        for (Location location : blocks.keySet()) {
            blocks.get(location).put(location, svg);
        }
    }

    public void put(Minecraft minecraft) {
        // Mivel a fali faklyak lepotyognak, ha nincs elobb a blokk lerakva, amire kerulnek,
        // ezert eloszor lerakjuk a blokkokat, utana a tobbi dolgot.
        for (Location location : blocks.keySet()) {
            if (blocks.get(location) instanceof Solid) {
                blocks.get(location).put(location, minecraft);
            }
        }
        for (Location location : blocks.keySet()) {
            if (!(blocks.get(location) instanceof Solid)) {
                blocks.get(location).put(location, minecraft);
            }
        }
    }

    public Location getMarker(String marker) {
        if (markers.containsKey(marker)) {
            return markers.get(marker);
        } else {
            throw new IllegalArgumentException("Marker " + marker + " is not present in " + describe());
        }
    }

    public void setMarker(String marker, Location location) {
        if (markers.containsKey(marker)) {
            throw new IllegalArgumentException("Marker " + marker + " already exists in " + describe());
        }
        markers.put(marker, location);
    }
}
