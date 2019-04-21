package cpu;

import org.bukkit.block.BlockFace;

public abstract class FacingBlock extends Block {
    protected BlockFace blockFace;

    protected FacingBlock(BlockFace blockFace) {
        this.blockFace = blockFace;
    }

    @Override
    protected String describe() {
        return facingDescribe() + " facing " + blockFace;
    }

    abstract protected String facingDescribe();

    abstract protected boolean facingSame(FacingBlock other);

    @Override
    protected boolean same(Block other) {
        if (!(other instanceof FacingBlock)) {
            return false;
        }
        if (!blockFace.equals(((FacingBlock) other).blockFace)) {
            return false;
        }
        return facingSame((FacingBlock) other);
    }

    @Override
    public void rotate(int quarters) {
        while (quarters < 0) { quarters += 4; }
        for (; quarters > 0; --quarters) {
            switch (blockFace) {
                case NORTH:
                    blockFace = BlockFace.EAST;
                    break;
                case EAST:
                    blockFace = BlockFace.SOUTH;
                    break;
                case SOUTH:
                    blockFace = BlockFace.WEST;
                    break;
                case WEST:
                    blockFace = BlockFace.NORTH;
                    break;
            }
        }
    }

    @Override
    public void flipX() {
        if (blockFace == BlockFace.EAST) {
            blockFace = BlockFace.WEST;
        } else if (blockFace == BlockFace.WEST) {
            blockFace = BlockFace.EAST;
        }
    }
}
