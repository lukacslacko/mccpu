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
        svg.append(location, svg.circle(svg.proj(location, 0.5f, 0f, 0.5f), svg.getGridSize() / 3, "red"));
    }

    @Override
    public void put(Location location, Minecraft minecraft) {
        minecraft.at(location).setType(Material.REDSTONE_WIRE);
    }
}
