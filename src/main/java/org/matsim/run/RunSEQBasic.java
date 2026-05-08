package org.matsim.run;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.simwrapper.SimWrapperModule;


public class RunSEQBasic {

    public static void main(String[] args) {
        // 1. 載入 Config，必須包含 MultiModeDrtConfigGroup 與 DvrpConfigGroup
        // args[0] 應為 config.xml 的路徑
        String configFile = args.length > 0 ? args[0] : "scenarios/SEQ/SEQ_Basic.xml";

        Config config = ConfigUtils.loadConfig(configFile);

        // 2. 建立 Scenario
        Scenario scenario = ScenarioUtils.loadScenario(config);

        // 3. 建立 Controler
        Controler controler = new Controler(scenario);
        controler.addOverridingModule(new SimWrapperModule());


        // 5. 啟動模擬
        controler.run();
    }
}