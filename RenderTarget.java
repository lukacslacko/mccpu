import org.bukkit.Material;
import org.bukkit.block.BlockFace;

abstract class RenderTarget {
    abstract void message(String msg);
    abstract void setBlock(Location location, Material material);
    abstract void setWire(Location location);
    abstract void setRepeater(Location location, BlockFace facing);
    abstract void setTopSlab(Location location);
    abstract void setWallTorch(Location location, BlockFace facing);
    abstract void setRedstoneTorch(Location location);
    abstract void setStickyPiston(Location location, BlockFace facing);
    abstract void setComparator(Location location, BlockFace facing);

    void setWireBlock(Location location, Material material) {
        setBlock(location, material);
        setWire(location.above(1));
    }

    void setRepeaterBlock(Location location, Material material, BlockFace facing) {
        setBlock(location, material);
        setRepeater(location.above(1), facing);
    }
}
