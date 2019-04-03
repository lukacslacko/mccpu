import org.bukkit.Material;

public class Transmitter extends Component {
    Transmitter(Location origin, Vector width, Vector length, Material material) {
        super(origin, width, length, material,
new String[] {
"X X  ",
"X -  ",
"X +  ",
"XXVXX",
"  + X",
"  - X",
"  X X",
"  XXX",
"+XXXX",
},
new String[] {
"+ +  ",
"+ +  ",
"+ X  ",
"+>X++",
"  X +",
"  + +",
"  X]V",
"  V+U",
" +)>X",
},
new String[] {
"X X  ",
"X -  ",
"X +  ",
"X V  ",
"X +  ",
"X -  ",
"X X  ",
"XXX  ",
"XXXX+",
},
new String[] {
"+ +  ",
"+ +  ",
"+ X  ",
"+ X  ",
"+ X  ",
"+ +  ",
"V[X  ",
"U+V  ",
"X<(+ ",
});
    }
}
