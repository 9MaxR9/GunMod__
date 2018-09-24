package com.PixelDev.GunMod.Guns;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneDiode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityEMP extends EntityThrowable
{

    public float explosionPower = 5.0F;
    public int empRadius = 5;
    public World worldObj;

    public EntityEMP(World world, EntityLivingBase entity)
    {

        super(world, entity);
        this.worldObj = world;

    }

    private void explode()
    {

        int bx = (int)posX;
        int by = (int)posY;
        int bz = (int)posZ;
        int direction = MathHelper.floor_double((double)((Minecraft.getMinecraft().thePlayer.rotationYawHead * 4F) / 360F) + 0.5D) & 3;

        if(direction == 0){

            worldObj.createExplosion(this, posX, posY, posZ + 5, 5.0F, true);

        }
        else if(direction == 1){

            worldObj.createExplosion(this, posX - 5, posY, posZ, 5.0F, true);

        }
        else if(direction == 2){

            worldObj.createExplosion(this, posX, posY, posZ - 5, 5.0F, true);

        }
        else if(direction == 3){

            worldObj.createExplosion(this, posX + 5, posY, posZ, 5.0F, true);

        }
        for (int x = bx - empRadius; x <= empRadius; x++)
        {

            for (int y = by - empRadius; y <= by + empRadius; y++)
            {

                for (int z = bz - empRadius; z <= bz + empRadius; z++)
                {

                    Block block = (Block) worldObj.getBlockState(new BlockPos(x, y, z));

                    if (block == Blocks.REDSTONE_WIRE || block instanceof BlockRedstoneDiode)
                    {

                        block.dropBlockAsItem(worldObj, new BlockPos(x, y, z), worldObj.getBlockState(new BlockPos(x, y, z)), 0);
                        worldObj.setBlockToAir(new BlockPos(x, y, z));

                    }
                    if (block == Blocks.REDSTONE_BLOCK)
                    {

                        worldObj.setBlockState(new BlockPos(x, y, z), (IBlockState) Blocks.STONE);

                    }
                }
            }
        }
        setDead();
    }
    @Override
    public void onUpdate()
    {

        super.onUpdate();

        if (ticksExisted > 20)
        {

            //explode();

        }

        for (int i = 0; i < 10; i++)
        {

            double x = (double)(rand.nextInt(10) - 5) / 8.0D;
            double y = (double)(rand.nextInt(10) - 5) / 8.0D;
            double z = (double)(rand.nextInt(10) - 5) / 8.0D;
            //worldObj.spawnParticle(EnumParticleTypes.getByName("fireworksSpark"), posX, posY, posZ, x, y, z);

        }
    }

    @Override
    protected float getGravityVelocity()
    {

        return 0.055F;

    }

    @Override
    protected void onImpact(RayTraceResult result) {

        explode();

    }
}