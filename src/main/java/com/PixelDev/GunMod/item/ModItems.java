package com.PixelDev.GunMod.item;

import com.PixelDev.GunMod.Guns.Gun;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.PixelDev.GunMod.Main;


public class ModItems {
    public static Item gun;

    public static void preInit() {

        gun = new Gun("emp_gun");
        registerItems();

    }

    public static void registerItems() {

        GameRegistry.register(gun, new ResourceLocation(Main.MOD_ID, "emp_gun"));

    }

    public static void registerRenders() {
        //registerRender(tutorialItem);
    }

    public static void registerRender(Item item) {

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Main.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));

    }

}
