package kml.matchers;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import kml.Constants;
import kml.Utils;

/**
 * @website https://krothium.com
 * @author DarkLBP
 */
public class HasJoinedMatcher implements URLMatcher{
    private final String hasURL = "https://sessionserver.mojang.com/session/minecraft/hasJoined";
    private final URL url;
    
    public HasJoinedMatcher(URL url){
        this.url = url;
    }
    @Override
    public boolean match(){
        return (this.url.toString().contains(hasURL) && this.url.getQuery() != null);
    }
    @Override
    public URLConnection handle(){
        if ((this.url.toString().contains(hasURL) && this.url.getQuery() != null)){
            URL remoteURL;
            if (Constants.USE_HTTPS){
                remoteURL = Utils.stringToURL("https://mc.krothium.com/server/hasJoined?" + this.url.getQuery());
            } else {
                remoteURL = Utils.stringToURL("http://mc.krothium.com/server/hasJoined?" + this.url.getQuery());
            }
            try{
                return remoteURL.openConnection();
            } catch (IOException ex) {
                return null;
            }
        }
        return null;
    }
}
