package cn.delcx.sirius.Gui.design;

import cn.delcx.sirius.Modules.Module;
import cn.delcx.sirius.Sirius;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;

public class Design extends GuiScreen {
    public static ArrayList<Panel> panels;
    //Lightcolour

    public Design() {
        ArrayList<Module> mods = Sirius.ENTER.MODULE_MANAGER.getToggledGuiModules();
        panels = new ArrayList();
        for(Module m : mods) {
            panels.add(new Panel(m));
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for(Panel p : panels) {
            p.drawScreen(mouseX,mouseY,partialTicks);
        }
        super.drawScreen(mouseX,mouseY,partialTicks);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for (Panel p : panels) {
            if (p.mouseClicked(mouseX, mouseY, mouseButton))
                return;
        }

    }


    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        for (Panel p : panels) {
            p.mouseReleased(mouseX, mouseY, state);
        }
        super.mouseReleased(mouseX, mouseY, state);
    }


}
