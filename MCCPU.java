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
            Location start = playerLocation.shifted(new Vector(5, 0, 5));
            Vector wire = new Vector(1, 0, 0);
            Bundle bundle = new Bundle(start, start.shifted(wire.times(10)), 4, "bundle", Wire.BLUE);
            Selector selector = new Selector(start.shifted(wire.times(2)), wire, new Vector(0, 0, 1), 4, 5, Wire.BLUE);
            selector.setLadderHeight(8);
            bundle.render(target);
            selector.render(target);
        }
        return true;
    }
}
