package cpu.blocks;

import cpu.Block;
import cpu.Location;
import cpu.render.Minecraft;
import cpu.render.SVG;
import org.bukkit.Material;

public class Air extends Block {
    @Override
    public void put(Location location, SVG svg) {}

    @Override
    public void put(Location location, Minecraft minecraft) {
        minecraft.at(location).setType(Material.AIR);
    }

    @Override
    protected String describe() {
        return "air";
    }

    @Override
    protected boolean same(Block other) {
        return other instanceof Air;
    }
}
