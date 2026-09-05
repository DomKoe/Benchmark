/**
 * OWASP Benchmark Project v1.2
 *
 * <p>This file is part of the Open Web Application Security Project (OWASP) Benchmark Project. For
 * details, please see <a
 * href="https://owasp.org/www-project-benchmark/">https://owasp.org/www-project-benchmark/</a>.
 *
 * <p>The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by the Free Software Foundation, version 2.
 *
 * <p>The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License for more details.
 *
 * @created 2026
 */
package org.owasp.benchmark.testcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet(value = "/securecookie-00/BenchmarkTest02770")
public class BenchmarkTest02770 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // The uploaded part's content is read but discarded; the flag is always the hardcoded
        // constant below.
        Part part = request.getPart("BenchmarkTest02770");
        if (part != null) {
            org.apache.commons.io.IOUtils.toString(part.getInputStream(), "UTF-8");
        }
        boolean secure = !"1".equals("0");

        javax.servlet.http.Cookie cookie =
                new javax.servlet.http.Cookie("BenchmarkTest02770", "session-token");
        cookie.setSecure(secure);
        cookie.setHttpOnly(true);
        cookie.setPath(request.getRequestURI());
        response.addCookie(cookie);

        response.getWriter()
                .println("Created cookie: 'BenchmarkTest02770' with secure flag set to: " + secure);
    }
}
