import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class MCCPU extends JavaPlugin {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equals("mccpu")) {
            Bukkit.broadcastMessage("Hello mccpu!");
            Player player = (Player) sender;
            MCRenderTarget target = new MCRenderTarget(player.getWorld());
            Location playerLocation =
                    new Location(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
            Wire wire_above = new Wire(
                    playerLocation.shifted(new Vector(3, 3, 13)),
                    playerLocation.shifted(new Vector(23, 3, 13)),
                    "wire_above");
            Wire wire_below = new Wire(
                    playerLocation.shifted(new Vector(13, 2, 3)),
                    playerLocation.shifted(new Vector(13, 2, 23)),
                    "wire_below"
            );
            wire_above.intersectWithWireBelow(wire_below);
            wire_above.render(target);
            wire_below.render(target);
        }
        return true;
    }
}
