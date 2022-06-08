package com.skillafire.antiquita.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.skillafire.antiquita.blockentity.DivineFurnaceBlockEntity;
import com.skillafire.antiquita.blockentity.DivineFurnaceModel;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class DivineFurnaceRenderer extends GeoBlockRenderer<DivineFurnaceBlockEntity> {
    public DivineFurnaceRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new DivineFurnaceModel());
    }
    
    @Override
    public RenderType getRenderType(DivineFurnaceBlockEntity animatable, float partialTicks, PoseStack stack,
    		MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
    		ResourceLocation textureLocation) {
    	return RenderType.translucent();
    }
}
