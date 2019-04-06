import org.bukkit.Material;

public class Transmitter extends Template {
    Transmitter(Location origin, Coordinates coordinates, Material material) {
        super(origin, coordinates, material,
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

    Location inputGate() {
        return loc(2, -1, 0);
    }

    Location inputData() {
        return loc(0, -1, 0);
    }

    Location output() {
        return loc(0, 8, 3);
    }
}
