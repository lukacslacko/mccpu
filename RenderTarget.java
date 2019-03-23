import org.bukkit.block.BlockFace;

interface RenderTarget {
    void setBlock(Location location);
    void setWire(Location location);
    void setRepeater(Location location, BlockFace facing);
}
