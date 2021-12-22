package come491.hanibana.Model;

import java.util.ArrayList;
import java.util.List;

public class subCategoryModel {
    String id;
    String subCategoryName;
    String subCategoryImage;
    String topCategoryName;
    String topCategoryid;

    public subCategoryModel(String id, String subCategoryName, String subCategoryImage, String topCategoryName, String topCategoryid) {
        this.id = id;
        this.subCategoryName = subCategoryName;
        this.subCategoryImage = subCategoryImage;
        this.topCategoryName = topCategoryName;
        this.topCategoryid = topCategoryid;
    }

    public subCategoryModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getSubCategoryImage() {
        return subCategoryImage;
    }

    public void setSubCategoryImage(String subCategoryImage) {
        this.subCategoryImage = subCategoryImage;
    }

    public String getTopCategoryName() {
        return topCategoryName;
    }

    public void setTopCategoryName(String topCategoryName) {
        this.topCategoryName = topCategoryName;
    }

    public String getTopCategoryid() {
        return topCategoryid;
    }

    public void setTopCategoryid(String topCategoryid) {
        this.topCategoryid = topCategoryid;
    }

}
