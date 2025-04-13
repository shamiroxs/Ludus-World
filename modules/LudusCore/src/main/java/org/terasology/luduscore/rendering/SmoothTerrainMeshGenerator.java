package org.terasology.luduscore.rendering;

import org.terasology.engine.rendering.primitives.ChunkMesh;
import org.terasology.engine.world.ChunkView;
import org.terasology.engine.rendering.assets.mesh.Mesh;
import org.terasology.engine.rendering.assets.mesh.MeshBuilder;
import org.terasology.engine.rendering.primitives.BlockMeshGenerator;
import org.terasology.engine.rendering.logic.MeshRenderer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.joml.Vector3f;

public class SmoothTerrainMeshGenerator implements BlockMeshGenerator {

    private static final Logger logger = LoggerFactory.getLogger(SmoothTerrainMeshGenerator.class);

    @Override
    public void generateChunkMesh(ChunkView chunkView, ChunkMesh chunkMesh, int x, int y, int z) {
    
        logger.info("Generating SMOOTH terrain mesh at ({}, {}, {})", x, y, z);

        MeshBuilder builder = new MeshBuilder();

        builder.addVertex(new Vector3f(x, y, z));
        builder.addVertex(new Vector3f(x + 1, y, z));
        builder.addVertex(new Vector3f(x, y + 1, z));

        builder.addIndices(0, 1, 2);

    // Now convert the builder into a mesh, usually to preview/test it
        Mesh mesh = builder.build();

    // ❗ But this mesh is not injected into the chunkMesh
    // Instead, it’s for previewing or returning via `getStandaloneMesh()`

        
    }

    @Override
    public Mesh getStandaloneMesh() {
        MeshBuilder builder = new MeshBuilder();
        builder.addVertex(new Vector3f(0, 0, 0));
        builder.addVertex(new Vector3f(1, 0, 0));
        builder.addVertex(new Vector3f(0, 1, 0));
        builder.addIndices(0, 1, 2);
        return builder.build();
    }
}
