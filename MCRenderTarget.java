import org.bukkit.Material;
import org.bukkit.World;

public class MCRenderTarget implements RenderTarget {
    private World world;

    MCRenderTarget(World world) {
        this.world = world;
    }

    @Override
    public void setBlock(Location location) {
        world.getBlockAt(location.getX(), location.getY(), location.getZ()).setType(Material.BLUE_WOOL);
    }

    @Override
    public void setWire(Location location) {
        world.getBlockAt(location.getX(), location.getY(), location.getZ()).setType(Material.REDSTONE_WIRE);
    }
}
