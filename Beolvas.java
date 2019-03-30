import org.bukkit.Material;import org.bukkit.Material;import org.bukkit.block.Block;import org.bukkit.block.BlockFace;import org.bukkit.block.data.type.Comparator;import org.bukkit.block.data.type.Piston;import org.bukkit.block.data.type.RedstoneWallTorch;import org.bukkit.block.data.type.Repeater;import org.bukkit.block.data.type.Slab;import org.bukkit.World;
public class Beolvas{public String[][] olvas(World w,int x,int y,int z,int dx,int dy,int dz)
{String[][] r = new String[dy+1][dx+1];for(int ddy = 0; ddy <= dy; ddy++)
{for(int ddx = 0; ddx <= dx; ddx++)
{String s = "";for(int ddz = 0; ddz <= dz; ddz++)
{Block b = w.getBlockAt(x+ddx,y+ddy,z+ddz);Material m = b.getType();switch(m)
{case Material.AIR: s += " ";break;
case Material.REDSTONE_WIRE: s += "+";break;
case Material.REDSTONE_TORCH: s += "*";break;
case Material.GLOWSTONE: s += "-";break;
case Material.QUARZ_SLAB: s += "-";break;
case Material.REPEATER: Repeater rep = (Repeater)b.getBlockData();switch(rep.getFacing())
{case BlockFace.NORTH: s += "<";break;
case BlockFace.EAST: s += "A";break;
case BlockFace.SOUTH: s += ">";break;
case BlockFace.WEST: s += "V";break;}
break;
case Material.REDSTONE_WALL_TORCH: RedstoneWallTorch red = (RedstoneWallTorch)b.getBlockData();switch(red.getFacing())
{case BlockFace.NORTH: s += "]";break;
case BlockFace.EAST: s += "v";break;
case BlockFace.SOUTH: s += "[";break;
case BlockFace.WEST: s += "^";break;}
break;
case Material.STICKY_PISTON: Piston pis = (Piston)b.getBlockData();switch(pis.getFacing())
{case BlockFace.NORTH: s += "E";break;
case BlockFace.EAST: s += "W";break;
case BlockFace.SOUTH: s += "3";break;
case BlockFace.WEST: s += "M";break;}
break;
case Material.COMPARATOR: Comparator com = (Comparator)b.getBlockData();switch(com.getFacing())
{case BlockFace.NORTH: s += "(";break;
case BlockFace.EAST: s += "?";break;
case BlockFace.SOUTH: s += ")";break;
case BlockFace.WEST: s += "U";break;}
break;
case Material.PISTON_HEAD: s += " ";break;
default: s += "X";break;}}
r[ddy][ddx] = s;}}
return r;}}                    
