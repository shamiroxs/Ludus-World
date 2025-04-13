package org.terasology.luduscore.systems;

import org.terasology.engine.rendering.primitives.BlockMeshGenerator;
import org.terasology.engine.rendering.primitives.ChunkMesh;
import org.terasology.engine.world.ChunkView;
import org.terasology.luduscore.rendering.SmoothTerrainMeshGenerator;
import org.terasology.luduscore.systems.RenderModeToggleSystem;
import org.terasology.engine.rendering.assets.mesh.Mesh;
import org.terasology.luduscore.rendering.TerrainRenderMode; 

import org.terasology.engine.world.generation.WorldRasterizer;

public class TerrainMeshDispatcher implements BlockMeshGenerator {

    private final SmoothTerrainMeshGenerator smoothMeshGenerator;
    private final RenderModeToggleSystem renderModeToggleSystem;

    public TerrainMeshDispatcher(SmoothTerrainMeshGenerator smoothMeshGenerator,
                                 RenderModeToggleSystem renderModeToggleSystem) {
        this.smoothMeshGenerator = smoothMeshGenerator;
        this.renderModeToggleSystem = renderModeToggleSystem;
    }

    @Override
    public void generateChunkMesh(ChunkView chunkView, ChunkMesh chunkMesh, int x, int y, int z) {
        // Delegate to the appropriate mesh generator based on the current render mode
        if (renderModeToggleSystem.getCurrentMode() == TerrainRenderMode.SMOOTH) {
            smoothMeshGenerator.generateChunkMesh(chunkView, chunkMesh, x, y, z);
        } else {
            // Default blocky mesh generation logic (handled by the engine)
            //chunkMesh.generateChunk(chunkView, x, y, z);
        }
    }

    @Override
    public Mesh getStandaloneMesh() {
        // Return the appropriate standalone mesh based on the current render mode
        if (renderModeToggleSystem.getCurrentMode() == TerrainRenderMode.SMOOTH) {
            return smoothMeshGenerator.getStandaloneMesh();
        } else {
            // No need for a custom mesh here; the default block mesh is used automatically.
            // So, you can either return a placeholder or null.
            return null; // Or just return a simple blocky placeholder mesh if needed
        }
    }
}
