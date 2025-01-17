// 
// Decompiled by Procyon v0.5.36
// 

package net.minecraft.util;

import java.util.Date;
import moonsense.utils.screenshot.ScreenshotRunnable;
import net.minecraft.event.HoverEvent;
import net.minecraft.event.ClickEvent;
import moonsense.features.SCModule;
import moonsense.features.modules.ScreenshotModule;
import moonsense.config.ModuleConfig;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.BufferUtils;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import java.io.File;
import java.text.SimpleDateFormat;
import org.apache.logging.log4j.LogManager;
import java.nio.IntBuffer;
import java.text.DateFormat;
import org.apache.logging.log4j.Logger;

public class ScreenShotHelper
{
    private static final Logger logger;
    private static final DateFormat dateFormat;
    private static IntBuffer pixelBuffer;
    private static int[] pixelValues;
    private static final String __OBFID = "CL_00000656";
    
    static {
        logger = LogManager.getLogger();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
    }
    
    public static IChatComponent saveScreenshot(final File p_148260_0_, final int p_148260_1_, final int p_148260_2_, final Framebuffer p_148260_3_) {
        return saveScreenshot(p_148260_0_, null, p_148260_1_, p_148260_2_, p_148260_3_);
    }
    
    public static IChatComponent saveScreenshot(final File p_148259_0_, final String p_148259_1_, int p_148259_2_, int p_148259_3_, final Framebuffer p_148259_4_) {
        try {
            final File var5 = new File(p_148259_0_, "screenshots");
            var5.mkdir();
            if (OpenGlHelper.isFramebufferEnabled()) {
                p_148259_2_ = p_148259_4_.framebufferTextureWidth;
                p_148259_3_ = p_148259_4_.framebufferTextureHeight;
            }
            final int var6 = p_148259_2_ * p_148259_3_;
            if (ScreenShotHelper.pixelBuffer == null || ScreenShotHelper.pixelBuffer.capacity() < var6) {
                ScreenShotHelper.pixelBuffer = BufferUtils.createIntBuffer(var6);
                ScreenShotHelper.pixelValues = new int[var6];
            }
            GL11.glPixelStorei(3333, 1);
            GL11.glPixelStorei(3317, 1);
            ScreenShotHelper.pixelBuffer.clear();
            if (OpenGlHelper.isFramebufferEnabled()) {
                GlStateManager.func_179144_i(p_148259_4_.framebufferTexture);
                GL11.glGetTexImage(3553, 0, 32993, 33639, ScreenShotHelper.pixelBuffer);
            }
            else {
                GL11.glReadPixels(0, 0, p_148259_2_, p_148259_3_, 32993, 33639, ScreenShotHelper.pixelBuffer);
            }
            ScreenShotHelper.pixelBuffer.get(ScreenShotHelper.pixelValues);
            TextureUtil.func_147953_a(ScreenShotHelper.pixelValues, p_148259_2_, p_148259_3_);
            BufferedImage var7 = null;
            if (OpenGlHelper.isFramebufferEnabled()) {
                var7 = new BufferedImage(p_148259_4_.framebufferWidth, p_148259_4_.framebufferHeight, 1);
                int var9;
                for (int var8 = var9 = p_148259_4_.framebufferTextureHeight - p_148259_4_.framebufferHeight; var9 < p_148259_4_.framebufferTextureHeight; ++var9) {
                    for (int var10 = 0; var10 < p_148259_4_.framebufferWidth; ++var10) {
                        var7.setRGB(var10, var9 - var8, ScreenShotHelper.pixelValues[var9 * p_148259_4_.framebufferTextureWidth + var10]);
                    }
                }
            }
            else {
                var7 = new BufferedImage(p_148259_2_, p_148259_3_, 1);
                var7.setRGB(0, 0, p_148259_2_, p_148259_3_, ScreenShotHelper.pixelValues, 0, p_148259_2_);
            }
            File var11;
            if (p_148259_1_ == null) {
                var11 = getTimestampedPNGFileForDirectory(var5);
            }
            else {
                var11 = new File(var5, p_148259_1_);
            }
            ImageIO.write(var7, "png", var11);
            if (ModuleConfig.INSTANCE.isEnabled(ScreenshotModule.INSTANCE) && ScreenshotModule.INSTANCE.customScreenshotMessage.getBoolean()) {
                final ChatComponentText comp1 = new ChatComponentText("Saved Screenshot! ");
                final ChatComponentText comp2 = new ChatComponentText("[Open] ");
                final ChatComponentText comp3 = new ChatComponentText("[Folder] ");
                final ChatComponentText comp4 = new ChatComponentText("[Copy Image] ");
                final ChatComponentText comp5 = new ChatComponentText("[Upload To Imgur] ");
                comp1.getChatStyle().setColor(EnumChatFormatting.GRAY);
                comp2.getChatStyle().setColor(EnumChatFormatting.AQUA).setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, var11.getAbsolutePath()));
                comp2.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Open the image in your file explorer.")));
                comp3.getChatStyle().setColor(EnumChatFormatting.AQUA).setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, var5.getAbsolutePath()));
                comp3.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Open the screenshots folder in your file explorer.")));
                final ClickEvent ce1 = new ClickEvent(ClickEvent.Action.COPY_IMAGE, var5.getAbsolutePath());
                ce1.setPossibleImgurImage(var7);
                comp4.getChatStyle().setColor(EnumChatFormatting.AQUA).setChatClickEvent(ce1);
                comp4.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Copy the image to your clipboard.")));
                final ClickEvent ce2 = new ClickEvent(ClickEvent.Action.UPLOAD_TO_IMGUR, "");
                ce2.setPossibleImgurImage(var7);
                comp5.getChatStyle().setColor(EnumChatFormatting.AQUA).setChatClickEvent(ce2);
                comp5.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Upload screenshot to Imgur and copy link to clipboard.")));
                comp1.appendSibling(comp2).appendSibling(comp3).appendSibling(comp4).appendSibling(comp5);
                return comp1;
            }
            final ChatComponentText var12 = new ChatComponentText(var11.getName());
            var12.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, var11.getAbsolutePath()));
            return new ChatComponentTranslation("screenshot.success", new Object[] { var12 });
        }
        catch (Exception var13) {
            ScreenShotHelper.logger.warn("Couldn't save screenshot", (Throwable)var13);
            return new ChatComponentTranslation("screenshot.failure", new Object[] { var13.getMessage() });
        }
    }
    
    public static void uploadScreenshot(final BufferedImage theImage) {
        new Thread(new ScreenshotRunnable("imgur", theImage)).start();
    }
    
    private static File getTimestampedPNGFileForDirectory(final File p_74290_0_) {
        final String var2 = ScreenShotHelper.dateFormat.format(new Date()).toString();
        int var3 = 1;
        File var4;
        while (true) {
            var4 = new File(p_74290_0_, String.valueOf(var2) + ((var3 == 1) ? "" : ("_" + var3)) + ".png");
            if (!var4.exists()) {
                break;
            }
            ++var3;
        }
        return var4;
    }
}
