package com.text.golo.service;

import com.text.golo.entity.Keyword;
import com.text.golo.entity.Text;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TfIdfService {

    private String delimiter = "[^\\w']+";

    /**
     * @author Mohamed Guendouz
     */
    class TFIDFCalculator {
        /**
         * @param doc  list of strings
         * @param term String represents a term
         * @return term frequency of term in document
         */
         double tf(List<String> doc, String term) {
            double result = 0;
            for (String word : doc) {
                if (term.equalsIgnoreCase(word))
                    result++;
            }
            return result / doc.size();
        }

        /**
         * @param docs list of list of strings represents the dataset
         * @param term String represents a term
         * @return the inverse term frequency of term in documents
         */
        double idf(List<List<String>> docs, String term) {
            double n = 0;
            for (List<String> doc : docs) {
                for (String word : doc) {
                    if (term.equalsIgnoreCase(word)) {
                        n++;
                        break;
                    }
                }
            }
            return Math.log(docs.size() / n);
        }

        /**
         * @param doc  a text document
         * @param docs all documents
         * @param term term
         * @return the TF-IDF of term
         */
         double tfIdf(List<String> doc, List<List<String>> docs, String term) {
            return tf(doc, term) * idf(docs, term);

        }

    }

    private List<String> tokenize(Text text){
        return Arrays.asList(text.getContent().split(this.delimiter));
    }

//    private List<Keyword> getKeywordsForText()

    public Text injectKeywords(List<Text> texts, Text newText){
        TFIDFCalculator tfidfCalculator = new TFIDFCalculator();
        int topNumber;

        List<List<String>> textsAsLists = texts.stream().map(this::tokenize).collect(Collectors.toList());

        List<String> newTextAsList = this.tokenize(newText);

        Set<String> newTextAsSet = new LinkedHashSet<>(newTextAsList);

        textsAsLists.add(newTextAsList);

        List<Keyword> scoredTerms = newTextAsSet.stream().map(n -> {
            double score = tfidfCalculator.tfIdf(newTextAsList, textsAsLists, n);
            return Keyword.builder().name(n).tfIdf(score).build();
        }).sorted((a, b) -> Double.compare(b.getTfIdf(), a.getTfIdf())).collect(Collectors.toList());

        topNumber = (int) Math.floor(Math.cbrt(scoredTerms.size()));

        List<Keyword> keywords = scoredTerms.subList(0,topNumber);

        newText.setKeywords(keywords);

        return newText;



    }




}
