import org.bukkit.Material;

class Component implements Renderer {
    private Location origin;
    private Vector width, length;
    private Material material;
    private String[][] layers;

    Component(Location origin, Vector width, Vector length, Material material, String[]... layers) {
        this.origin = origin;
        this.width = width;
        this.length = length;
        this.material = material;
        this.layers = layers;
    }

    @Override
    public void render(RenderTarget target) {
        for (int height = 0; height < layers.length; ++height) {
            for (int l = 0; l < layers[height].length; ++l) {
                for (int w = 0; w < layers[height][l].length(); ++w) {
                    Location loc = origin.shifted(width.times(w)).shifted(length.times(l)).above(height);
                    char c = layers[height][l].charAt(w);
                    switch (c) {
                        case 'X': target.setBlock(loc, material); break;
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
                        case '?': target.setComparator(loc, Utils.facing(length)); break;
                        case 'U': target.setComparator(loc, Utils.facing(length.times(-1))); break;
                        case '(': target.setComparator(loc, Utils.facing(width)); break;
                        case ')': target.setComparator(loc, Utils.facing(width.times(-1))); break;
                    }
                }
            }
        }
    }
}
