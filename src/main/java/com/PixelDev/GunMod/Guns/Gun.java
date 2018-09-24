package com.PixelDev.GunMod.Guns;

import com.PixelDev.GunMod.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.MinecraftError;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import com.PixelDev.GunMod.item.ModItems;

import java.util.ArrayList;
import java.util.List;

public class Gun extends Item {

    public static List<String> anims = new ArrayList<String>();

    public static int bullets = 7;
    public static int countdown = 720;
    public static boolean aiming = false;

    public Gun(String name)
    {

        setCreativeTab(Main.tab);
        setMaxStackSize(1);
        setMaxDamage(1);
        setUnlocalizedName(name);

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World world, EntityPlayer player, EnumHand hand)
    {

        if (player.capabilities.isCreativeMode && bullets > 0) {

            bullets += -1;
            player.swingArm(EnumHand.MAIN_HAND);
            world.playSound(player, player.getPosition(), SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

            if (!world.isRemote)
            {

                world.spawnEntityInWorld(new EntityEMP(world, player));

            }
        }
        else if(player.capabilities.isCreativeMode && bullets <= 0){

            System.out.println("Don't have any bullets!");

        }

        return super.onItemRightClick(itemStackIn, world, player, hand);
    }

    public static void onRenderItemReload(float x, float y, float z){
        countdown += -1;
        GL11.glPushMatrix();
        GL11.glRotatef(90, x, y, z);
        GL11.glPopMatrix();

        if(countdown <= 0){

            anims.remove(0);
            GL11.glPushMatrix();
            GL11.glRotatef(0, x, y, z);
            GL11.glPopMatrix();
            Gun.bullets = 7;
            countdown = 720;
            Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new TextComponentString("Finished Reloading!"));

        }
    }

    public static void onRenderItemAim(float x, float y, float z){

        GL11.glPushMatrix();
        GL11.glRotatef(90, x, y, z);
        GL11.glPopMatrix();
        System.out.println("Aiming!");
        anims.remove(0);

    }

}
