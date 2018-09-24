package com.PixelDev.GunMod.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class Tab extends CreativeTabs {

    public Tab(int index, String label) {

        super(index, label);

    }

    @Override
    public Item getTabIconItem() {

        return Items.EMERALD;

    }
}
