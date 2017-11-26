package net.iwantbuyer.module.product.model.entity;

/**
 * Created by yangshuyu on 2017/5/8.
 */
public class CarouselBean {


    /**
     * _resource : type of the resource
     * batch_id : 0
     * game_id : 0
     * id : 0
     * image : string
     * url : string
     */

    private String _resource;
    private int batch_id;
    private int game_id;
    private int id;
    private String image;
    private String url;

    public String get_resource() {
        return _resource;
    }

    public void set_resource(String _resource) {
        this._resource = _resource;
    }

    public int getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(int batch_id) {
        this.batch_id = batch_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
