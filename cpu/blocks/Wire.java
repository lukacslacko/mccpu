package cpu.blocks;

import cpu.Block;
import cpu.Location;
import cpu.render.Minecraft;
import cpu.render.SVG;
import org.bukkit.Material;

public class Wire extends Block {
    @Override
    protected String describe() {
        return "wire";
    }

    @Override
    public void put(Location location, SVG svg) {
        svg.setWire(location);
    }

    @Override
    public void put(Location location, Minecraft minecraft) {
        minecraft.at(location).setType(Material.REDSTONE_WIRE);
    }
}
