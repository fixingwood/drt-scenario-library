package org.matsim.utils;

import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.NetworkWriter;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.network.algorithms.NetworkCleaner;
import org.matsim.core.network.io.MatsimNetworkReader;
import org.matsim.core.scenario.ScenarioUtils;

public class PrepareNetwork {
    public static void main(String[] args) {
        Scenario scenario = ScenarioUtils.createScenario(ConfigUtils.createConfig());
        Network network = scenario.getNetwork();
        new MatsimNetworkReader(network).readFile("scenarios/kelheim-kexi/SEQ_network_modified.xml.gz");
        new NetworkCleaner().run(network);
        new NetworkWriter(network).write("scenarios/kelheim-kexi/SEQ_network_cleaned.xml.gz");

    }
}
