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
    this.subPath = subPath;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String filename = req.getPathInfo();
    String osFileLocation =  "target/classes/templates/";
    Path path = Paths.get(osFileLocation, subPath, filename);
    log.debug(String.format("Filename-> %s, osFIlelocation-> %s ,full path-> %s",filename,osFileLocation,path));

    try (OutputStream os = resp.getOutputStream()) {
      Files.copy(path, os);
    }
  }
}
