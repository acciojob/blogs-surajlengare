package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringTokenizer;

@Service
public class ImageService
{
    @Autowired
    BlogRepository blogRepository2;

    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions)
    {
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();

        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);

        blog.getImageList().add(image);     // updating imageList of respective blog
        blogRepository2.save(blog);         // saving parent

        return image;
    }

    public void deleteImage(Integer id)
    {
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions)
    {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();
        int len = screenDimensions.length();
        int first = Integer.parseInt(screenDimensions.substring(0, len/2));
        int second = Integer.parseInt(screenDimensions.substring((len/2) + 1));

        String imageDim = image.getDimensions();
        int imageLen = imageDim.length();
        int imageD1 = Integer.parseInt(imageDim.substring(0,imageLen/2));
        int imageD2 = Integer.parseInt(imageDim.substring((imageLen/2) + 1));

        int count = (first/imageD1) * (second/imageD2);
        return count;
    }
}
