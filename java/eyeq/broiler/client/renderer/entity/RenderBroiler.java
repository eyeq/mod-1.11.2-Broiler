package eyeq.broiler.client.renderer.entity;

import eyeq.util.client.renderer.EntityRenderResourceLocation;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import static eyeq.broiler.Broiler.MOD_ID;

public class RenderBroiler extends RenderLiving {
    protected static final ResourceLocation textures = new EntityRenderResourceLocation(MOD_ID, "broiler");

    public RenderBroiler(RenderManager renderManager) {
        super(renderManager, new ModelChicken(), 0.3F);
    }

    @Override
    public void doRender(EntityLiving entity, double x, double y, double z, float rotationYaw, float partialTicks) {
        ModelChicken model = (ModelChicken) this.mainModel;
        model.rightWing.isHidden = true;
        model.leftWing.isHidden = true;
        super.doRender(entity, x, y, z, rotationYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return textures;
    }
}
