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
                    playerLocation.shifted(new Vector(5, 0, 10)),
                    playerLocation.shifted(new Vector(15, 0, 10)),
                    3,
                    "bundle1");
            Bundle bundle2a = new Bundle(
                    playerLocation.shifted(new Vector(9, 0, 5)),
                    playerLocation.shifted(new Vector(9, 0, 15)),
                    3,
                    "wire2"
            );
            Bundle bundle2b = new Bundle(
                    playerLocation.shifted(new Vector(11, 0, 15)),
                    playerLocation.shifted(new Vector(11, 0, 5)),
                    3,
                    "wire2"
            );
            bundle1.intersectWith(bundle2a);
            bundle1.intersectWith(bundle2b);
            bundle1.render(target);
            bundle2a.render(target);
            bundle2b.render(target);
        }
        return true;
    }
}
