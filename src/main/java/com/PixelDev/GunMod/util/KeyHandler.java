package com.PixelDev.GunMod.util;

import com.PixelDev.GunMod.Guns.Gun;
import com.PixelDev.GunMod.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


public class KeyHandler{

    @SubscribeEvent
    public void onKeyPressed(KeyInputEvent event){

        if(Main.keyReload.isKeyDown()){

            Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new TextComponentString("Reloading!"));
            Gun.anims.add("Reload");

        }

        else if(Main.keyAim.isKeyDown()){

            Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new TextComponentString("Aiming!"));
            Gun.anims.add("Aim");

        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {

        if(Gun.anims.contains("Reload")){

            System.out.println("Reload is in the List!");
            Gun.onRenderItemReload(1.0F, 0.0F, 0.0F);

        }

        else if(Gun.anims.contains("Aim")){

            System.out.println("Aim is in the list!");
            Gun.onRenderItemAim(0.0F, 0.0F, 1.0F);

        }
    }
}
