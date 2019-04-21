package cpu.components.memory.bank;

import cpu.Component;
import org.bukkit.Material;

public class Bank extends Component {
    private final String name;

    public Bank(String name, Material material) {
        this.name = name;

    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String kind() {
        return "bank";
    }
}
