import org.bukkit.Material;

class Template extends Renderer {
    private Vector width, length;
    private Material material;
    private String[][] layers;

    Template(Location origin, Coordinates coordinates, Material material, String[]... layers) {
        super(origin, coordinates);
        this.width = coordinates.at(1, 0);
        this.length = coordinates.at(0, 1);
        this.material = material;
        this.layers = layers;
    }

    @Override
    public void render(RenderTarget target) {
        // Mivel a fali faklyak lepotyognak, ha nincs elobb a blokk lerakva, amire kerulnek,
        // ezert eloszor lerakjuk a blokkokat, utana a tobbi dolgot.
        for (int height = 0; height < layers.length; ++height) {
            for (int l = 0; l < layers[height].length; ++l) {
                for (int w = 0; w < layers[height][l].length(); ++w) {
                    Location loc = loc(w, l, height);
                    char c = layers[height][l].charAt(w);
                    switch (c) {
                        case 'X': target.setBlock(loc, material); break;
                    }
                }
            }
        }
        for (int height = 0; height < layers.length; ++height) {
            for (int l = 0; l < layers[height].length; ++l) {
                for (int w = 0; w < layers[height][l].length(); ++w) {
                    Location loc = loc(w, l, height);
                    char c = layers[height][l].charAt(w);
                    switch (c) {
                        case '+': target.setWire(loc); break;
                        case '>': target.setRepeater(loc, Utils.facing(width)); break;
                        case '<': target.setRepeater(loc, Utils.facing(width.times(-1))); break;
                        case 'V': target.setRepeater(loc, Utils.facing(length)); break;
                        case 'A': target.setRepeater(loc, Utils.facing(length.times(-1))); break;
                        case '*': target.setRedstoneTorch(loc); break;
                        case 'v': target.setWallTorch(loc, Utils.facing(length.times(-1))); break;
                        case '^': target.setWallTorch(loc, Utils.facing(length)); break;
                        case '[': target.setWallTorch(loc, Utils.facing(width)); break;
                        case ']': target.setWallTorch(loc, Utils.facing(width.times(-1))); break;
                        case 'M': target.setStickyPiston(loc, Utils.facing(length)); break;
                        case 'W': target.setStickyPiston(loc, Utils.facing(length.times(-1))); break;
                        case '3': target.setStickyPiston(loc, Utils.facing(width)); break;
                        case 'E': target.setStickyPiston(loc, Utils.facing(width.times(-1))); break;
                        case '-': target.setTopSlab(loc); break;
                        case 'U': target.setComparator(loc, Utils.facing(length)); break;
                        case '?': target.setComparator(loc, Utils.facing(length.times(-1))); break;
                        case ')': target.setComparator(loc, Utils.facing(width)); break;
                        case '(': target.setComparator(loc, Utils.facing(width.times(-1))); break;
                        case ' ': target.setAir(loc);break;
                        case 'O': target.setLamp(loc);break;
                    }
                }
            }
        }
    }
}
