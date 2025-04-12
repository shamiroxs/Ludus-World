package org.terasology.luduscore.components;

import org.terasology.gestalt.entitysystem.component.Component;
import org.terasology.luduscore.rendering.TerrainRenderMode;

public class RenderSettingsComponent implements Component<RenderSettingsComponent> {
    public TerrainRenderMode terrainMode = TerrainRenderMode.BLOCKY;

    @Override
    public void copyFrom(RenderSettingsComponent other) {
        this.terrainMode = other.terrainMode;
    }
}
