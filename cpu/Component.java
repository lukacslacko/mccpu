package cpu;

import cpu.blocks.Solid;
import cpu.render.Minecraft;
import cpu.render.SVG;

import java.util.Map;
import java.util.TreeMap;

public abstract class Component {
    // A blokkok azert vannak igy rendezve, hogy az svg szepen rajzolja ki oket.
    private Map<Location, Block> blocks = new TreeMap<>(new Location.Comparator());

    public abstract String name();
    public abstract String kind();

    public String describe() { return name() + " of kind " + kind(); }

    protected void add(Location location, Block block) {
        if (blocks.containsKey(location)) {
            if (block.equals(blocks.get(location))) {
                return;
            }
            throw new IllegalArgumentException(
                    "In component " + describe() + " location " + location
                            + " contains " + blocks.get(location) + ", cannot add " + block);
        }
        blocks.put(location, block);
    }

    protected void merge(Component other) {
        for (Location location : other.blocks.keySet()) {
            try {
                add(location, other.blocks.get(location));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        "Cannot merge " + other.describe() + " into " + describe(), e);
            }
        }
    }

    public void shift(Vector v) {
        Map<Location, Block> newBlocks = new TreeMap<>();
        for (Location location : blocks.keySet()) {
            newBlocks.put(location.shifted(v), blocks.get(location));
        }
        blocks = newBlocks;
    }

    public void rotateAround(Location center, int quarters) {
        Map<Location, Block> newBlocks = new TreeMap<>();
        for (Location location : blocks.keySet()) {
            Block block = blocks.get(location);
            block.rotate(quarters);
            newBlocks.put(location.rotatedAround(center, quarters), block);
        }
        blocks = newBlocks;
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
}
