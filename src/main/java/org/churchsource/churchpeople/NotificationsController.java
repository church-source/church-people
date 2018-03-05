package org.churchsource.churchpeople;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notifications")
public class NotificationsController {

  
  @PersistenceContext
  private EntityManager entityManager;
  
  @GetMapping("/{id}")
  public String index() {
      return "Greetings from Spring!";
  }
  
  @RequestMapping(method = RequestMethod.POST)
  public String printNotification(
      @RequestParam("X-Goog-Channel-ID") String channelID,
      @RequestParam("X-Goog-Resource-ID") String resourceID,
      @RequestParam("X-Goog-Resource-URI") String resourceURI,
      @RequestParam("X-Goog-Resource-State") String resourceState) {
      return "ChannelID" + channelID + "\r\n" +
          "resourceID" + resourceID + "\r\n" +
          "resourceURI" + resourceURI + "\r\n" +
          "resourceState" + resourceState + "\r\n";
  }
}
