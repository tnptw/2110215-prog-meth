package entity;

import entity.base.Consumable;
import entity.base.Entity;
import entity.base.Interactable;
import entity.base.Updatable;
import exception.IllegalValueException;
import logic.GameController;
import logic.Sprites;

public class TrashCompactor extends Entity implements Interactable, Consumable, Updatable {
    // Field
    private int cooldown;

    // Constructor
    public TrashCompactor() {
        this.cooldown = 0;
    }

    // Methods
    @Override
    public int getSymbol() {
        if (cooldown == 0) {
            return Sprites.COMPACTOR_ON;
        }
        return Sprites.COMPACTOR_OFF;
    }

    @Override
    public boolean interact(Entity e) {
        return isBox(e) && cooldown == 0;
    }

    @Override
    public boolean consume(Entity e) {
        if (isBox(e) && cooldown == 0) {
            cooldown = GameController.MAX_COOLDOWN_TIME;
            return true;
        }
        return false;
    }

    @Override
    public void update() throws IllegalValueException {
        if (cooldown > 0) {
            cooldown--;
        } else if (cooldown < 0) {
            throw new IllegalValueException();
        }
    }

    @Override
    public void valueCorrection() {
        cooldown = 0;
    }

    // Getter & Setter
    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }
}
