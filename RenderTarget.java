import org.bukkit.block.BlockFace;

interface RenderTarget {
    void message(String msg);
    void setBlock(Location location);
    void setWire(Location location);
    void setRepeater(Location location, BlockFace facing);
}
