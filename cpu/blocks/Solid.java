package cpu.blocks;

import cpu.Block;
import cpu.Location;
import cpu.render.Minecraft;
import cpu.render.SVG;
import org.bukkit.Material;

public class Solid extends Block {
    private Material material;

    public Solid(Material material) {
        this.material = material;
    }

    @Override
    protected String describe() {
        return "solid " + material;
    }

    @Override
    public void put(Location location, SVG svg) {
        svg.append(location, svg.cuboid(location, 0, 1, 0, 1, 0, 1, svg.color(material)));
    }

    @Override
    public void put(Location location, Minecraft minecraft) {
        minecraft.at(location).setType(material);
    }
}
