package com.example.catchman.studlife.network;

/**
 * Created by catchman on 6/14/18.
 */

public interface WebParams {
    String BASE_URL = "https://homeward-bound-boxc.000webhostapp.com/";
    String HEADER_AUTHORIZATION = "Authorization";
    String HEADER_ORGANIZATION = "Organization";
    String HEADER_CONTENT_TYPE = "Content-Type";
    String AUTH_TYPE = "Bearer";
    String AUTH_TYPE_BASIC = "Basic";
    String USERNAME = "username";
    String PASSWORD = "password";
    String GRANT_TYPE = "grant_type";
    String IMAGE_TO_PROCESS = "imageToProcess";
    String USE_PREPROCESSING = "usePreprocessing";
    String SELECTED_REGION = "selectedRegion";
    String IMAGE_NAME_DUMMY = "image.jpg";
    String MEDIA_TYPE_IMAGE = "image/jpeg";
    String FRONT_IMAGE_TO_PROCESS = "frontImageToProcess";
    String BACK_IMAGE_TO_PROCESS = "backImageToProcess";
    String SELFIE_IMAGE_TO_PROCESS = "selfieImageToProcess";
}
