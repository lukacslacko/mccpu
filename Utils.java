import org.bukkit.block.BlockFace;

class Utils {
    static BlockFace facingXZ(int dx, int dz) {
        if (dz > 0) return BlockFace.NORTH;
        if (dz < 0) return BlockFace.SOUTH;
        if (dx > 0) return BlockFace.WEST;
        return BlockFace.EAST;
    }

    static BlockFace facing(Vector v) {
        return facingXZ(v.getDx(), v.getDz());
    }
}
