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
        if (label.equals("beolvas")) {
            int x1 = Integer.valueOf(args[0]);
            int y1 = Integer.valueOf(args[1]);
            int z1 = Integer.valueOf(args[2]);
            int x2 = Integer.valueOf(args[3]);
            int y2 = Integer.valueOf(args[4]);
            int z2 = Integer.valueOf(args[5]);
            String[][] result = new Beolvas().olvas(((Player) sender).getWorld(), Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2), Math.max(x1, x2) - Math.min(x1, x2), Math.max(y1, y2) - Math.min(y1, y2), Math.max(z1, z2) - Math.min(z1, z2));
            for (String[] layer : result) {
                Bukkit.broadcastMessage(String.join("|", layer));
            }
        }
        return true;
    }
}
