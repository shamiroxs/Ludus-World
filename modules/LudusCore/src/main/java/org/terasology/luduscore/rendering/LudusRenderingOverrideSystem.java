package org.terasology.luduscore.rendering;

import org.terasology.engine.entitySystem.systems.BaseComponentSystem;
import org.terasology.engine.entitySystem.systems.RegisterMode;
import org.terasology.engine.entitySystem.systems.RegisterSystem;
import org.terasology.engine.registry.In;

import  org.terasology.engine.world.block.BlockManager;
import  org.terasology.engine.world.block.Block;
import org.terasology.engine.world.block.internal.BlockManagerImpl;

import org.terasology.luduscore.systems.RenderModeToggleSystem;
import org.terasology.luduscore.systems.TerrainMeshDispatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@RegisterSystem(RegisterMode.ALWAYS)
public class LudusRenderingOverrideSystem extends BaseComponentSystem {

    private static final Logger logger = LoggerFactory.getLogger(LudusRenderingOverrideSystem.class);

    @In
    private BlockManager blockManager;

    @In
    private SmoothTerrainMeshGenerator smoothMeshGenerator;

    @In
    private RenderModeToggleSystem renderModeToggleSystem;

    private TerrainMeshDispatcher meshDispatcher;

    @Override
    public void postBegin() {
        logger.info("LudusRenderingOverrideSystem postBegin");

        //meshDispatcher = new TerrainMeshDispatcher(smoothMeshGenerator, renderModeToggleSystem);

        //overrideBlockMeshGenerators();
    }

    private void overrideBlockMeshGenerators() {/*
        Block airBlock = blockManager.getBlock(BlockManager.AIR_ID);
    
        List<String> terrainBlockUris = Arrays.asList(
            "CoreAssets:Dirt",
            "CoreAssets:Grass",
            "CoreAssets:Stone",
            "CoreAssets:Snow"
            // Add more as needed
        );

        for (String uri : terrainBlockUris) {
            Block block = blockManager.getBlock(uri);
            if (block != null && block != airBlock) {
                block.setMeshGenerator(meshDispatcher);
                logger.info("Custom mesh generator applied to block: {}", uri);
            } else {
                logger.warn("Block not found or AIR: {}", uri);
            }
        }*/
    }
}
