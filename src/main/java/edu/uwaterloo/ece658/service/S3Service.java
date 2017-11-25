/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.service;
import java.net.URL;

import javax.ejb.Stateless;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

/**
 *
 * @author Daniel
 */
@Stateless
public class S3Service {

    // TODO: assign IAM to each user for S3 access
    final private String accessKey = "AKIAI3V5NZVUSNCMDN7Q";
    final private String secretKey = "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";
    final private String bucketName = "phototrest";
    
    public URL getUploadURL(String s3Key) {
        URL uploadURL = getS3Url(s3Key, HttpMethod.PUT);
        return uploadURL;
    }
    
    public URL getDownloadURL(String S3key) {
        URL downloadURL = getS3Url(S3key, HttpMethod.GET);
        return downloadURL;
    }
    
    /**
     * Returns the URL for a specified key. Specify method for upload or download
     * @param objectKey 
     * @param method (PUT = upload, GET = download)
     * @return 
     */
    private URL getS3Url(String objectKey, HttpMethod method) {
        BasicAWSCredentials creds =
                new BasicAWSCredentials(accessKey, secretKey);
        
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(
                new AWSStaticCredentialsProvider(creds)).build();
        
        URL url = null;
        try {
                java.util.Date expiration = new java.util.Date();
                long milliSeconds = expiration.getTime();
                milliSeconds += 1000 * 60 * 60; // Add 1 hour.
                expiration.setTime(milliSeconds);

                GeneratePresignedUrlRequest generatePresignedUrlRequest = 
                            new GeneratePresignedUrlRequest(bucketName, objectKey);
                generatePresignedUrlRequest.setMethod(method); 
                generatePresignedUrlRequest.setExpiration(expiration);

                url = s3Client.generatePresignedUrl(generatePresignedUrlRequest); 
        } catch (AmazonServiceException exception) {
                System.out.println("Caught an AmazonServiceException, " +
                    "which means your request made it to Amazon S3, " +
                    "but was rejected with an error response for some reason.");
                System.out.println("Error Message: " + exception.getMessage());
                System.out.println("HTTP  Code: "    + exception.getStatusCode());
                System.out.println("AWS Error Code:" + exception.getErrorCode());
                System.out.println("Error Type:    " + exception.getErrorType());
                System.out.println("Request ID:    " + exception.getRequestId());
        } catch (AmazonClientException ace) {
                System.out.println("Caught an AmazonClientException, " +
                    "which means the client encountered an internal error " +
                    "while trying to communicate with S3, such as not being " +
                    "able to access the network.");
                System.out.println("Error Message: " + ace.getMessage());
        }
        return url;            
    }
    
    public boolean deleteImage(String s3key) {
        BasicAWSCredentials creds =
                new BasicAWSCredentials(accessKey, secretKey);
        
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(
                new AWSStaticCredentialsProvider(creds)).build();
        
        try {
            s3Client.deleteObject(new DeleteObjectRequest(bucketName, s3key));
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            return false;
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException.");
            System.out.println("Error Message: " + ace.getMessage());
            return false;
        }
        return true;
    }
}
