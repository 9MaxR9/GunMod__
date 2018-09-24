package com.PixelDev.GunMod;

import com.PixelDev.GunMod.Guns.EntityEMP;
import com.PixelDev.GunMod.item.ModItems;
import com.PixelDev.GunMod.proxy.CommonProxy;
import com.PixelDev.GunMod.tab.Tab;
import com.PixelDev.GunMod.util.KeyHandler;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import org.lwjgl.input.Keyboard;

@Mod(modid = Main.MOD_ID, version = Main.VERSIONS, name = Main.NAME)
public class Main {

    public static final String MOD_ID = "gm";
    public static final String VERSIONS = "1.0";
    public static final String NAME = "Gun Mod";

    @SidedProxy(clientSide = "com.PixelDev.GunMod.proxy.ClientProxy", serverSide = "com.PixelDev.GunMod.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static Main instance;

    public static Tab tab;

    public static final KeyBinding keyReload = new KeyBinding("key.reload",Keyboard.KEY_R, "category.tutorialmod");
    public static final KeyBinding keyAim = new KeyBinding("key.aim",Keyboard.KEY_LSHIFT, "category.tutorialmod");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        tab = new Tab(CreativeTabs.getNextID(), "tab_tutorial");
        ModItems.preInit();
        proxy.preInit(event);

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        EntityRegistry.registerModEntity(EntityEMP.class, "EMP", 0, this, 64, 10, true);
        ClientRegistry.registerKeyBinding(keyReload);
        ClientRegistry.registerKeyBinding(keyAim);
        FMLCommonHandler.instance().bus().register(new KeyHandler());
        proxy.init(event);

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        proxy.postInit(event);

    }

}
