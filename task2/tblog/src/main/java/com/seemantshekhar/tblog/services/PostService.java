package com.seemantshekhar.tblog.services;

import com.seemantshekhar.tblog.models.Post;
import com.seemantshekhar.tblog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

//Service class for Posts
@Service
@Transactional
public class PostService {

    /**
     * Update the absolute path of "media" in accordance with where you cloned the project.
     * Media folder lies in tblog folder.
     * Example: if you have cloned fossee-task on your desktop, then the absolute path will be:
     * "/home/pc_name/Desktop/fossee-task/task-2/tblog/media/";
     */
    final static String POST_IMAGE_PATH = "absolute_path_of_media_folder";
    final static String RELATIVE_PATH = "/media/"; // No need to change this path

    @Autowired
    private PostRepository postRepository;

    //Method to find all posts by page
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    //Method to save a post
    public void save(Post post) {
        postRepository.save(post);
    }

    //Method to retrieve a post from DB by id
    public Post get(long id) {
        return postRepository.findById(id).get();
    }

    //Method to deleta a post by id
    public void delete(long id) {
        postRepository.deleteById(id);
    }

    /**
     * Method to save image in media folder
     *
     * @param file image file to store
     * @return path where image is stored
     */
    public String saveImage(MultipartFile file) {

        try {
            String format = ".jpg"; //default format if file format is not specified

            format = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String sName = POST_IMAGE_PATH + Calendar.getInstance().getTimeInMillis() + format;
            String name = RELATIVE_PATH + Calendar.getInstance().getTimeInMillis() + format;

            Path location = Paths.get(POST_IMAGE_PATH);
            Files.copy(file.getInputStream(), location.resolve(sName));
            return name;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
