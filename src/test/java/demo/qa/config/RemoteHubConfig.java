package demo.qa.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:Config/remoteHub.properties")
public interface RemoteHubConfig extends Config {
    String login();
    String password();
    String urlRemoteHub();
    String urlTestedSite();
}
