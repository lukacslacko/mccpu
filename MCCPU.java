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
            Bidir bidir1 = new Bidir(
                    playerLocation.shifted(new Vector(5, 0, 9)),
                    playerLocation.shifted(new Vector(15, 0, 9)),
                    new Vector(0, 0, 2),
                    4,
                    "bidir1",
                    Wire.BLUE);
            Bidir bidir2 = new Bidir(
                    playerLocation.shifted(new Vector(9, 0, 5)),
                    playerLocation.shifted(new Vector(9, 0, 15)),
                    new Vector(2, 0, 0),
                    2,
                    "bidir2",
                    Wire.LIGHT_GREEN);
            bidir2.intersectWith(bidir1);
            bidir1.render(target);
            bidir2.render(target);
        }
        return true;
    }
}
