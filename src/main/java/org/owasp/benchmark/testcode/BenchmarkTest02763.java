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

// Sink dimension not otherwise exercised by any securecookie test case: every existing case
// uses the javax.servlet.http.Cookie object; here the Set-Cookie header is built and emitted
// manually via addHeader(), which many tools that only model the Cookie API will miss.
@WebServlet(value = "/securecookie-00/BenchmarkTest02763")
public class BenchmarkTest02763 extends HttpServlet {

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

        boolean insecureRequested = "1".equals(request.getHeader("BenchmarkTest02763"));

        StringBuilder setCookie = new StringBuilder("BenchmarkTest02763=session-token; Path=/");
        if (!insecureRequested) {
            setCookie.append("; Secure");
        }
        setCookie.append("; HttpOnly");
        response.addHeader("Set-Cookie", setCookie.toString());

        response.getWriter()
                .println(
                        "Created cookie via raw Set-Cookie header: '"
                                + org.owasp
                                        .esapi
                                        .ESAPI
                                        .encoder()
                                        .encodeForHTML(setCookie.toString())
                                + "'");
    }
}
