package com.feedback.feedback.npl_integrator;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GoogleNLPIntegrator {
    private LanguageServiceClient language;

    public GoogleNLPIntegrator() {
        try {
            this.language = LanguageServiceClient.create();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Sentiment analyze(String text){
        Document doc = Document.newBuilder()
                .setContent(text).setType(Document.Type.PLAIN_TEXT).build();
        return language.analyzeSentiment(doc).getDocumentSentiment();
    }
}
