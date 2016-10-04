package com.savior;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class HelloController {

    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    public HelloController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String helloFacebook(Model model) {
    	
    	System.out.println("The Login request got hit..");
    	
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
        	System.out.println("Facebook object got created..");
        	
//        	if(connectionRepository.findPrimaryConnection(Facebook.class).getApi().isAuthorized())
//        	{
//        		System.out.println("Facebook is authorized !!");
//        	}
//        	if(connectionRepository.getPrimaryConnection(Facebook.class).getApi().isAuthorized())
//        	{
//        	
//        	}
        	
        	System.out.println("About to redirect the page now to connnect/facebook without authentication");
            return "redirect:/connect/facebook";
        }

      
        
//        Facebook facebook = new FacebookTemplate();
//        
//        Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
//        Facebook facebook1 = connection != null ? connection.getApi() : new FacebookTemplate();
//        
//        org.springframework.social.facebook.api.User facebookUser = facebook.userOperations().getUserProfile();
        
        
        model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
//        System.out.println("The email Id is " + facebook.userOperations().getUserProfile().getEmail());
        System.out.println("The name is " + facebook.userOperations().getUserProfile().getFirstName());
//        System.out.println("The birthday is " + facebook.userOperations().getUserProfile().getBirthday());
//        System.out.println("The gender is " + facebook.userOperations().getUserProfile().getGender());
//        
//        List<String> friendsIds = facebook.friendOperations().getFriendIds();
//        for(String id : friendsIds){
//            org.springframework.social.facebook.api.User user = facebook.userOperations().getUserProfile(id);
//            System.out.println("Friend id:"+ id + " "+user.getEmail());
//            System.out.println(user.getFirstName());
//        }
        
        //model.addAttribute("facebookProfileEmail", facebook.userOperations().getUserProfile().getEmail());
        PagedList<Post> feed = facebook.feedOperations().getFeed();
        model.addAttribute("feed", feed);
        
        return "hello";
    }

}
