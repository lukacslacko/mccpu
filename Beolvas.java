import org.bukkit.Material;import org.bukkit.Material;import org.bukkit.block.Block;import org.bukkit.block.BlockFace;import org.bukkit.block.data.type.Comparator;import org.bukkit.block.data.type.Piston;import org.bukkit.block.data.type.RedstoneWallTorch;import org.bukkit.block.data.type.Repeater;import org.bukkit.block.data.type.Slab;import org.bukkit.World;
public class Beolvas{public BeolvasResult olvas(World w,int x,int y,int z,int dx,int dy,int dz)
{BeolvasResult r = new BeolvasResult();r.layers = new String[dy+1][dx+1];for(int ddy = 0; ddy <= dy; ddy++)
{for(int ddx = 0; ddx <= dx; ddx++)
{String s = "";for(int ddz = 0; ddz <= dz; ddz++)
{Block b = w.getBlockAt(x+ddx,y+ddy,z+ddz);Material m = b.getType();switch(m)
{case AIR: s += " ";break;
    case REDSTONE_WIRE: s += "+";break;
    case REDSTONE_TORCH: s += "*";break;
    case GLOWSTONE: s += "-";break;
    case QUARTZ_SLAB: s += "-";break;
    case REDSTONE_LAMP: s += "O";break;
    case SIGN: s += "+";org.bukkit.block.Sign state = (org.bukkit.block.Sign) b.getState();r.markers.add("setMarker(\""+state.getLine(0)+"\", new Location("+ddz+", "+(ddy-1)+", "+ddx+"));");break;
    case REPEATER: Repeater rep = (Repeater)b.getBlockData();switch(rep.getFacing())
    {case NORTH: s += ">";break;
        case EAST: s += "A";break;
        case SOUTH: s += "<";break;
        case WEST: s += "V";break;}
        break;
    case REDSTONE_WALL_TORCH: RedstoneWallTorch red = (RedstoneWallTorch)b.getBlockData();switch(red.getFacing())
    {case NORTH: s += "[";break;
        case EAST: s += "v";break;
        case SOUTH: s += "]";break;
        case WEST: s += "^";break;}
        break;
    case STICKY_PISTON: Piston pis = (Piston)b.getBlockData();switch(pis.getFacing())
    {case NORTH: s += "3";break;
        case EAST: s += "W";break;
        case SOUTH: s += "E";break;
        case WEST: s += "M";break;}
        break;
    case COMPARATOR: Comparator com = (Comparator)b.getBlockData();switch(com.getFacing())
    {case NORTH: s += ")";break;
        case EAST: s += "?";break;
        case SOUTH: s += "(";break;
        case WEST: s += "U";break;}
        break;
    case PISTON_HEAD: s += " ";break;
    default: s += "X";break;}}
    r.layers[ddy][ddx] = s;}}
    return r;}}