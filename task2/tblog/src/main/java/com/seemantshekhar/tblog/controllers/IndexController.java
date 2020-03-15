package com.seemantshekhar.tblog.controllers;

import com.seemantshekhar.tblog.models.Post;
import com.seemantshekhar.tblog.models.User;
import com.seemantshekhar.tblog.services.PostService;
import com.seemantshekhar.tblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
public class IndexController {

    @Autowired
    private PostService service;

    @Autowired
    private UserService userService;

    /**
     * Mapping for root "/"
     * @param model to contain posts and currentpage attributes
     * @param page is the page value
     * @return all_posts.html web page.
     */
    @RequestMapping("/")
    public String Index(Model model, @RequestParam(defaultValue = "0")int page){

        Pageable pageable = PageRequest.of(page, 8, Sort.by("date").descending());

        model.addAttribute("posts", service.findAll(pageable));
        model.addAttribute("currentPage",page);

        return "all_posts";
    }


    /**
     * Mapping for add new post "/new"
     * @param model to contain post object as an attribute
     * @return new_post.html web page
     */
    @RequestMapping("/new")
    public String showNewPostForm(Model model){
        Post post = new Post();
        System.out.println("create new called");
        model.addAttribute("post", post);

        return "new_post";
    }

    /**
     *
     * @param cover_image is a multipart file
     * @param post is a model attribute of post object
     * @param model to contain error_text attribute
     * @param bindingResult
     * @return redirects to all_posts.html web page if successful
     * @throws ParseException
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String savePost( @RequestParam("cover-image") MultipartFile cover_image, @ModelAttribute("post") Post post,
                            Model model, BindingResult bindingResult) throws ParseException {
        if(bindingResult.hasErrors()) {
            return "/new_post";
        }
        System.out.println("save new called");
        post.setAuthor(userService.getAuthenticatedUser().getUsername());

        //Checking length of post title
        if(post.getName().length() > 100){
            model.addAttribute("error_text", "Max Size of name is 100 characters.");
            return "/new_post";
        }
        //Checking whether the date is not more than today
        else if(dateCompare(post.getDate())){
            model.addAttribute("error_text", "Enter a valid date.");
            return "/new_post";
        }
        //Checking the size of the image uploaded
        if(cover_image.getSize()> 5242880){
            model.addAttribute("error_text", "Max Image size allowed is 5MB.");
            return "/new_post";
        }

        String imageName = service.saveImage(cover_image); //saving the image in media folder and returning the path of the same
        //checking wheter the path is not null
        if(imageName != null){
            post.setImage(imageName);
        }

        service.save(post); //saving the post

        return "redirect:/posts/";
    }

    /**
     * Date comparing function
     * @param uDate date
     * @return boolean whether after or not
     * @throws ParseException
     */
    public boolean dateCompare(String uDate) throws ParseException {
        SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdfo.parse(uDate);
        Date today = Calendar.getInstance().getTime();
        return d1.after(today);


    }

    //Function to handle MaxUploadSizeExceededException
    @ControllerAdvice
    public class FileUploadExceptionAdvice {

        @ExceptionHandler(MaxUploadSizeExceededException.class)
        public ModelAndView handleMaxSizeException(
                MaxUploadSizeExceededException exc,
                HttpServletRequest request,
                HttpServletResponse response) {

            ModelAndView modelAndView = new ModelAndView("/new_post");
            modelAndView.getModel().put("error_text", "File too large!");
            modelAndView.getModel().put("post", new Post());
            return modelAndView;
        }
    }

    /**
     * Mapping for posts "/posts"
     *@param model to contain posts and currentpage attributes
     *@param page is the page value
     *@return all_posts.html web page.
     */
    @RequestMapping(value = "/posts")
    public String showAllPosts(Model model, @RequestParam(defaultValue = "0")int page){

        Pageable pageable = PageRequest.of(page, 8, Sort.by("date").descending());

        model.addAttribute("posts", service.findAll(pageable));
        model.addAttribute("currentPage",page);

        return "all_posts";
    }

    /**
     * Mapping for view selected post "/posts/{id}"
     * @param model containing id of the post as an attribute
     * @param id id of the post
     * @return selected_post.html web page
     */
    @RequestMapping("/posts/{id}")
    public String selectedPost( Model model,@PathVariable("id") Long id){
        model.addAttribute("selected_post", service.get(id));
        return "selected_post";
    }


    /**
     * Mapping for editing a post "/edit/{id}"
     * @param id id of the post
     * @return ModelAndView with model as model and view as new_post.html web page
     */
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_post");
        Post post = service.get(id);
        mav.addObject("post", post);
        System.out.println(post.getId());
        return mav;
    }


    /**
     * Mapping for saving the edited post "/edit/{id}"
     * @param id id of the post
     * @param post post object
     * @param bindingResult
     * @param model model containing error_text attribute
     * @return redirects to all_posts.html web page
     * @throws ParseException
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editPost(@PathVariable("id") Long id,
                                   @Valid Post post, BindingResult bindingResult,
                                   Model model) throws ParseException {


        Post post_final = service.get(id);
        post_final.setDescription(post.getDescription());
        post_final.setName(post.getName());
        post_final.setDate(post.getDate());
        post_final.setAuthor("seemant");

        if(post.getName().length() > 100){
            model.addAttribute("error_text", "Max Size of name is 100 characters.");
            return "/edit_post";
        } else if(dateCompare(post.getDate())){
            model.addAttribute("error_text", "Enter a valid date.");
            return "/edit_post";
        }
        service.save(post_final);
        System.out.println("new" + post_final.getId());
        return "redirect:/posts/";

    }

    /**
     * Mapping for deleting a post "/delete/{id}"
     * @param id id of the post
     * @return redirects to all_posts.html web page
     */
    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/posts/";
    }


    /**
     * Mapping for register form "/register"
     * @param model model containing user object as an attribute
     * @return register.html web page
     */
    @RequestMapping("/register")
    public String register(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    /**
     * Mapping for saving register form "/register"
     * @param user user object
     * @param bindingResult
     * @param model model containing doExist as an attribute
     * @return redirects to root all_posts.html
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()) return "register";



        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoles("ROLE_USER");

        String response = userService.save(user);
        if(response.equals("exists") ) {
            model.addAttribute("doExist", "Username Already Exist!");
            return "register";
        }

        return "redirect:/";
    }

}
