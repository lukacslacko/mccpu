package cpu.components;

import cpu.Component;

public class Compound extends Component {
    private final String name;

    public Compound(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String kind() {
        return "compound";
    }
}
