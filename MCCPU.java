import cpu.components.Components;
import cpu.render.Minecraft;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Repeater;
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
                BeolvasResult result = new Beolvas().olvas(((Player) sender).getWorld(), Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2), Math.max(x1, x2) - Math.min(x1, x2), Math.max(y1, y2) - Math.min(y1, y2), Math.max(z1, z2) - Math.min(z1, z2));
                String[] newStrings = new String[result.layers.length];
                for (int i = 0; i < result.layers.length; ++i) {
                    String newString = "new String[] {\n";
                    for (String row : result.layers[i]) {
                        newString += "\"" + row + "\",\n";
                    }
                    newString += "}";
                    newStrings[i] = newString;
                }
                String out = String.join(",\n", newStrings);
                for (String marker : result.markers) {
                    out += marker + "\n";
                }
                Files.write(Paths.get(args[6]), out.getBytes());
                Bukkit.broadcastMessage("Wrote results to " + args[6]);
            }
            catch (Exception e) {
                Bukkit.broadcastMessage("Exception: " + e);
                return false;
            }
        }
        if (label.equals("setmem")) {
            if (args.length != 5) return false;
            try {
                Player player = (Player) sender;
                Minecraft minecraft = new Minecraft(player.getWorld());
                int x = Integer.valueOf(args[0]);
                int y = Integer.valueOf(args[1]);
                int z = Integer.valueOf(args[2]);
                cpu.Location location = new cpu.Location(x, y, z);
                int quarters = Integer.valueOf(args[3]);
                String hex = args[4];
                for (int i = 0; i < hex.length() / 2; ++i) {
                    int val = Integer.parseInt(hex.substring(2*i, 2*i+2), 16);
                    cpu.Location loc = new cpu.Location(location);
                    for (int j = 0; j < 8; ++j) {
                        Block block = minecraft.at(loc);
                        Repeater repeater = (Repeater) block.getBlockData();
                        repeater.setPowered((val & (1 << j)) > 0);
                        block.setBlockData(repeater);
                        loc = loc.above(2);
                    }
                    location = location.shifted(new cpu.Vector(0, 0, 4).rotate(quarters));
                }
            } catch (Exception e) {
                Bukkit.broadcastMessage("Excpetion: " + e);
                return false;
            }
        }
        return true;
    }
}
