package com.lilithsthrone.game.slavery;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SlavePermissionSettingMap {
    protected Map<SlavePermission, Set<SlavePermissionSetting>> slavePermissionSettings;

    public SlavePermissionSettingMap() {
        slavePermissionSettings = new HashMap<>();
        for(SlavePermission permission : SlavePermission.values()) {
            slavePermissionSettings.put(permission, new HashSet<>());
            for(SlavePermissionSetting setting : permission.getSettings()) {
                if(setting.isDefaultValue()) {
                    slavePermissionSettings.get(permission).add(setting);
                }
            }
        }
    }

    public boolean addSlavePermissionSetting(SlavePermission permission, SlavePermissionSetting setting) {
        if(permission.isMutuallyExclusiveSettings()) {
            slavePermissionSettings.get(permission).clear();
        }
        return slavePermissionSettings.get(permission).add(setting);
    }

    public boolean removeSlavePermissionSetting(SlavePermission permission, SlavePermissionSetting setting) {
        if(permission.isMutuallyExclusiveSettings()) {
            System.err.println("You cannot remove a setting from a mutually exclusive settings list!");
            return false;
        }
        return slavePermissionSettings.get(permission).remove(setting);
    }

    public Map<SlavePermission, Set<SlavePermissionSetting>> getSlavePermissionSettings() {
        return slavePermissionSettings;
    }
}
