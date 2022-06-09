package com.skillafire.antiquita.renderer;

import com.skillafire.antiquita.blockentity.DivineFurnaceBlockEntity;
import com.skillafire.antiquita.blockentity.DivineFurnaceModel;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

@OnlyIn(Dist.CLIENT)
public class DivineFurnaceRenderer extends GeoBlockRenderer<DivineFurnaceBlockEntity> {
	
    public DivineFurnaceRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new DivineFurnaceModel());
    }
    
    /*@Override
    public RenderType getRenderType(DivineFurnaceBlockEntity animatable, float partialTicks, PoseStack stack,
    		MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
    		ResourceLocation textureLocation) {
    	return RenderType.entityTranslucent(textureLocation);
    }*/
}
