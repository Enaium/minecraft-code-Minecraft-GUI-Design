package cn.enaium.sirius.Gui.design;

import cn.enaium.sirius.Font.UnicodeFontRenderer;
import cn.enaium.sirius.Modules.Module;
import cn.enaium.sirius.Setting.Setting;
import cn.enaium.sirius.Sirius;
import cn.enaium.sirius.Utils.PlayerUtils;
import cn.enaium.sirius.Utils.RenderUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Panel {
    //Enaium
    public Module module;
    public double x;
    public double y;
    public double width;
    public double height;
    private double x2;
    private double y2;
    public boolean dragging;
    private boolean hovered;
    UnicodeFontRenderer font = Sirius.ENTER.FONT_MANAGER.simpleton20;
    public Panel(Module module) {
        this.module = module;
        this.x = Setting.getByName("X",module).getCurrent();
        this.y = Setting.getByName("Y",module).getCurrent();
        this.width = Setting.getByName("W",module).getCurrent();
        this.height = Setting.getByName("H",module).getCurrent();
        dragging = false;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
        if (this.dragging) {
            x = x2 + mouseX;
            y = y2 + mouseY;
            Setting.getByName("X",module).setCurrent(x);
            Setting.getByName("Y",module).setCurrent(y);
        }

        Color color = Color.WHITE;
        if(this.hovered) {
            color = Color.GRAY;
        }
        if(this.hovered) {
            ArrayList<String> infolist = new ArrayList();
            infolist.add("Name:" + this.module.getName());
            infolist.add( "X:" + this.x + " " + "Y:" + this.y);
            infolist.sort(((o1, o2) -> font.getStringWidth(o2) - font.getStringWidth(o1)));
            Gui.drawRect(mouseX,mouseY,mouseX + font.getStringWidth(infolist.get(0)),mouseY +font.FONT_HEIGHT * infolist.size(),SystemColor.black);
            int y = mouseY;
            for(int i = 0;i < infolist.size();i++) {
                font.drawString(infolist.get(i),mouseX,y,Color.WHITE.getRGB());
                y += font.FONT_HEIGHT;
            }

        }


        RenderUtils.drawRect(2,this.x,this.y,this.x + this.width, this.y + this.height,color.getRGB());
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && this.hovered) {
            x2 = this.x - mouseX;
            y2 = this.y - mouseY;
            dragging = true;
            return true;
        }
        return false;
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (state == 0) {
            this.dragging = false;
        }
    }
}
