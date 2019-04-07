package cpu.blocks;

import cpu.FacingBlock;
import cpu.Location;
import cpu.render.Minecraft;
import cpu.render.SVG;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;

public class RedstoneWallTorch extends FacingBlock {
    public RedstoneWallTorch(BlockFace blockFace) {
        super(blockFace);
    }

    @Override
    protected String facingDescribe() {
        return "redstone wall torch";
    }

    @Override
    public void put(Location location, SVG svg) {
        float dx = (blockFace == BlockFace.EAST) ? -0.5f : ((blockFace == BlockFace.WEST) ? 0.5f : 0);
        float dz = (blockFace == BlockFace.NORTH) ? 0.5f : ((blockFace == BlockFace.SOUTH) ? -0.5f : 0);
        svg.append(
                location,
                svg.line(
                        svg.proj(location, 0.5f, 0.5f, 0.5f),
                        svg.proj(location, 0.5f + dx, 0.5f, 0.5f + dz),
                        "white", 3));
        svg.append(
                location,
                svg.line(
                        svg.proj(location, 0.5f, 0.5f, 0.5f),
                        svg.proj(location, 0.5f + dx, 0.5f, 0.5f + dz),
                        "red", 2));
    }

    @Override
    public void put(Location location, Minecraft minecraft) {
        minecraft.at(location).setBlockData(Bukkit.createBlockData("minecraft:redstone_wall_torch[facing=" + blockFace.toString().toLowerCase() + "]"));

    }
}
