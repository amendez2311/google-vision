package com.upc.ia.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.upc.ia.entity.GoogleVisionEntity;
import com.upc.ia.repository.GoogleVisionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;

@Service
public class GoogleVisionService {
	@Autowired
	private GoogleVisionEntityRepository googleVisionEntityRepository;

	private GoogleVisionEntity googleVisionEntity;

	private String infoGet;

	public List<AnnotateImageResponse> processImage(MultipartFile file,String key) throws IOException {

		try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {

			byte[] data = file.getBytes();
			ByteString imgBytes = ByteString.copyFrom(data);

			// Builds the image annotation request
			List<AnnotateImageRequest> requests = new ArrayList<>();
			Image img = Image.newBuilder().setContent(imgBytes).build();
			Feature feat = Feature.newBuilder().setType(Type.LABEL_DETECTION).build();
			Feature feat2 = Feature.newBuilder().setType(Type.TEXT_DETECTION).build();
			AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).addFeatures(feat2).setImage(img).build();
			requests.add(request);

			// Performs label detection on the image file
			BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
			List<AnnotateImageResponse> responses = response.getResponsesList();

			//String infoGet="";
			for (AnnotateImageResponse res : responses) {
				if (res.hasError()) {
					System.out.format("Error: %s%n", res.getError().getMessage());
				}

				System.out.println("############# LABELS OF IMAGE #############");
				for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
					this.googleVisionEntity= new GoogleVisionEntity();
					annotation.getAllFields().forEach((k, v) -> {
						System.out.println("++++");
						this.infoGet=k.getName();
						googleVisionEntity.setKey(key);
						if(infoGet.equals("mid")){
							googleVisionEntity.setMid(v.toString().replaceAll("\n", " "));
						}else if(infoGet.equals("description")){
							googleVisionEntity.setDescription(v.toString().replaceAll("\n", " "));
						}else if(infoGet.equals("score")){
							googleVisionEntity.setScore(v.toString().replaceAll("\n", " "));
						}else if(infoGet.equals("topicality")){
							googleVisionEntity.setTopicality(v.toString().replaceAll("\n", " "));
						}
						System.out.println("Tipo= "+infoGet+" - v.toString() = "+v.toString().replaceAll("\n", " "));
					});
					System.out.println("-------------------"+googleVisionEntity.toString());
					this.googleVisionEntityRepository.save(googleVisionEntity);
				}
				System.out.println("############# TEXT OF IMAGE #############");
				for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
					this.googleVisionEntity= new GoogleVisionEntity();
					annotation.getAllFields().forEach((k, v) -> {
						System.out.println("++++");
						this.infoGet=k.getName();
						googleVisionEntity.setKey(key);
						System.out.println("+++ TEXT OF IMAG= +"+this.infoGet);
						if(infoGet.equals("locale")){
							googleVisionEntity.setLocale(v.toString());
						}else if(infoGet.equals("description")){
							googleVisionEntity.setDescription(v.toString().replaceAll("\n", " ") );
						}
						System.out.println("Tipo= "+infoGet+" - v.toString() = "+v.toString().replaceAll("\n", " ") );

					});
					System.out.println("-------------------"+googleVisionEntity.toString());
					this.googleVisionEntityRepository.save(googleVisionEntity);
				}
			}
			return responses;

		}

	}

}
