package org.terasology.luduscore.ui;

import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.widgets.UILabel;

public class ExploreModePopupScreen extends CoreScreenLayer {
    @Override
    public void initialise() {
        UILabel label = find("messageLabel", UILabel.class);
        if (label != null) {
            label.setText("Switching Mode");
        }
    }
}
