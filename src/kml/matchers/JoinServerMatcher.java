package kml.matchers;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import kml.Utils;

/**
 * @website https://krothium.com
 * @author DarkLBP
 */
public class JoinServerMatcher implements URLMatcher{
    private final String joinURL = "http://session.minecraft.net/game/joinserver.jsp";
    private final URL url;
    
    public JoinServerMatcher(URL url){
        this.url = url;
    }
    @Override
    public boolean match(){
        return (this.url.toString().contains(joinURL) && this.url.getQuery() != null);
    }
    @Override
    public URLConnection handle(){
        if ((this.url.toString().contains(joinURL) && this.url.getQuery() != null)){
            URL remoteURL = Utils.stringToURL("http://mc.krothium.com/server/joinserver?" + this.url.getQuery());
            try{
                return remoteURL.openConnection();
            } catch (IOException ex) {
                return null;
            }
        }
        return null;
    }
}
