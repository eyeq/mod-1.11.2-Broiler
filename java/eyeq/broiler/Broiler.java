package eyeq.broiler;

import eyeq.util.client.renderer.ResourceLocationFactory;
import eyeq.util.client.resource.ULanguageCreator;
import eyeq.util.client.resource.lang.LanguageResourceManager;
import eyeq.util.common.registry.UEntityRegistry;
import eyeq.util.world.biome.BiomeUtils;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import eyeq.broiler.entity.passive.EntityBroiler;
import eyeq.broiler.client.renderer.entity.RenderBroiler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.io.File;
import java.util.List;

import static eyeq.broiler.Broiler.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
public class Broiler {
    public static final String MOD_ID = "eyeq_broiler";

    @Mod.Instance(MOD_ID)
    public static Broiler instance;

    private static final ResourceLocationFactory resource = new ResourceLocationFactory(MOD_ID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        registerEntities();
        if(event.getSide().isServer()) {
            return;
        }
        registerEntityRenderings();
        createFiles();

    }

    public static void registerEntities() {
        UEntityRegistry.registerModEntity(resource, EntityBroiler.class, "Broiler", 0, instance, 0xFACEBE, 0xF5AF99);
        List<Biome> biomes = BiomeUtils.getSpawnBiomes(EntityChicken.class, EnumCreatureType.CREATURE);
        EntityRegistry.addSpawn(EntityBroiler.class, 1, 20, 40, EnumCreatureType.CREATURE, biomes.toArray(new Biome[0]));
    }

    public static void registerEntityRenderings() {
        RenderingRegistry.registerEntityRenderingHandler(EntityBroiler.class, RenderBroiler::new);
    }

    public static void createFiles() {
        File project = new File("../1.11.2-Broiler");

        LanguageResourceManager language = new LanguageResourceManager();

        language.register(LanguageResourceManager.EN_US, EntityBroiler.class, "Processed Poultry");
        language.register(LanguageResourceManager.JA_JP, EntityBroiler.class, "加工済みチキン");

        ULanguageCreator.createLanguage(project, MOD_ID, language);
    }
}
