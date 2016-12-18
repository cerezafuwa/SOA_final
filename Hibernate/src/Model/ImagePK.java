package Model;

import java.io.Serializable;

/**
 * Created by hubeini on 2016/12/19.
 */
public class ImagePK implements Serializable {
    private int imageId;
    private String imagePath;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImagePK imagePK = (ImagePK) o;

        if (imageId != imagePK.imageId) return false;
        if (imagePath != null ? !imagePath.equals(imagePK.imagePath) : imagePK.imagePath != null) return false;

        return true;
    }

    public int hashCode() {
        int result = imageId;
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }
}
