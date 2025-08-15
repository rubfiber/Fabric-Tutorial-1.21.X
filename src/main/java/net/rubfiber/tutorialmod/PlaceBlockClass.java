package net.rubfiber.tutorialmod;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.block.Block;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;


class MyServerHolder {
    private static MinecraftServer server;

    public static void init() {
        ServerLifecycleEvents.SERVER_STARTED.register(s -> server = s);
        ServerLifecycleEvents.SERVER_STOPPED.register(s -> server = null);
    }

    public static MinecraftServer getServer() {
        return server;
    }
}


public class PlaceBlockClass {
   @Contract("_ -> new")
   public static double @NotNull [] getPlayerPosition(String name) {
       MinecraftServer server = MyServerHolder.getServer();
       ServerPlayerEntity player = server.getPlayerManager().getPlayer(name);
       assert player != null;
       return new double[] {player.getBlockX(), player.getBlockY(), player.getBlockZ()};
   }

    public static void placeBlock(double[] pos, Block block, @NotNull World world) {
        BlockState blockstate = block.getDefaultState();
        BlockPos blockPos = new BlockPos((int) pos[0], (int) pos[1], (int) pos[2]);
        world.setBlockState(blockPos, blockstate, 3);
    }

    public static void placeBlockAtPlayerLocation() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            var player = handler.getPlayer();
            placeBlock(
                    new double[]{player.getBlockX(), player.getBlockY(), player.getBlockZ()},
                    Blocks.BELL,
                    server.getOverworld()
            );
            System.out.println("Placed bell at " + player.getBlockPos());
        });
    }
}