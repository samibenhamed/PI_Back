package PI.OLTP.API.DTO;

import PI.OLTP.API.Model.Image;
import lombok.Data;

@Data
public class ImageDTO {
    private String name ;
    private String type ;
    private byte[] bytes ;

    public ImageDTO toDTO (Image image ) {
        this.setName(image.getName());
        this.setType(image.getType());
        this.setBytes(image.getBytes());
        return this ;

    }
}
