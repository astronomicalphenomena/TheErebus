package erebus.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelStone4 extends ModelBase {

	private final ModelRenderer stone4, stone1, stone2, stone3;

	public ModelStone4() {
		textureWidth = 256;
		textureHeight = 128;

		stone4 = new ModelRenderer(this, 160, 30);
		stone4.addBox(-1F, -1F, -2F, 3, 3, 3);
		stone4.setRotationPoint(-2F, -4F, 5F);
		stone4.setTextureSize(64, 32);
		stone4.mirror = true;
		setRotation(stone4, 0.7807508F, -0.7261189F, 0.3346075F);
		stone1 = new ModelRenderer(this, 160, 0);
		stone1.addBox(-1F, -1F, -1F, 4, 4, 4);
		stone1.setRotationPoint(0F, -12F, -2F);
		stone1.setTextureSize(64, 32);
		stone1.mirror = true;
		setRotation(stone1, 0.5711987F, 0.3046393F, -0.1903996F);
		stone2 = new ModelRenderer(this, 160, 10);
		stone2.addBox(-1F, -2F, -2F, 3, 3, 3);
		stone2.setRotationPoint(-4F, -4F, -3F);
		stone2.setTextureSize(64, 32);
		stone2.mirror = true;
		setRotation(stone2, -0.4089647F, 0F, 0.2602503F);
		stone3 = new ModelRenderer(this, 160, 18);
		stone3.addBox(-2F, -2F, -2F, 5, 5, 5);
		stone3.setRotationPoint(4F, 1F, -1F);
		stone3.setTextureSize(64, 32);
		stone3.mirror = true;
		setRotation(stone3, 0F, -0.4089647F, 0.3892394F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void renderAll() {
		stone4.render(0.0625F);
		stone1.render(0.0625F);
		stone2.render(0.0625F);
		stone3.render(0.0625F);
	}
}