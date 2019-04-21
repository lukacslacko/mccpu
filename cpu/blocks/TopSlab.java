package cpu.blocks;

import cpu.Block;
import cpu.Location;
import cpu.render.Minecraft;
import cpu.render.SVG;
import org.bukkit.Material;
import org.bukkit.block.data.type.Slab;

public class TopSlab extends Block {
    @Override
    protected String describe() {
        return "TopSlab";
    }

    @Override
    protected boolean same(Block other) {
        return other instanceof TopSlab;
    }

    @Override
    public void put(Location location, SVG svg) {
        svg.append(location, svg.cuboid(location, 0f, 1f, 0f, 1f, 0.5f, 1f, "white"));
    }

    @Override
    public void put(Location location, Minecraft minecraft) {
        org.bukkit.block.Block block = minecraft.at(location);
        block.setType(Material.QUARTZ_SLAB);
        Slab slab = (Slab) block.getBlockData();
        slab.setType(Slab.Type.TOP);
        block.setBlockData(slab);
    }
}
