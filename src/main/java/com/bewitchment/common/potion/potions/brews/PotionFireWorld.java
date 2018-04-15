package com.bewitchment.common.potion.potions.brews;

import java.util.HashMap;
import java.util.Map;

import com.bewitchment.api.cauldron.IBrewModifierList;
import com.bewitchment.api.cauldron.modifiers.BewitchmentModifiers;
import com.bewitchment.common.block.ModBlocks;
import com.bewitchment.common.potion.BrewMod;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Joseph on 4/14/2018.
 */
public class PotionFireWorld extends BrewMod {

	private final Map<Block, IBlockState> stateMap = new HashMap<>();

	@SuppressWarnings("deprecation")
	public PotionFireWorld() {
		super("fireworld", true, 0xED2939, true, 0);
		stateMap.put(Blocks.GRASS_PATH, Blocks.RED_NETHER_BRICK.getDefaultState());
		stateMap.put(Blocks.GRAVEL, Blocks.SOUL_SAND.getDefaultState());
		stateMap.put(Blocks.COBBLESTONE, Blocks.NETHERRACK.getDefaultState());
		stateMap.put(Blocks.PLANKS, Blocks.NETHER_BRICK.getDefaultState());
		stateMap.put(Blocks.OAK_FENCE, Blocks.NETHER_BRICK_FENCE.getDefaultState());
		stateMap.put(Blocks.SPRUCE_FENCE, Blocks.NETHER_BRICK_FENCE.getDefaultState());
		stateMap.put(Blocks.ACACIA_FENCE, Blocks.NETHER_BRICK_FENCE.getDefaultState());
		stateMap.put(Blocks.JUNGLE_FENCE, Blocks.NETHER_BRICK_FENCE.getDefaultState());
		stateMap.put(Blocks.BIRCH_FENCE, Blocks.NETHER_BRICK_FENCE.getDefaultState());
		stateMap.put(Blocks.DARK_OAK_FENCE, Blocks.NETHER_BRICK_FENCE.getDefaultState());
		stateMap.put(Blocks.PACKED_ICE, Blocks.OBSIDIAN.getDefaultState());
		stateMap.put(Blocks.ICE, Blocks.OBSIDIAN.getDefaultState());
		stateMap.put(Blocks.WOOL, Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.RED));
		stateMap.put(Blocks.FARMLAND, Blocks.SOUL_SAND.getDefaultState());
		stateMap.put(Blocks.DIRT, Blocks.NETHERRACK.getDefaultState());
		stateMap.put(Blocks.GRASS, Blocks.NETHERRACK.getDefaultState());
		stateMap.put(Blocks.GLASS_PANE, Blocks.STAINED_GLASS_PANE.getDefaultState().withProperty(BlockStainedGlassPane.COLOR, EnumDyeColor.RED));
		stateMap.put(Blocks.STAINED_GLASS_PANE, Blocks.STAINED_GLASS_PANE.getDefaultState().withProperty(BlockStainedGlassPane.COLOR, EnumDyeColor.RED));
		stateMap.put(Blocks.HARDENED_CLAY, Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.RED));
		stateMap.put(Blocks.STAINED_HARDENED_CLAY, Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.RED));
		stateMap.put(Blocks.BLUE_GLAZED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA.getDefaultState());
		stateMap.put(Blocks.GREEN_GLAZED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA.getDefaultState());
		stateMap.put(Blocks.YELLOW_GLAZED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA.getDefaultState());
		stateMap.put(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA.getDefaultState());
		stateMap.put(Blocks.GRAY_GLAZED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA.getDefaultState());
		stateMap.put(Blocks.BLACK_GLAZED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA.getDefaultState());
		stateMap.put(Blocks.BROWN_GLAZED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA.getDefaultState());
		stateMap.put(Blocks.CYAN_GLAZED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA.getDefaultState());
		stateMap.put(Blocks.LIME_GLAZED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA.getDefaultState());
		stateMap.put(Blocks.MAGENTA_GLAZED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA.getDefaultState());
		stateMap.put(Blocks.ORANGE_GLAZED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA.getDefaultState());
		stateMap.put(Blocks.PINK_GLAZED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA.getDefaultState());
		stateMap.put(Blocks.PURPLE_GLAZED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA.getDefaultState());
		stateMap.put(Blocks.SILVER_GLAZED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA.getDefaultState());
		stateMap.put(Blocks.WHITE_GLAZED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA.getDefaultState());
		stateMap.put(Blocks.WATER, Blocks.LAVA.getDefaultState());
		stateMap.put(Blocks.GLASS, Blocks.STAINED_GLASS.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.RED));
		stateMap.put(Blocks.STAINED_GLASS, Blocks.STAINED_GLASS.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.RED));
	}
	
	@Override
	public void affectEntity(Entity source, Entity indirectSource, EntityLivingBase entity, int amplifier, double health) {
		entity.setFire((1 + amplifier) * 2);
	}

	@Override
	public void applyInWorld(World world, BlockPos pos, EnumFacing side, IBrewModifierList modifiers, EntityLivingBase thrower) {
		int box = 1 + modifiers.getLevel(BewitchmentModifiers.RADIUS).orElse(0);

		BlockPos posI = pos.add(box, box / 2, box);
		BlockPos posF = pos.add(-box, -box / 2, -box);

		Iterable<BlockPos> spots = BlockPos.getAllInBox(posI, posF);
		for (BlockPos spot : spots) {
			if (spot.distanceSq(pos) < 2 + box * box / 2) {
				IBlockState state = world.getBlockState(spot);
				if (world.rand.nextInt(4) <= modifiers.getLevel(BewitchmentModifiers.POWER).orElse(0) / 2) {
					if (BlockStairs.isBlockStairs(state)) {
						IBlockState newState = Blocks.NETHER_BRICK_STAIRS.getDefaultState()
								.withProperty(BlockStairs.FACING, state.getValue(BlockStairs.FACING))
								.withProperty(BlockStairs.HALF, state.getValue(BlockStairs.HALF));
						world.setBlockState(spot, newState);
					} else if (state.getBlock() == Blocks.BRICK_BLOCK) {
						world.setBlockState(spot, ModBlocks.scorned_bricks.getDefaultState(), 3);
					} else if (state.getBlock() == Blocks.STONEBRICK) {
						world.setBlockState(spot, ModBlocks.scorned_bricks.getDefaultState(), 3);
					} else if (state.getBlock() == Blocks.END_BRICKS) {
						world.setBlockState(spot, ModBlocks.scorned_bricks.getDefaultState(), 3);
					} else if (state.getBlock() == ModBlocks.embittered_bricks) {
						world.setBlockState(spot, ModBlocks.scorned_bricks.getDefaultState(), 3);
					} else if (state.getBlock() == ModBlocks.fake_ice_fence) {
						world.setBlockState(spot, Blocks.NETHER_BRICK_FENCE.getDefaultState(), 3);
					} else if (state.getBlock() instanceof BlockLog) {
						world.setBlockState(spot, ModBlocks.nethersteel.getDefaultState(), 3);
					} else if (stateMap.containsKey(state.getBlock())) {
						world.setBlockState(spot, stateMap.get(state.getBlock()), 3);
					} else if (state.getMaterial() == Material.SNOW) {
						world.setBlockToAir(spot);
					} else if (state.getBlock() == Blocks.WATER) {
						world.setBlockState(spot, Blocks.LAVA.getDefaultState(), 3);
					} else if (state.getBlock() == Blocks.FLOWING_WATER) {
						world.setBlockState(spot, Blocks.FLOWING_LAVA.getDefaultState().withProperty(BlockDynamicLiquid.LEVEL, state.getValue(BlockDynamicLiquid.LEVEL)), 2);
					} else if (state.getBlock() instanceof BlockLeaves) {
						world.setBlockState(spot, Blocks.GLOWSTONE.getDefaultState(), 3);
					}
					
					if (world.getBlockState(spot).getBlock() == Blocks.NETHERRACK && world.isAirBlock(spot.up()) && world.rand.nextInt(10) == 0) {
						world.setBlockState(spot.up(), Blocks.FIRE.getDefaultState(), 3);
					}
					
				}
			}
		}
	}
}