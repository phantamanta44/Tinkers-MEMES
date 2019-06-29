package io.github.phantamanta44.tmemes.client;

import io.github.phantamanta44.tmemes.ElectricToolRegistry;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import org.lwjgl.opengl.GL11;

import java.util.Objects;

public class MemeRenderInterceptor {

    public static void handleRender(ItemStack stack, int posX, int posY) {
        if (ElectricToolRegistry.isElectric(stack)) {
            IEnergyStorage energy = Objects.requireNonNull(stack.getCapability(CapabilityEnergy.ENERGY, EnumFacing.NORTH));
            float fraction = (float)energy.getEnergyStored() / energy.getMaxEnergyStored();
            int barY = posY + (stack.getItem().showDurabilityBar(stack) ? 11 : 13);

            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            GlStateManager.disableTexture2D();
            GlStateManager.disableAlpha();
            GlStateManager.disableBlend();
            drawRect(posX + 2, barY, 13, 2, 0, 0, 0, 0, 0, 0);
            GlStateManager.shadeModel(GL11.GL_SMOOTH);
            drawRect(posX + 2, barY, (int)(fraction * 13), 1, 0x1A, 0x23, 0x7E, 0x42, 0xA5, 0xF5);
            GlStateManager.shadeModel(GL11.GL_FLAT);
            GlStateManager.enableBlend();
            GlStateManager.enableAlpha();
            GlStateManager.enableTexture2D();
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
        }
    }

    private static void drawRect(int x, int y, int width, int height,
                                 int r1, int g1, int b1, int r2, int g2, int b2) {
        Tessellator tess = Tessellator.getInstance();
        BufferBuilder renderer = tess.getBuffer();
        renderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        renderer.pos(x, y, 0.0D).color(r1, g1, b1, 255).endVertex();
        renderer.pos(x, y + height, 0.0D).color(r1, g1, b1, 255).endVertex();
        renderer.pos(x + width, y + height, 0.0D).color(r2, g2, b2, 255).endVertex();
        renderer.pos(x + width, y, 0.0D).color(r2, g2, b2, 255).endVertex();
        tess.draw();
    }

}
