package org.terasology.luduscore.systems;

import org.terasology.engine.input.BindButtonEvent;
import org.terasology.engine.input.RegisterBindButton;
import org.terasology.engine.input.DefaultBinding;
import org.terasology.input.InputType;
import org.terasology.input.Keyboard;

@RegisterBindButton(id = "toggleRenderMode", description = "Toggle Terrain Render Mode", repeating = false)
@DefaultBinding(type = InputType.KEY, id = Keyboard.KeyId.M)
public class ToggleRenderModeButton extends BindButtonEvent {}
