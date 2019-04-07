import cpu.components.Components;
import cpu.render.Minecraft;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MCCPU extends JavaPlugin {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equals("mccpu")) {
            if (args.length != 5) return false;
            Player player = (Player) sender;
            Minecraft minecraft = new Minecraft(player.getWorld());
            cpu.Location playerLocation =
                    new cpu.Location(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
            try {
                cpu.components.Components.Kind kind = Components.Kind.valueOf(args[0]);
                int x = Integer.valueOf(args[1]);
                int y = Integer.valueOf(args[2]);
                int z = Integer.valueOf(args[3]);
                int quarters = Integer.valueOf(args[4]);
                cpu.components.Components.place(kind, new cpu.Location(x, y, z), quarters, Material.BLUE_WOOL, minecraft);
            } catch (Throwable e) {
                Bukkit.broadcastMessage("MCCPU exception: " + e);
                while (e.getCause() != null) {
                    e = e.getCause();
                    Bukkit.broadcastMessage("MCCPU caused by: " + e);
                }
                return false;
            }
        }
        if (label.equals("components")) {
            Bukkit.broadcastMessage("Components: " + Arrays.deepToString(Components.Kind.values()));
        }
        if (label.equals("beolvas")) {
            if (args.length != 7) return false;
            try {
                int x1 = Integer.valueOf(args[0]);
                int y1 = Integer.valueOf(args[1]);
                int z1 = Integer.valueOf(args[2]);
                int x2 = Integer.valueOf(args[3]);
                int y2 = Integer.valueOf(args[4]);
                int z2 = Integer.valueOf(args[5]);
                String[][] result = new Beolvas().olvas(((Player) sender).getWorld(), Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2), Math.max(x1, x2) - Math.min(x1, x2), Math.max(y1, y2) - Math.min(y1, y2), Math.max(z1, z2) - Math.min(z1, z2));
                String[] newStrings = new String[result.length];
                for (int i = 0; i < result.length; ++i) {
                    String newString = "new String[] {\n";
                    for (String row : result[i]) {
                        newString += "\"" + row + "\",\n";
                    }
                    newString += "}";
                    newStrings[i] = newString;
                }
                String out = String.join(",\n", newStrings);
                Files.write(Paths.get(args[6]), out.getBytes());
                Bukkit.broadcastMessage("Wrote results to " + args[6]);
            }
            catch (Exception e) {
                Bukkit.broadcastMessage("Exception: " + e);
                return false;
            }
        }
        return true;
    }
}
