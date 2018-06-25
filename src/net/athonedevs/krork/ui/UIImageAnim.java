package net.athonedevs.krork.ui;

import net.athonedevs.krork.gfx.Animation;

import java.awt.*;

public class UIImageAnim extends UIObject {

    private Animation anim;
    private ClickListener clicker;

    public UIImageAnim(float x, float y, int width, int height, Animation anim, ClickListener clicker) {
        super(x, y, width, height);
        this.anim = anim;
        this.clicker = clicker;
    }


    @Override
    public void tick() {
        anim.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(anim.getCurrentFrame(), (int) x, (int) y, width, height, null);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
