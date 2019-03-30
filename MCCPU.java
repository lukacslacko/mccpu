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
            Components.component(args[0], playerLocation, args[1], args[2], args[3], target);
        }
        if (label.equals("components")) {
            Bukkit.broadcastMessage(String.join(", ", Components.COMPONENTS));
        }
        return true;
    }
}
