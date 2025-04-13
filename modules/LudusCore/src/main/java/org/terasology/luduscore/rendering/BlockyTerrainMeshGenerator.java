package org.terasology.luduscore.rendering;

import org.terasology.engine.world.ChunkView;
import org.terasology.engine.rendering.primitives.ChunkMesh;
import org.terasology.engine.rendering.primitives.BlockMeshGenerator;
import org.terasology.engine.rendering.assets.mesh.Mesh;
import org.terasology.engine.rendering.assets.mesh.MeshBuilder;
import org.terasology.engine.rendering.primitives.Tessellator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.joml.Vector3f;

public class BlockyTerrainMeshGenerator implements BlockMeshGenerator {

    private static final Logger logger = LoggerFactory.getLogger(BlockyTerrainMeshGenerator.class);

    public BlockyTerrainMeshGenerator() {
        logger.info("BlockyTerrainMeshGenerator initialized");
    }

    @Override
    public void generateChunkMesh(ChunkView chunkView, ChunkMesh chunkMesh, int x, int y, int z) {
        logger.info("Generating BLOCKY terrain mesh at ({}, {}, {})", x, y, z);
        // TODO: Add block-style mesh generation logic here
        /*
         MeshBuilder builder = new MeshBuilder();

         builder.addVertex(new Vector3f(0, 0, 0));
         builder.addVertex(new Vector3f(1, 0, 0));
         builder.addVertex(new Vector3f(0, 1, 0));
         builder.addIndices(0, 1, 2);

         chunkMesh.addMeshPart(builder.build());
         */
    }

    @Override
    public Mesh getStandaloneMesh() {
    logger.info("Returning placeholder Mesh from BlockyTerrainMeshGenerator");

    MeshBuilder builder = new MeshBuilder();

    // Add at least 3 vertices to form one triangle (minimum for a valid mesh)
    builder.addVertex(new Vector3f(0, 0, 0));
    builder.addVertex(new Vector3f(1, 0, 0));
    builder.addVertex(new Vector3f(0, 1, 0));

    // Optionally add colors or UVs if needed
    // builder.addColor(...);
    // builder.addTexCoord(...);

    // Add indices to define a triangle
    builder.addIndices(0, 1, 2);

    // Generate the mesh
    return builder.build();
    }

}
