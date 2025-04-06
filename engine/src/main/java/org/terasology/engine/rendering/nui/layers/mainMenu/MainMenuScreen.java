// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.engine.rendering.nui.layers.mainMenu;

import org.terasology.engine.core.GameEngine;
import org.terasology.engine.core.NonNativeJVMDetector;
import org.terasology.engine.core.modes.StateLoading;
import org.terasology.engine.core.TerasologyEngine;

import org.terasology.engine.i18n.TranslationSystem;
import org.terasology.engine.identity.storageServiceClient.StorageServiceWorker;
import org.terasology.engine.identity.storageServiceClient.StorageServiceWorkerStatus;
import org.terasology.engine.rendering.nui.animation.MenuAnimationSystems;
import org.terasology.engine.rendering.nui.layers.mainMenu.settings.PlayerSettingsScreen;
import org.terasology.engine.rendering.nui.layers.mainMenu.settings.SettingsMenuScreen;
import org.terasology.nui.WidgetUtil;
import org.terasology.nui.widgets.UILabel;
import org.terasology.engine.registry.In;
import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.engine.version.TerasologyVersion;
import org.terasology.engine.network.NetworkMode;

import org.terasology.engine.network.NetworkSystem;
import org.terasology.engine.network.JoinStatus;
import org.terasology.engine.context.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMenuScreen extends CoreScreenLayer {

    private static final Logger logger = LoggerFactory.getLogger(MainMenuScreen.class);
    @In
    private GameEngine engine;

    @In
    private StorageServiceWorker storageService;

    @In
    private TranslationSystem translationSystem;
    
    @In
    private NetworkSystem networkSystem;
    @In
    private Context context;

    @Override
    public void initialise() {

        setAnimationSystem(MenuAnimationSystems.createDefaultSwipeAnimation());

        UILabel versionLabel = find("version", UILabel.class);
        versionLabel.setText(TerasologyVersion.getInstance().getHumanVersion());

        UILabel jvmWarningLabel = find("nonNativeJvmWarning", UILabel.class);
        jvmWarningLabel.setVisible(NonNativeJVMDetector.JVM_ARCH_IS_NONNATIVE);

        SelectGameScreen selectScreen = getManager().createScreen(SelectGameScreen.ASSET_URI, SelectGameScreen.class);

        UniverseWrapper universeWrapper = new UniverseWrapper();
/*
        WidgetUtil.trySubscribe(this, "start", button -> {
            logger.info("Start Game button clicked");
            universeWrapper.setLoadingAsServer(false);
            selectScreen.setUniverseWrapper(universeWrapper);
            universeWrapper.setServerAddress("localhost");  // Set the server address to local
            universeWrapper.setNetworkMode(NetworkMode.CLIENT);  // Set the mode to client
            //triggerForwardAnimation(selectScreen);
            
            engine.changeState(new StateLoading(joinStatus));
        });
*/
    WidgetUtil.trySubscribe(this, "start", button -> {
        logger.info("Start Game button clicked");

        JoinStatus joinStatus;
        try {
            joinStatus = networkSystem.join("localhost", 25777);
        } catch (InterruptedException e) {
            logger.error("Failed to join the server due to interruption", e);
            return;
        }

        engine.changeState(new StateLoading(joinStatus));
    });


        WidgetUtil.trySubscribe(this, "settings", button -> triggerForwardAnimation(SettingsMenuScreen.ASSET_URI));
        WidgetUtil.trySubscribe(this, "extras", button -> triggerForwardAnimation(ExtrasMenuScreen.ASSET_URI));
        WidgetUtil.trySubscribe(this, "exit", button -> engine.shutdown());
        WidgetUtil.trySubscribe(this, "storageServiceAction",
                widget -> triggerForwardAnimation(PlayerSettingsScreen.ASSET_URI));
    }

    @Override
    public void onOpened() {
        super.onOpened();
        getAnimationSystem().skip();
    }

    @Override
    protected boolean isEscapeToCloseAllowed() {
        return false;
    }

    @Override
    public boolean isLowerLayerVisible() {
        return false;
    }
}


