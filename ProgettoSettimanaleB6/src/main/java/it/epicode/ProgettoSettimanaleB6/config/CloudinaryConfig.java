package it.epicode.ProgettoSettimanaleB6.config;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dtzncihbd",
                "api_key", "563452228256964",
                "api_secret", "Q6bRwYSFBCVb8KWlMGMaG4KKB1k"));
    }
}