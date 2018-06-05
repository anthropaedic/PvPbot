package anthropaedic.http;

import java.util.Map;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;

public interface UriResponder 
{

    public Response get(UriResource uriResource, Map<String, String> urlParams, IHTTPSession session);
    public Response post(UriResource uriResource, Map<String, String> urlParams, IHTTPSession session);
}
