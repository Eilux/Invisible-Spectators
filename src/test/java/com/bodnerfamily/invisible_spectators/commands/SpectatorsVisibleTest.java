package com.bodnerfamily.invisible_spectators.commands;

import com.bodnerfamily.invisible_spectators.StateToggle;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class SpectatorsVisibleTest {
    private static class StateToggleTester implements StateToggle {
        public boolean curState;

        @Override
        public void setState(boolean state) {
            this.curState = state;
        }
    }

    private static class CommandSenderTester implements CommandSender {
        public String message;

        @Override
        public void sendMessage(String s) {
            this.message = s;
        }

        @Override
        public void sendMessage(String[] strings) {

        }

        @Override
        public Server getServer() {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public Spigot spigot() {
            return null;
        }

        @Override
        public boolean isPermissionSet(String s) {
            return false;
        }

        @Override
        public boolean isPermissionSet(Permission permission) {
            return false;
        }

        @Override
        public boolean hasPermission(String s) {
            return false;
        }

        @Override
        public boolean hasPermission(Permission permission) {
            return false;
        }

        @Override
        public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b) {
            return null;
        }

        @Override
        public PermissionAttachment addAttachment(Plugin plugin) {
            return null;
        }

        @Override
        public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b, int i) {
            return null;
        }

        @Override
        public PermissionAttachment addAttachment(Plugin plugin, int i) {
            return null;
        }

        @Override
        public void removeAttachment(PermissionAttachment permissionAttachment) {

        }

        @Override
        public void recalculatePermissions() {

        }

        @Override
        public Set<PermissionAttachmentInfo> getEffectivePermissions() {
            return null;
        }

        @Override
        public boolean isOp() {
            return false;
        }

        @Override
        public void setOp(boolean b) {

        }
    }

    @Test
    public void testOnCommand() {
        StateToggleTester stt = new StateToggleTester();
        CommandSenderTester cst = new CommandSenderTester();

        SpectatorsVisible sv = new SpectatorsVisible(stt);

        {
            stt.curState = false;
            cst.message = "";
            boolean result = sv.onCommand(cst, null, "label", new String[]{"true"});
            assertEquals(true, result);
            assertEquals(false, stt.curState);
            assertEquals("§bSpectators are now visible.", cst.message);
        }

        {
            stt.curState = false;
            cst.message = "";
            boolean result = sv.onCommand(cst, null, "label", new String[]{});
            assertEquals(false, result);
            assertEquals(false, stt.curState);
            assertEquals("§cNo argument provided. Only 'true' and 'false' are valid.", cst.message);
        }
        {
            stt.curState = false;
            cst.message = "";
            boolean result = sv.onCommand(cst, null, "label", new String[]{"false"});
            assertEquals(true, result);
            assertEquals(true, stt.curState);
            assertEquals("§bSpectators are now invisible.", cst.message);
        }

    }
}