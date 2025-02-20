package net.meteoserpent.craftkaisen.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CursedTechniqueCrafterBlock extends Block {

    public static final VoxelShape SHAPE = Block.box(0.0d, 0.0d, 0.0d, 16.0d, 12.0d, 16.0d);

    public CursedTechniqueCrafterBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

//    @Override
//    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
//        if (pState.getBlock() != pNewState.getBlock()) {
//            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
//            if (blockEntity instanceof CursedTechniqueCrafterBlockEntity) {
//                ((CursedTechniqueCrafterBlockEntity) blockEntity).drops();
//            }
//        }
//
//        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
//    }

//    @Override
//    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
//        if (pLevel.isClientSide) {
//            return InteractionResult.SUCCESS;
//        } else {
//            pPlayer.openMenu(pState.getMenuProvider(pLevel, pPos));
//            return InteractionResult.CONSUME;
//        }
//    }

//    public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
//        return new SimpleMenuProvider((p_52229_, p_52230_, p_52231_) ->
//                new CursedMenu(p_52229_, p_52230_, ContainerLevelAccess.create(pLevel, pPos)), this.getName());
//    }

//    @Override
//    public @org.jetbrains.annotations.Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
//        if(pLevel.isClientSide()) {
//            return null;
//        }
//
//        return createTickerHelper(pBlockEntityType, ModBlockEntities.CURSED_TECHNIQUE_CRAFTER.get(),
//                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
//    }
//
//    @Override
//    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
//        return new CursedTechniqueCrafterBlockEntity(pPos, pState);
//    }
}
