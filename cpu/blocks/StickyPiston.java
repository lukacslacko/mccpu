package cpu.blocks;

import cpu.FacingBlock;
import cpu.Location;
import cpu.render.Minecraft;
import cpu.render.SVG;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Piston;

public class StickyPiston extends FacingBlock {
    public StickyPiston(BlockFace blockFace) {
        super(blockFace);
    }

    @Override
    protected String facingDescribe() {
        return "sticky piston";
    }

    @Override
    protected boolean facingSame(FacingBlock other) {
        return other instanceof StickyPiston;
    }

    @Override
    public void put(Location location, SVG svg) {
        if (blockFace == BlockFace.NORTH) {
            svg.append(location, svg.cuboid(location, 0f, 1f, 0f, 1f, 0f, 0.25f, "lime"));
            svg.append(location, svg.cuboid(location, 0f, 1f, 0.25f, 1f, 0f, 1f, "gray"));
        }
        if (blockFace == BlockFace.SOUTH) {
            svg.append(location, svg.cuboid(location, 0f, 1f, 0f, 1f, 0f, 0.75f, "gray"));
            svg.append(location, svg.cuboid(location, 0f, 1f, 0.75f, 1f, 0f, 1f, "lime"));
        }
        if (blockFace == BlockFace.EAST) {
            svg.append(location, svg.cuboid(location, 0f, 1f, 0f, 0.75f, 0f, 1f, "gray"));
            svg.append(location, svg.cuboid(location, 0.75f, 1f, 0f, 1f, 0f, 1f, "lime"));
        }
        if (blockFace == BlockFace.WEST) {
            svg.append(location, svg.cuboid(location, 0f, 1f, 0f, 0.25f, 0f, 1f, "lime"));
            svg.append(location, svg.cuboid(location, 0.25f, 1f, 0f, 1f, 0f, 1f, "gray"));
        }

    }

    @Override
    public void put(Location location, Minecraft minecraft) {
        Block block = minecraft.at(location);
        block.setType(Material.STICKY_PISTON);
        Piston piston = (Piston) block.getBlockData();
        piston.setFacing(blockFace);
        block.setBlockData(piston);

    }
}
