package de.lumix.lumcraftserver.mysql;

import de.lumix.lumcraftserver.LumCraftServer;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MySqlGetter {

    private LumCraftServer plugin;

    public MySqlGetter(LumCraftServer plugin) {
        this.plugin = plugin;
    }

    public void createTable() {
        PreparedStatement ps;

        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `playerdata` (`ID` INT(11) NOT NULL AUTO_INCREMENT , `McName` VARCHAR(255) NOT NULL , `McUUID` VARCHAR(255) NOT NULL , `UsrCoins` INT(255) NOT NULL , `UsrVerify` TINYINT(1) NOT NULL , `UsrToken` VARCHAR(255) NOT NULL , `EvWins` INT NOT NULL , `EvLooses` INT NOT NULL , PRIMARY KEY (`ID`)) ENGINE = InnoDB;");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(Player player) {
        PreparedStatement ps;
        PreparedStatement ps2;

        try {
            UUID uuid = player.getUniqueId();
            if (!exists(uuid)) {
                ps2 = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INTO `PlayerData` (`McName` ,`McUUID` ,`UsrCoins`) VALUES (?,?,?)");
                ps2.setString(1, player.getName());
                ps2.setString(2, uuid.toString());
                ps2.setInt(3, 1000);
                ps2.executeUpdate();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(UUID uuid) {
        PreparedStatement ps;

        try {
            ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM `PlayerData` WHERE `McUUID` = ?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addCoins(UUID uuid, int points) {
        PreparedStatement ps;

        try {
            ps = plugin.SQL.getConnection().prepareStatement("UPDATE `PlayerData` SET `UsrCoins` = ? WHERE `McUUID` = ?");
            ps.setInt(1, (getCoins(uuid) + points));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCoins(UUID uuid) {
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = plugin.SQL.getConnection().prepareStatement("SELECT `UsrCoins` FROM `PlayerData` WHERE `McUUID` = ?");
            ps.setString(1, uuid.toString());
            rs = ps.executeQuery();
            int coins = 0;
            if (rs.next()) {
                coins = rs.getInt("COINS");
            }
            return coins;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getVerify(UUID uuid) {
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = plugin.SQL.getConnection().prepareStatement("SELECT `UsrVerify` FROM `PlayerData` WHERE `McUUID` = ?");
            ps.setString(1, uuid.toString());
            rs = ps.executeQuery();
            int Verify = 0;
            if (rs.next()) {
                Verify = rs.getInt("Verify");
            }
            return Verify;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
