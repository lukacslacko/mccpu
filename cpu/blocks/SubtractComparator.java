package cpu.blocks;

import cpu.FacingBlock;
import cpu.Location;
import cpu.render.Minecraft;
import cpu.render.SVG;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Comparator;

public class SubtractComparator extends FacingBlock {
    public SubtractComparator(BlockFace blockFace) {
        super(blockFace);
    }

    @Override
    protected String facingDescribe() {
        return "subtract comparator";
    }

    @Override
    public void put(Location location, SVG svg) {
        float dx = (blockFace == BlockFace.EAST) ? -0.3f : ((blockFace == BlockFace.WEST) ? 0.3f : 0);
        float dz = (blockFace == BlockFace.NORTH) ? 0.3f : ((blockFace == BlockFace.SOUTH) ? -0.3f : 0);
        svg.append(
                location,
                svg.line(
                        svg.proj(location, 0.5f - dx, 0, 0.5f - dz),
                        svg.proj(location, 0.5f + dx, 0, 0.5f + dz),
                        "red", 3));
        svg.append(
                location,
                svg.line(
                        svg.proj(location, 0.5f - dx, 0, 0.5f - dz),
                        svg.proj(location, 0.5f + dx, 0, 0.5f + dz),
                        "white", 2));
        svg.append(
                location,
                svg.line(
                        svg.proj(location, 0.5f - dx - dz, 0, 0.5f - dz + dx),
                        svg.proj(location, 0.5f - dx + dz, 0, 0.5f - dz - dx),
                        "red", 3));
        svg.append(
                location,
                svg.line(
                        svg.proj(location, 0.5f - dx - dz, 0, 0.5f - dz + dx),
                        svg.proj(location, 0.5f - dx + dz, 0, 0.5f - dz - dx),
                        "white", 2));
    }

    @Override
    public void put(Location location, Minecraft minecraft) {
        Block block = minecraft.at(location);
        block.setType(Material.COMPARATOR);
        Comparator comparator = (Comparator) block.getBlockData();
        comparator.setFacing(blockFace);
        comparator.setMode(Comparator.Mode.SUBTRACT);
        block.setBlockData(comparator);
    }
}
