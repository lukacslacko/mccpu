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
    public boolean equals(Object obj) {
        return obj instanceof Air;
    }

    @Override
    protected String describe() {
        return "air";
    }
}
