package com.bewitchment.common.block.natural;

import static net.minecraft.block.BlockHorizontal.FACING;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bewitchment.client.core.IModelRegister;
import com.bewitchment.client.fx.ParticleF;
import com.bewitchment.client.handler.ModelHandler;
import com.bewitchment.common.Bewitchment;
import com.bewitchment.common.core.statics.ModCreativeTabs;
import com.bewitchment.common.core.statics.ModSounds;
import com.bewitchment.common.item.ModItems;
import com.bewitchment.common.lib.LibMod;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This class was created by Joseph on 3/4/2017.
 * It's distributed as part of Bewitchment under
 * the MIT license.
 */
public class BlockBeehive extends BlockFalling implements IModelRegister {

	private static final AxisAlignedBB BOX = new AxisAlignedBB(0.18F, 0, 0.18F, 0.82F, 1, 0.82F);

	public BlockBeehive(String id, Material material) {
		super(Material.GRASS);
		setUnlocalizedName(id);
		setDefaultState(blockState.getBaseState());
		setRegistryName(LibMod.MOD_ID, id);
		setCreativeTab(ModCreativeTabs.BLOCKS_CREATIVE_TAB);
		setSoundType(SoundType.PLANT);
		setResistance(1F);
		setHardness(1F);
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromMeta(int meta) {
		final EnumFacing facing = EnumFacing.getHorizontal(meta);
		return getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		final EnumFacing facing = state.getValue(FACING);
		return facing.getHorizontalIndex();
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOX;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public int quantityDropped(Random random) {
		return random.nextInt(5);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (rand.nextInt(10) == 0) {
			Bewitchment.proxy.spawnParticle(ParticleF.BEE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0, 0, 0);
		}
		if (rand.nextInt(25) == 0) {
			worldIn.playSound(null, pos, ModSounds.BUZZ, SoundCategory.BLOCKS, 0.2F, 1F);
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.empty_honeycomb;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> ret = new ArrayList<ItemStack>();
		Random rand = world instanceof World ? ((World) world).rand : new Random();

		ItemStack honeycomb = new ItemStack(getItemDropped(state, rand, fortune), quantityDropped(rand), damageDropped(state));
		if (!honeycomb.isEmpty()) {
			ret.add(honeycomb);
		}
		return ret;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return true;
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		final EnumFacing enumfacing = EnumFacing.fromAngle(placer.rotationYaw).getOpposite();
		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModel() {
		ModelHandler.registerModel(this, 0);
	}
}
