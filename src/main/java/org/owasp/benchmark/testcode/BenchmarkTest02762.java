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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/securecookie-00/BenchmarkTest02762")
public class BenchmarkTest02762 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // A server-side constant, never influenced by request data - contrast with the vulnerable
    // sibling test, where the equivalent decision is attacker-controllable.
    private static final boolean SERVER_ENFORCES_HTTPS = true;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Read (and discard) the same header the vulnerable sibling test uses, to keep the two
        // test cases structurally comparable for a static analysis tool.
        request.getHeader("BenchmarkTest02762");

        javax.servlet.http.Cookie cookie =
                new javax.servlet.http.Cookie("BenchmarkTest02762", "session-token");
        cookie.setSecure(SERVER_ENFORCES_HTTPS);
        cookie.setHttpOnly(true);
        cookie.setPath(request.getRequestURI());
        response.addCookie(cookie);

        response.getWriter()
                .println(
                        "Created cookie: 'BenchmarkTest02762' with secure flag set to: "
                                + SERVER_ENFORCES_HTTPS);
    }
}
