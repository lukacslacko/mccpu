package cpu.components.arithmetic;

import cpu.Component;
import cpu.Location;
import cpu.Vector;
import cpu.components.infra.Bundle;
import cpu.components.infra.Transmitter;
import org.bukkit.Material;

public class Comparator extends Component {
    private final String name;
    private final int bits;

    public Comparator(String name, int bits, Material materialXor, Material materialEq, Material materialLt, Material materialGt) {
        this.name = name;
        this.bits = bits;
        Location xorOut = null;
        if (bits % 2 != 0) throw new IllegalArgumentException("Bits " + bits + " is odd");
        for (int i = 0; i < bits; ++i) {
            Xor xor = new Xor(name + "/xor" + i, materialXor);
            xor.shift(new Vector(0, 2*i, 0));
            merge(xor);
            if (i == 0) {
                setMarker("inputA", xor.getMarker("inputA"));
                setMarker("inputB", xor.getMarker("inputB"));
                xorOut = xor.getMarker("output");
            }
        }
        for (int i = 0; i < bits / 2; ++i) {
            boolean isTop = i == bits/2 - 1;

            Transmitter eq = new Transmitter(name + "/eq" + i, materialEq, isTop
            );
            eq.shift(eq.getMarker("input").to(xorOut));
            eq.shift(new Vector(0, 4*i, 0));
            merge(eq);

            Transmitter lt = new Transmitter(name + "/lt" + i, materialLt, isTop);
            lt.shift(lt.getMarker("input").to(xorOut));
            lt.rotateAround(xorOut, 1);
            lt.shift(new Vector(0, 4*i, 0));
            merge(lt);

            Transmitter gt = new Transmitter(name + "/gt" + i, materialGt, isTop);
            gt.flipX();
            gt.shift(gt.getMarker("input").to(xorOut));
            gt.rotateAround(xorOut, -1);
            gt.shift(new Vector(0, 4*i, 0));
            merge(gt);
        }
        Bundle ltA = new Bundle(name + "/ltA", bits, 2, 0, 1, 1, materialLt);
        ltA.rotateAround(ltA.getMarker("input"), 1);
        ltA.shift(ltA.getMarker("input").to(getMarker("inputA")).plus(new Vector(0, 0, 2)));
        merge(ltA);
        Bundle ltB = new Bundle(name + "/ltB", bits, 6, 0, 1, 1, materialLt);
        ltB.shift(ltB.getMarker("input").to(ltA.getMarker("output")));
        merge(ltB);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String kind() {
        return "comparator" + bits;
    }
}
