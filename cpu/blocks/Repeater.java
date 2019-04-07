package cpu.blocks;

import cpu.FacingBlock;
import cpu.Location;
import cpu.render.Minecraft;
import cpu.render.SVG;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class Repeater extends FacingBlock {
    public Repeater(BlockFace blockFace) {
        super(blockFace);
    }

    @Override
    protected String facingDescribe() {
        return "repeater";
    }

    @Override
    public void put(Location location, SVG svg) {
        float dx = (blockFace == BlockFace.EAST) ? -0.5f : ((blockFace == BlockFace.WEST) ? 0.5f : 0);
        float dz = (blockFace == BlockFace.NORTH) ? 0.5f : ((blockFace == BlockFace.SOUTH) ? -0.5f : 0);
        svg.append(
                location,
                svg.line(
                        svg.proj(location, 0.5f+dx, 0, 0.5f+dz),
                        svg.proj(location, 0.5f-dx, 0, 0.5f-dz),
                        "white"));
        svg.append(
                location,
                svg.line(
                        svg.proj(location, 0.5f+dx, 0, 0.5f+dz),
                        svg.proj(location, 0.5f-dz, 0, 0.5f+dx),
                        "white"));
        svg.append(
                location,
                svg.line(
                        svg.proj(location, 0.5f+dx, 0, 0.5f+dz),
                        svg.proj(location, 0.5f+dz, 0, 0.5f-dx),
                        "white"));
    }

    @Override
    public void put(Location location, Minecraft minecraft) {
        Block block = minecraft.at(location);
        block.setType(Material.REPEATER);
        org.bukkit.block.data.type.Repeater repeater = (org.bukkit.block.data.type.Repeater) block.getBlockData();
        repeater.setFacing(blockFace);
        block.setBlockData(repeater);
    }
}
