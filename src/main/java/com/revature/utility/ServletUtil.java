package com.revature.utility;

import com.revature.delegates.LoginDelegate;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

import static com.revature.utility.LoggerSingleton.getLogger;

public class ServletUtil {

    public static String sendError(HttpServletResponse resp, int code, String message) throws IOException {
        resp.setStatus(code);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        StringBuilder sb = new StringBuilder("{\"error\":");
        sb.append(message);
        sb.append("}");
        resp.getWriter().write(message);
        return sb.toString();
    }

    public static JSONObject doJson(HttpServletRequest request) {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            getLogger(LoginDelegate.class).error(e.toString());
        }
        return new JSONObject(jb.toString());

    }
}
