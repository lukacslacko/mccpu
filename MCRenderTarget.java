import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Repeater;

public class MCRenderTarget implements RenderTarget {
    private World world;

    MCRenderTarget(World world) {
        this.world = world;
    }

    @Override
    public void message(String msg) {
        Bukkit.broadcastMessage(msg);
    }

    @Override
    public void setBlock(Location location) {
        world.getBlockAt(location.getX(), location.getY(), location.getZ()).setType(Material.BLUE_WOOL);
    }

    @Override
    public void setWire(Location location) {
        world.getBlockAt(location.getX(), location.getY(), location.getZ()).setType(Material.REDSTONE_WIRE);
    }

    @Override
    public void setRepeater(Location location, BlockFace facing) {
        Block block = world.getBlockAt(location.getX(), location.getY(), location.getZ());
        block.setType(Material.REPEATER);
        Repeater repeater = (Repeater) block.getBlockData();
        repeater.setFacing(facing);
        block.setBlockData(repeater);
    }
}
