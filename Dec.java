import org.bukkit.Material;

class Dec extends Component {
    private Location input;
    private Location output;

    Dec(Location origin, Coordinates coordinates, Material material) {
        super(origin, coordinates);
        Not notIn = new Not(origin, coordinates, material);
        add(notIn);
        Inc inc = new Inc(origin, coordinates, material);
        inc.shift(inc.input().to(notIn.output()));
        add(inc);
        Not notOut = new Not(origin, coordinates, material);
        notOut.shift(notOut.input().to(inc.output()));
        add(notOut);

        input = notIn.input();
        output = notOut.output();
    }

    Location input() { return input; }
    Location output() { return output; }
}
