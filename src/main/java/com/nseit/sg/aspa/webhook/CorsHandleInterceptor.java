package com.nseit.sg.aspa.webhook;

import com.blade.mvc.RouteContext;
import com.blade.mvc.handler.RouteHandler;
import com.blade.security.web.cors.CorsConfiger;

public class CorsHandleInterceptor implements RouteHandler{
    private CorsConfiger corseConfiger;

	@Override
	public void handle(RouteContext context) {
		context.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        context.header("Access-Control-Allow-Origin", "*");
        context.header("Access-Control-Allow-Headers", corseConfiger.ALL);
        context.status(204);
		
	}
    
}