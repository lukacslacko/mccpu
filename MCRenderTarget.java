import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Comparator;
import org.bukkit.block.data.type.Piston;
import org.bukkit.block.data.type.RedstoneWallTorch;
import org.bukkit.block.data.type.Repeater;
import org.bukkit.block.data.type.Slab;

public class MCRenderTarget extends RenderTarget {
    private World world;

    MCRenderTarget(World world) {
        this.world = world;
    }

    @Override
    void message(String msg) {
        Bukkit.broadcastMessage(msg);
    }

    @Override
    public void setBlock(Location location, Material material) {
        world.getBlockAt(location.getX(), location.getY(), location.getZ()).setType(material);
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

    @Override
    public void setTopSlab(Location location) {
        Block block = world.getBlockAt(location.getX(), location.getY(), location.getZ());
        block.setType(Material.QUARTZ_SLAB);
        Slab slab = (Slab) block.getBlockData();
        slab.setType(Slab.Type.TOP);
        block.setBlockData(slab);
    }

    @Override
    public void setWallTorch(Location location, BlockFace facing) {
        Block block = world.getBlockAt(location.getX(), location.getY(), location.getZ());
        block.setType(Material.REDSTONE_WALL_TORCH);
        RedstoneWallTorch torch = (RedstoneWallTorch) block.getBlockData();
        torch.setFacing(facing);
        block.setBlockData(torch);

    }

    @Override
    void setRedstoneTorch(Location location) {
        world.getBlockAt(location.getX(), location.getY(), location.getZ()).setType(Material.REDSTONE_TORCH);
    }

    @Override
    public void setStickyPiston(Location location, BlockFace facing) {
        Block block = world.getBlockAt(location.getX(), location.getY(), location.getZ());
        block.setType(Material.STICKY_PISTON);
        Piston piston = (Piston) block.getBlockData();
        piston.setFacing(facing);
        block.setBlockData(piston);
    }

    @Override
    void setComparator(Location location, BlockFace facing) {
        Block block = world.getBlockAt(location.getX(), location.getY(), location.getZ());
        block.setType(Material.COMPARATOR);
        Comparator comparator = (Comparator) block.getBlockData();
        comparator.setFacing(facing);
        comparator.setMode(Comparator.Mode.SUBTRACT);
        block.setBlockData(comparator);
    }
}
