import org.bukkit.Material;
import org.bukkit.block.BlockFace;

interface RenderTarget {
    void message(String msg);
    void setBlock(Location location, Material material);
    void setWire(Location location);
    void setRepeater(Location location, BlockFace facing);
    void setTopSlab(Location location);
    void setWallTorch(Location location, BlockFace facing);
}
