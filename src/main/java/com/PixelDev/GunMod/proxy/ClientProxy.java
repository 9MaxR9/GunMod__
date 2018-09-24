package com.PixelDev.GunMod.proxy;

import com.PixelDev.GunMod.item.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import com.PixelDev.GunMod.Guns.EntityEMP;
import com.PixelDev.GunMod.Guns.RenderEMP;
import com.PixelDev.GunMod.Main;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {

        OBJLoader.INSTANCE.addDomain(Main.MOD_ID);
        registerModel(ModItems.gun);

    }

    public void registerModel(Item item) {

        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Main.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));

    }

    @Override
    public void init(FMLInitializationEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(EntityEMP.class, RenderEMP::new);
        ModItems.registerRenders();

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }
}
