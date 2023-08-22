package com.kuro15.jo.aeindexlao;

/**
 * Created by Jo on 06-05-17.
 */

public class LaoIndexModel {
    private int IndexID;
    private String IndexName;
    private String IndexEng;

    public LaoIndexModel(int indexID, String indexName, String indexEng) {
        IndexID = indexID;
        IndexName = indexName;
        IndexEng = indexEng;
    }

    public int getIndexID() {
        return IndexID;
    }

    public void setIndexID(int indexID) {
        IndexID = indexID;
    }

    public String getIndexName() {
        return IndexName;
    }

    public void setIndexName(String indexName) {
        IndexName = indexName;
    }

    public String getIndexEng() {
        return IndexEng;
    }

    public void setIndexEng(String indexEng) {
        IndexEng = indexEng;
    }
}
