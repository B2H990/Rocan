package Mafia.Occisor.util;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class rendershit {
    public static rendershit INSTANCE = new rendershit();

    public rendershit() {
        super(2097152);
    }

    public static void prepare(int mode) {
        prepareGL();
        begin(mode);
    }

    public static void prepareGL() {
        GL11.glBlendFunc(770, 771);
        GlStateManager.func_187428_a(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
        GlStateManager.func_187441_d(1.5F);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179140_f();
        GlStateManager.func_179129_p();
        GlStateManager.func_179141_d();
        GlStateManager.func_179124_c(1.0F, 1.0F, 1.0F);
    }

    public static void begin(int mode) {
        INSTANCE.func_178180_c().func_181668_a(mode, DefaultVertexFormats.field_181706_f);
    }

    public static void release() {
        render();
        releaseGL();
    }

    public static void render() {
        INSTANCE.func_78381_a();
    }

    public static void releaseGL() {
        GlStateManager.func_179089_o();
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179098_w();
        GlStateManager.func_179147_l();
        GlStateManager.func_179126_j();
    }

    public static void drawBox(BlockPos blockPos, int argb, int sides) {
        int a = argb >>> 24 & 255;
        int r = argb >>> 16 & 255;
        int g = argb >>> 8 & 255;
        int b = argb & 255;
        drawBox(blockPos, r, g, b, a, sides);
    }

    public static void drawBox(float x, float y, float z, int argb, int sides) {
        int a = argb >>> 24 & 255;
        int r = argb >>> 16 & 255;
        int g = argb >>> 8 & 255;
        int b = argb & 255;
        drawBox(INSTANCE.func_178180_c(), x, y, z, 1.0F, 1.0F, 1.0F, r, g, b, a, sides);
    }

    public static void drawBox(BlockPos blockPos, int r, int g, int b, int a, int sides) {
        drawBox(INSTANCE.func_178180_c(), (float)blockPos.field_177962_a, (float)blockPos.field_177960_b, (float)blockPos.field_177961_c, 1.0F, 1.0F, 1.0F, r, g, b, a, sides);
    }

    public static BufferBuilder getBufferBuilder() {
        return INSTANCE.func_178180_c();
    }

    public static void drawBox(BufferBuilder buffer, float x, float y, float z, float w, float h, float d, int r, int g, int b, int a, int sides) {
        if ((sides & 1) != 0) {
            buffer.func_181662_b((double)(x + w), (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
        }

        if ((sides & 2) != 0) {
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }

        if ((sides & 4) != 0) {
            buffer.func_181662_b((double)(x + w), (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
        }

        if ((sides & 8) != 0) {
            buffer.func_181662_b((double)x, (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }

        if ((sides & 16) != 0) {
            buffer.func_181662_b((double)x, (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
        }

        if ((sides & 32) != 0) {
            buffer.func_181662_b((double)(x + w), (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }

    }

    public static void drawLines(BufferBuilder buffer, float x, float y, float z, float w, float h, float d, int r, int g, int b, int a, int sides) {
        if ((sides & 17) != 0) {
            buffer.func_181662_b((double)x, (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }

        if ((sides & 18) != 0) {
            buffer.func_181662_b((double)x, (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }

        if ((sides & 33) != 0) {
            buffer.func_181662_b((double)(x + w), (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }

        if ((sides & 34) != 0) {
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }

        if ((sides & 5) != 0) {
            buffer.func_181662_b((double)x, (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
        }

        if ((sides & 6) != 0) {
            buffer.func_181662_b((double)x, (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
        }

        if ((sides & 9) != 0) {
            buffer.func_181662_b((double)x, (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }

        if ((sides & 10) != 0) {
            buffer.func_181662_b((double)x, (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }

        if ((sides & 20) != 0) {
            buffer.func_181662_b((double)x, (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
        }

        if ((sides & 36) != 0) {
            buffer.func_181662_b((double)(x + w), (double)y, (double)z).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)z).func_181669_b(r, g, b, a).func_181675_d();
        }

        if ((sides & 24) != 0) {
            buffer.func_181662_b((double)x, (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)x, (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }

        if ((sides & 40) != 0) {
            buffer.func_181662_b((double)(x + w), (double)y, (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
            buffer.func_181662_b((double)(x + w), (double)(y + h), (double)(z + d)).func_181669_b(r, g, b, a).func_181675_d();
        }

    }

    public static void drawBoundingBox(BlockPos blockPos, float width, int argb) {
        AxisAlignedBB bb = Wrapper.getWorld().func_180495_p(blockPos).func_185918_c(Wrapper.getWorld(), blockPos);
        drawBoundingBox(bb, width, argb);
    }

    public static void drawBoundingBox(AxisAlignedBB bb, float width, int argb) {
        int a = argb >>> 24 & 255;
        int r = argb >>> 16 & 255;
        int g = argb >>> 8 & 255;
        int b = argb & 255;
        drawBoundingBox(bb, width, r, g, b, a);
    }

    public static void drawBoundingBox(AxisAlignedBB bb, float width, int red, int green, int blue, int alpha) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        GlStateManager.func_187441_d(width);
        BufferBuilder bufferbuilder = INSTANCE.func_178180_c();
        if (!bufferbuilder.field_179010_r) {
            bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
            bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
        }

        render();
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
    }

    public static void drawBoundingBoxBottom(AxisAlignedBB bb, float width, int argb) {
        int a = argb >>> 24 & 255;
        int r = argb >>> 16 & 255;
        int g = argb >>> 8 & 255;
        int b = argb & 255;
        drawBoundingBoxBottom(bb, width, r, g, b, a);
    }

    public static void drawBoundingBoxBottom(AxisAlignedBB bb, float width, int red, int green, int blue, int alpha) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        GlStateManager.func_187441_d(width);
        BufferBuilder bufferbuilder = INSTANCE.func_178180_c();
        bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
        render();
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
    }

    public static void drawBoundingBoxHalf(AxisAlignedBB bb, float width, int argb) {
        int a = argb >>> 24 & 255;
        int r = argb >>> 16 & 255;
        int g = argb >>> 8 & 255;
        int b = argb & 255;
        drawBoundingBoxHalf(bb, width, r, g, b, a);
    }

    public static void drawBoundingBoxHalf(AxisAlignedBB bb, float width, int red, int green, int blue, int alpha) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179120_a(770, 771, 0, 1);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a(false);
        GlStateManager.func_187441_d(width);
        BufferBuilder bufferbuilder = INSTANCE.func_178180_c();
        bufferbuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e - 0.5D, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e - 0.5D, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72338_b, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e - 0.5D, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e - 0.5D, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e - 0.5D, bb.field_72334_f).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e - 0.5D, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72338_b, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72336_d, bb.field_72337_e - 0.5D, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
        bufferbuilder.func_181662_b(bb.field_72340_a, bb.field_72337_e - 0.5D, bb.field_72339_c).func_181669_b(red, green, blue, alpha).func_181675_d();
        render();
        GlStateManager.func_179132_a(true);
        GlStateManager.func_179126_j();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
    }
}
