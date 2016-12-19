package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by hubeini on 2016/12/19.
 */
@Entity
@IdClass(ImagePK.class)
public class Image {
    private int imageId;
    private String imagePath;

    @Id
    @Column(name = "image_id", nullable = false)
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Id
    @Column(name = "image_path", nullable = false, length = 255)
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (imageId != image.imageId) return false;
        if (imagePath != null ? !imagePath.equals(image.imagePath) : image.imagePath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = imageId;
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }
}
