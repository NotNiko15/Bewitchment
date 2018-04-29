package com.bewitchment.client.jei.components;

import java.util.ArrayList;
import java.util.Arrays;

import com.bewitchment.api.cauldron.IBrewEffect;
import com.bewitchment.common.cauldron.BrewData;
import com.bewitchment.common.cauldron.BrewData.BrewEntry;
import com.bewitchment.common.cauldron.BrewModifierListImpl;
import com.bewitchment.common.crafting.cauldron.CauldronRegistry;
import com.bewitchment.common.item.ModItems;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class BrewingWrapper implements IRecipeWrapper {
	
	IBrewEffect effect;
	
	public BrewingWrapper(IBrewEffect effect) {
		this.effect = effect;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, Arrays.asList(Arrays.asList(CauldronRegistry.getIngredientFromBrew(effect).orElse(Ingredient.EMPTY).getMatchingStacks())));
		
		BrewData data = new BrewData();
		data.addEntry(new BrewEntry(CauldronRegistry.getPotionFromBrew(effect), new BrewModifierListImpl()));
		
		ingredients.setOutputLists(ItemStack.class, Arrays.asList(getListFor(data)));
	}
	
	private ArrayList<ItemStack> getListFor(BrewData data) {
		ArrayList<ItemStack> items = new ArrayList<>(4);
		ItemStack a = new ItemStack(ModItems.brew_phial_drink);
		data.saveToStack(a);
		items.add(a);
		a = new ItemStack(ModItems.brew_phial_splash);
		data.saveToStack(a);
		items.add(a);
		a = new ItemStack(ModItems.brew_phial_linger);
		data.saveToStack(a);
		items.add(a);
		a = new ItemStack(ModItems.brew_arrow);
		data.saveToStack(a);
		items.add(a);
		return items;
	}
	
}
