package com.PixelDev.GunMod.Guns;

import com.PixelDev.GunMod.Main;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderEMP extends Render
{
    private static final ResourceLocation texture = new ResourceLocation(Main.MOD_ID, "textures/entity/emp.png");
    private ModelBase model;

    public RenderEMP(RenderManager renderManager) {

        super(renderManager);
        model = new ModelEMP();

    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity)
    {

        return texture;

    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick)
    {

        GL11.glPushMatrix();
        bindTexture(texture);
        GL11.glTranslated(x, y - 1.25D, z);
        model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();

    }
}
