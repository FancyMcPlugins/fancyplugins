package de.oliver.fancysitula.versions.v1_21_3.packets;

import de.oliver.fancysitula.versions.v1_21_3.packets.ClientboundPlayerInfoRemovePacketImpl;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoRemovePacket;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

class ClientboundPlayerInfoRemovePacketImplTest {

    @Test
    void createPacket() {
        List<UUID> uuids = List.of(UUID.randomUUID(), UUID.randomUUID());

        ClientboundPlayerInfoRemovePacketImpl packet = new ClientboundPlayerInfoRemovePacketImpl(uuids);
        ClientboundPlayerInfoRemovePacket vanillaPacket = (ClientboundPlayerInfoRemovePacket) packet.createPacket();

        for (UUID uuid : uuids) {
            assert vanillaPacket.profileIds().contains(uuid);
        }
    }
}