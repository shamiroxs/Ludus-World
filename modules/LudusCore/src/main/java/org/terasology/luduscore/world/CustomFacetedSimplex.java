package org.terasology.luduscore.world;

import org.terasology.core.world.generator.facetProviders.*;
import org.terasology.core.world.generator.rasterizers.*;
import org.terasology.engine.core.SimpleUri;
import org.terasology.engine.entitySystem.entity.EntityRef;
import org.terasology.engine.logic.spawner.FixedSpawner;
import org.terasology.engine.registry.In;
import org.terasology.engine.world.generation.BaseFacetedWorldGenerator;
import org.terasology.engine.world.generation.WorldBuilder;
import org.terasology.engine.world.generator.RegisterWorldGenerator;
import org.terasology.engine.world.generator.plugin.WorldGeneratorPluginLibrary;
import org.joml.Vector2i;
import org.joml.Vector2ic;
import org.joml.Vector3fc;

@RegisterWorldGenerator(id = "CustomFacetedSimplex", displayName = "Custom Faceted Simplex")
public class CustomFacetedSimplex extends BaseFacetedWorldGenerator {

    private static final Vector2ic SAFE_SPAWN = new Vector2i(10, 10);  // use inside-safe bounds
    private final FixedSpawner spawner = new FixedSpawner(SAFE_SPAWN.x(), SAFE_SPAWN.y());

    @In
    private WorldGeneratorPluginLibrary pluginLibrary;

    public CustomFacetedSimplex(SimpleUri uri) {
        super(uri);
    }

    @Override
    public Vector3fc getSpawnPosition(EntityRef entity) {
        return spawner.getSpawnPosition(getWorld(), entity);
    }

    @Override
    protected WorldBuilder createWorld() {    
        int seaLevel = 15;

        return new WorldBuilder(pluginLibrary)
                .setSeaLevel(seaLevel)
                .addProvider(new SeaLevelProvider(seaLevel))
                .addProvider(new SimplexHumidityProvider())
                .addProvider(new SimplexSurfaceTemperatureProvider())
                .addProvider(new SimplexBaseSurfaceProvider())
                .addProvider(new SimplexRiverProvider())
                .addProvider(new SimplexRoughnessProvider())
                .addProvider(new BiomeProvider())
                .addProvider(new SurfaceToDensityProvider())
                .addProvider(new DensityNoiseProvider())
                .addProvider(new DefaultFloraProvider())
                .addProvider(new DefaultTreeProvider())
                .addProvider(new SpawnPlateauProvider(SAFE_SPAWN))
                .addRasterizer(new SolidRasterizer())
                .addPlugins()
                .addRasterizer(new FloraRasterizer())
                .addRasterizer(new TreeRasterizer())
                .addRasterizer(new SunlightRasterizer(-20));
    }
}
