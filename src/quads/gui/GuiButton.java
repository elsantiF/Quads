package quads.gui;

import engine.util.Vector2;

public abstract class GuiButton {

    public String name;
    public int x, y, width, height;

    public GuiButton(int x, int y, int width, int height, String name) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Vector2 getPos() {
        return new Vector2(x, y);
    }

    public boolean isIn(int mouseX, int mouseY) {
        if (mouseX > getPos().x && mouseX < width) {
            return mouseY > getPos().y && mouseY < height;
        }
        return false;
    }

    public abstract void render();

    public abstract void onClick(int i);

}
