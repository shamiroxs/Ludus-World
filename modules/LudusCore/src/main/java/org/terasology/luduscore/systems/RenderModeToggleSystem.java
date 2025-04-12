package org.terasology.luduscore.systems;

import org.terasology.engine.entitySystem.entity.EntityRef;
import org.terasology.engine.entitySystem.systems.BaseComponentSystem;
import org.terasology.engine.entitySystem.systems.RegisterSystem;
import org.terasology.engine.input.BindButtonEvent;
import org.terasology.engine.logic.players.LocalPlayer;
import org.terasology.engine.registry.In;
import org.terasology.engine.rendering.nui.NUIManager;
import org.terasology.gestalt.entitysystem.event.ReceiveEvent;

import org.terasology.input.ButtonState;

import org.terasology.luduscore.components.RenderSettingsComponent;
import org.terasology.luduscore.rendering.TerrainRenderMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

@RegisterSystem
public class RenderModeToggleSystem extends BaseComponentSystem {

    private static final Logger logger = LoggerFactory.getLogger(RenderModeToggleSystem.class);

    private TerrainRenderMode currentMode = TerrainRenderMode.BLOCKY;

    @In
    private LocalPlayer localPlayer;
    
    @In
    private NUIManager nuiManager;

    @ReceiveEvent
    public void onToggleRenderMode(ToggleRenderModeButton event, EntityRef entity) {
        if (event.getState() == ButtonState.DOWN && localPlayer.getCharacterEntity().equals(entity)) {
            currentMode = (currentMode == TerrainRenderMode.BLOCKY)
                ? TerrainRenderMode.SMOOTH
                : TerrainRenderMode.BLOCKY;

            // In a real system, you'd notify the renderer or config
            logger.info("Switched terrain render mode to: {}", currentMode);
            
            String screenId = "LudusCore:exploreModePopup";
            nuiManager.pushScreen(screenId);
            
            new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // Use UI thread to safely interact with NUI
                nuiManager.closeScreen(screenId);
            }
        }, 3000);
            
            event.consume();
        }
    }

    public TerrainRenderMode getCurrentMode() {
        return currentMode;
    }
    
       
}
