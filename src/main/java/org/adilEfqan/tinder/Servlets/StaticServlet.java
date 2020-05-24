package org.adilEfqan.tinder.Servlets;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Log4j2
public class StaticServlet extends HttpServlet {

  private final String subPath;

  public StaticServlet(String subPath) {
    //where static file located
    this.subPath = subPath;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    /**
     * 1. get the file name from the request
     * 2. translate the file name to the object File (Path)
     * 3. read this File
     * 4. write file read to response.outputStream
     */
    // 1
    String filename = req.getPathInfo();
    //String osFileLocation = "src/main/java/templates/";
    String osFileLocation =  "target/classes/templates/";
   // String osFileLocation = StaticServlet.class.getClassLoader().getResource("templates").getPath().replace("/","\\");
    // 2 Full path
    Path path = Paths.get(osFileLocation, subPath, filename);
    log.debug(String.format("Filename-> %s, osFIlelocation-> %s ,full path-> %s",filename,osFileLocation,path));
    //System.out.println(path);
    /**
     * Paths.get("a","b","c")      -> "a/b/c"
     * Paths.get("a/","b","c")     -> "a/b/c"
     * Paths.get("a/","/b","c")    -> "a/b/c"
     * Paths.get("a/","/b","//c")  -> "a/b/c"
     * but:
     * Paths.get("/a","b","c")     -> "/a/b/c"
     */

    try (OutputStream os = resp.getOutputStream()) {
      // 3 & 4
      Files.copy(path, os);
    }
  }
}
