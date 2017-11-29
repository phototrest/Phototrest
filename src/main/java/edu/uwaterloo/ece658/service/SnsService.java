/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.DeleteTopicRequest;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;
import com.amazonaws.services.sns.model.UnsubscribeRequest;
import edu.uwaterloo.ece658.entity.Tag;
import edu.uwaterloo.ece658.entity.User;
import java.net.URL;
import javax.ejb.Stateless;

/**
 *
 * @author Daniel
 */
@Stateless
public class SnsService {
    final private String accessKey = "AKIAJWCWANPXM4H3CAMA";
    final private String secretKey = "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";
    final private String bucketName = "phototrest";
    final private BasicAWSCredentials creds =
            new BasicAWSCredentials(accessKey, secretKey);
    final private AmazonSNSClient snsClient = (AmazonSNSClient) AmazonSNSClientBuilder
            .standard()
            .withRegion(Regions.CA_CENTRAL_1)
            .withCredentials(new AWSStaticCredentialsProvider(creds))
            .build();
    
    public void createNewTopic(Tag tag) {
        CreateTopicRequest createTopicRequest =
                new CreateTopicRequest("MyNewTopic");
        CreateTopicResult createTopicResult =
                snsClient.createTopic(createTopicRequest);
        tag.setTopicArn(createTopicResult.getTopicArn());
    }
    
    public void subscribeToTopic(String topicArn, String tagName, User user) {
        //subscribe to an SNS topic
        SubscribeRequest subRequest =
                new SubscribeRequest(topicArn, "email", user.getEmail());
        SubscribeResult result = snsClient.subscribe(subRequest);
        user.addSubscriptionArn(tagName, result.getSubscriptionArn());
    }
    
    public void unsubscribeFromTopic(String tagName, User user) {    
        String subscriptionArn = user.getSubscriptionArn(tagName);
        UnsubscribeRequest unsubscribeRequest = 
                new UnsubscribeRequest(subscriptionArn);
        snsClient.unsubscribe(unsubscribeRequest);
        user.removeSubscriptionArn(tagName, subscriptionArn);
    }
    
    public void publishToTopic(URL url, String topicArn) {
        //publish to an SNS topic
        PublishRequest publishRequest = new PublishRequest(topicArn, url.toString());
        snsClient.publish(publishRequest);
    }
    
    public void deleteTopic(String topicArn) {
        //delete an SNS topic
        DeleteTopicRequest deleteTopicRequest = new DeleteTopicRequest(topicArn);
        snsClient.deleteTopic(deleteTopicRequest);
    }
}
