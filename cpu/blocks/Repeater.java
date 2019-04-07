package cpu.blocks;

import cpu.FacingBlock;
import cpu.Location;
import cpu.render.Minecraft;
import cpu.render.SVG;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class Repeater extends FacingBlock {
    @Override
    protected String describe() {
        return "repeater";
    }

    @Override
    public void put(Location location, SVG svg) {
        /*
        int x = svg.projX(location) + svg.layerSizeX + svg.gridSize /2;
        int y = projY(location) + layerSizeY + gridSize /2;
        int dx = (facing == BlockFace.EAST) ? -gridSize /2 : ((facing == BlockFace.WEST) ? gridSize /2 : 0);
        int dy = (facing == BlockFace.NORTH) ? gridSize /2 : ((facing == BlockFace.SOUTH) ? -gridSize /2 : 0);
        add(location, line(x+dx,y+dy,x-dx,y-dy, "white"));
        add(location, line(x+dx,y+dy,x-dy,y+dx, "white"));
        add(location, line(x+dx,y+dy,x+dy,y-dx, "white"));
        */
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
