package cpu.render;

import cpu.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class Minecraft {
    private final World world;

    public Minecraft(World world) {
        this.world = world;
    }

    public Block at(Location location) {
        return world.getBlockAt(location.getX(), location.getY(), location.getZ());
    }
}
