package com.pluralsight.photoclone;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// this mean that this class is a controller. tells spring that this class is a controller and we to instantiate it
@RestController
public class PhotosController {

  private Map<String, Photo> db = new HashMap<>(){{
    put("1", new Photo("1", "photo1.jpg"));
    put("2", new Photo("2", "photo2.jpg"));
    put("3", new Photo("3", "photo3.jpg"));
  }};

    //By default we have a get request, and when we end up in this endpoint localhost:8080/,
    //we need to return a string that says "hello world"
    //@GetMapping("/")  Means we need to make a request to this endpoint
    @GetMapping("/")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/photos")
   public Collection<Photo> getPhotos() {
    return db.values();
   }

    @GetMapping("/photos/{id}")
    //@PathVariable means that we have a parameter in the endpoint
    // means when we pass a value in the url like localhost:8080/photos/1,
    // we want to get back the photo with id 1 by calling the method
    public Photo getPhoto(@PathVariable String id) throws RepsonseStatusException {
        Photo photo = db.get(id);
        if (photo == null) {
            throw new RepsonseStatusException(HttpStatus.NOT_FOUND);
        }
        return photo;
    }

    @DeleteMapping("/photos/{id}")
    public void deletePhoto(@PathVariable String id) throws RepsonseStatusException {
        Photo photo = db.remove(id);
        if (photo == null) throw new RepsonseStatusException(HttpStatus.NOT_FOUND);
    }

    //@RequestBody means that we are expecting a json in the body of the request
    @PostMapping("/photos")
    //@Valid means that we want to validate the request. we require it to have a value and not null
    public Photo addPhoto(@RequestBody  Photo photo) {
        photo.setId(UUID.randomUUID().toString());
        db.put(photo.getId(), photo);
        return photo;
    }
}
