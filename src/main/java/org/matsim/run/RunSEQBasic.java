package org.matsim.run;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.simwrapper.SimWrapperModule;


public class RunSEQBasic {

    public static void main(String[] args) {
        // 1. Loading Config, including  MultiModeDrtConfigGroup and DvrpConfigGroup modules
        // args[0] should be the route of config.xml
        String configFile = args.length > 0 ? args[0] : "scenarios/SEQ/SEQ_Basic.xml";

        Config config = ConfigUtils.loadConfig(configFile);

        // 2. Build Scenario
        Scenario scenario = ScenarioUtils.loadScenario(config);

        // 3. Build Controler
        Controler controler = new Controler(scenario);
        controler.addOverridingModule(new SimWrapperModule());


        // 5. start simulation
        controler.run();
    }
}