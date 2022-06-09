package com.skillafire.antiquita.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.skillafire.antiquita.Antiquita;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class DivineAnvilScreen extends AbstractContainerScreen<DivineAnvilMenu> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(Antiquita.MOD_ID, "textures/gui/divine_anvil_gui.png");
	
	public DivineAnvilScreen(DivineAnvilMenu menu, Inventory inv, Component title) {
		super(menu, inv, title);
	}

	@Override
	protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		
		int x = (width - imageWidth) / 2;
		int y = (height - imageHeight) / 2;
		
		blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);
		
		renderProgress(poseStack, x, y);		
	}
	
	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
		renderBackground(poseStack);
		super.render(poseStack, mouseX, mouseY, delta);
		renderTooltip(poseStack, mouseX, mouseY);
	}
	
	private void renderProgress(PoseStack poseStack, int x, int y) {
		if (menu.isCrafting()) {
			for (int i = 0; i < DivineAnvilMenu.TOTAL_PROGRESS_PIXELS; i++) {
				blit(poseStack, x + 90, y + 35, 176, 0, menu.getScaledProgress(), 15);
			}
		}
	}
}
