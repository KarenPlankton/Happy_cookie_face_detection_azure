package codeclan.controllers;

import codeclan.models.Photo;
import codeclan.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class PhotoController {

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    private Environment env;

    @GetMapping(value = "/photomanagementlist")
    public List<Photo> getAllPhotos() {
        System.out.println("Got a request, returning all photos");
        return photoRepository.findAll();

    }

    @GetMapping(value = "/photosdetails/{id}")
    public Optional<Photo> getPhoto(@PathVariable Long id) {
        return photoRepository.findById(id);
    }

    @GetMapping(value = "/photosbinary/{id}")
    public ResponseEntity<byte[]>  getPhotoBinary(@PathVariable Long id) throws IOException {

        AtomicReference<String> value = new AtomicReference<>();

        byte[]  data;

        if(this.getPhoto(id).isPresent()) {
            this.getPhoto(id).ifPresent(
                    photo -> {
                        String photoName = photo.getImageName();
                        value.set(photoName);
                    }); }
        try {

            String realpath = env.getProperty("imageDestination") + "/"
                    + value.get();
            Path path = Paths.get(realpath);
            data = Files.readAllBytes(path);
            // .readAllBytes needs exception
        } catch (Exception e) {
            data=null;
            e.printStackTrace();
        }

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);

    }

    @GetMapping(value = "/photodelete/{id}")
    public RedirectView deletePhoto(@PathVariable Long id) {
        if(this.getPhoto(id).isPresent()){
            // deleting photo file
            this.getPhoto(id).ifPresent(
                    photo -> {
                        String photoName=photo.getImageName();
                        try
                        {
                            File f= new File(env.getProperty("imageDestination")+"/"
                                    +photoName);           //file to be delete
                            if(f.delete())                      //returns Boolean value
                            {
                                System.out.println(f.getName() + " deleted");   //getting and printing the file name
                            }
                            else
                            {
                                System.out.println("failed");
                            }
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
            );
            //deleting db record
            photoRepository.deleteById(id);

        }
        return new RedirectView("http://localhost:3100/photomanagement.html" );
    }

    @PostMapping(value = "/photoImageUpload")
    public RedirectView handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        String photoOriginalName=file.getOriginalFilename();
        Photo newPhotoModel= new Photo(photoOriginalName);
        photoRepository.save(newPhotoModel);
        try {
            File newPhoto = new File(env.getProperty("imageDestination")+"/"
                    +photoOriginalName);
            System.out.println("Writing file to "+ env.getProperty("imageDestination")+"/"
                    +photoOriginalName);
            OutputStream os = new FileOutputStream(newPhoto);
            os.write(file.getBytes());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return new RedirectView("http://localhost:3100/photomanagement.html" );
    }



}
