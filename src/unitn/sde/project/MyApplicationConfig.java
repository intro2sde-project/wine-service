package unitn.sde.project;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("sde")
public class MyApplicationConfig extends ResourceConfig {
    public MyApplicationConfig () {
        packages("unitn.sde.project");
    }
}