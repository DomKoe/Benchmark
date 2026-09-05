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

// Source dimension not otherwise exercised by any securecookie test case: the flag-determining
// value is relayed through the HttpSession between doGet and doPost.
@WebServlet(value = "/securecookie-00/BenchmarkTest02767")
public class BenchmarkTest02767 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession()
                .setAttribute("BenchmarkTest02767_flag", request.getHeader("BenchmarkTest02767"));
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String flag = (String) request.getSession().getAttribute("BenchmarkTest02767_flag");
        boolean secure = !"1".equals(flag);

        javax.servlet.http.Cookie cookie =
                new javax.servlet.http.Cookie("BenchmarkTest02767", "session-token");
        cookie.setSecure(secure);
        cookie.setHttpOnly(true);
        cookie.setPath(request.getRequestURI());
        response.addCookie(cookie);

        response.getWriter()
                .println("Created cookie: 'BenchmarkTest02767' with secure flag set to: " + secure);
    }
}
