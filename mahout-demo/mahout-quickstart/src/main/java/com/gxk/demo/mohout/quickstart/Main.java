package com.gxk.demo.mohout.quickstart;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.net.URL;
import java.util.List;

public class Main {

  public static void main(String[] args) throws Exception {

    URL resource = Main.class.getClassLoader().getResource("dataset.csv");
    File file = new File(resource.toURI());

    DataModel model = new FileDataModel(file);

    UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

    UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);

    UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

    List<RecommendedItem> recommendations = recommender.recommend(2, 3);
    for (RecommendedItem recommendation : recommendations) {
      System.out.println(recommendation);
    }
  }
}
