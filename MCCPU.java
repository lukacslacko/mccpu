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
            Bundle bundle1 = new Bundle(
                    playerLocation.shifted(new Vector(3, 3, 13)),
                    playerLocation.shifted(new Vector(23, 3, 13)),
                    8,
                    "bundle1");
            Bundle bundle2 = new Bundle(
                    playerLocation.shifted(new Vector(13, 3, 3)),
                    playerLocation.shifted(new Vector(13, 3, 23)),
                    8,
                    "wire2"
            );
            bundle1.intersectWith(bundle2);
            bundle1.render(target);
            bundle2.render(target);
        }
        return true;
    }
}
