package io.github.badnotice.dailyreward.commands;

import io.github.badnotice.dailyreward.inventory.RewardInventory;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.entity.Player;

public final class RewardCommand {

    @Command(
            name = "reward",
            aliases = {
                    "recompensas",
                    "recompensa",
                    "coletar"
            },
            permission = "dailyreward.command.use",
            target = CommandTarget.PLAYER
    )
    public void handleRewardCommand(Context<Player> context) {
        RewardInventory rewardInventory = new RewardInventory().init();
        rewardInventory.openInventory(context.getSender());
    }

}
